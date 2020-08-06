package com.naadworks.lumos.service.impl;

import com.naadworks.lumos.entity.TopicEntity;
import com.naadworks.lumos.entry.TopicEntry;
import com.naadworks.lumos.exception.BaseException;
import com.naadworks.lumos.repository.impl.TopicDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class TopicServiceImpl {

    private static Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Autowired
    private TopicDAOImpl topicDAO;

    public TopicDAOImpl getTopicDAO() {
        return topicDAO;
    }

    public void setTopicDAO(TopicDAOImpl topicDAO) {
        this.topicDAO = topicDAO;
    }

    public TopicEntry findById(Long id) throws BaseException{
        TopicEntity topicEntity = null;
        if (!getTopicDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        try {
            topicEntity = getTopicDAO().findOne(id);
            log.info("Topic entity with id = " + id + " found successfully");
        } catch (Exception e1) {
            log.error(e1.getMessage());
            throw new BaseException("Id cannot be null. ");
        }
        return convertToEntry(topicEntity);
    }

    public List<TopicEntry> findAll() {
        List<TopicEntry> topicEntityList = new ArrayList<>();
        for (TopicEntity ue : getTopicDAO().findAll()) {
            topicEntityList.add(convertToEntry(ue));
        }
        return topicEntityList;
    }

    public TopicEntry create(TopicEntry topicEntry) throws BaseException {
        TopicEntity topicEntity = null;
        if (StringUtils.isEmpty(topicEntry.getTitle()))
            throw new BaseException("Title field cannot be null or Empty.");
        try {
            topicEntity = getTopicDAO().save(convertToEntity(topicEntry));
            log.info("New topic entity with id = " + topicEntity.getId() + " created successfully");
        } catch (Exception e1) {
            String message = "";
            if (topicEntry == null)
                message = "topic entry cannot be null";
            else
                // TODO : Should this be else or should I put an if condition.
                message = "Duplication of title";
            log.error(e1.getMessage());
            throw new BaseException("ERROR while creating new topic. " + message);
        }
        return convertToEntry(topicEntity);
    }

    public TopicEntry update(TopicEntry topicEntry, Long id) throws BaseException {
        topicEntry.setId(id);
        TopicEntity topicEntity = null;
        if (!getTopicDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        if (StringUtils.isEmpty(topicEntry.getTitle()))
            throw new BaseException("Title field cannot be null or Empty.");
        try {
            topicEntity = getTopicDAO().save(convertToEntity(topicEntry));
            log.info("TopicEntity with id = " + topicEntity.getId() + " updated successfully");
        } catch (Exception e1) {
            String message = "";
            if (id == null)
                message = "Id cannot be null";
            else if (topicEntry == null)
                message = "Topic entry cannot be null";
            else
                message = "Duplication of title";
            log.error(e1.getMessage());
            throw new BaseException("ERROR while updating topic entity. " + message);
        }
        return convertToEntry(topicEntity);
    }

    public void delete(Long id) throws BaseException {
        if (!getTopicDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        try {
            getTopicDAO().delete(id);
            log.info("Topic entity with id = " + id + " deleted successfully");
        } catch (Exception e1) {
            log.error(e1.getMessage());
            throw new BaseException("ERROR while deleting topic entity as the given id is null. ");
        }
    }

    public TopicEntry convertToEntry(TopicEntity topicEntity) {
        TopicEntry topicEntry = null;
        if (topicEntity != null) {
            topicEntry = new TopicEntry();
            try {
                BeanUtils.copyProperties(topicEntity, topicEntry);
            } catch (BeansException ex) {
                log.error("Error while converting to entry = ", ex);
                return null;
            }
        }
        return topicEntry;
    }

    public TopicEntity convertToEntity(TopicEntry topicEntry) {
        TopicEntity topicEntity = null;
        if (topicEntry != null) {
            topicEntity = new TopicEntity();
            try {
                BeanUtils.copyProperties(topicEntry, topicEntity);
            } catch (BeansException ex) {
                log.error("Error while converting to entity");
                return null;
            }
        }
        return topicEntity;
    }
}
