package com.naadworks.lumos.service.impl;

import com.naadworks.lumos.entity.UserEntity;
import com.naadworks.lumos.entry.UserEntry;
import com.naadworks.lumos.exception.BaseException;
import com.naadworks.lumos.repository.impl.UserDAOImpl;
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
public class UserServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAOImpl userDAO;

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public UserEntry findById(Long id) throws BaseException {
        UserEntity userEntity = null;
        if (!getUserDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        try {
            userEntity = getUserDAO().findOne(id);
            log.info("UserEntity with id = " + id + " found successfully");
        } catch (Exception e1) {
            log.error(e1.getMessage());
            throw new BaseException("Id cannot be null. ");
        }
        return convertToEntry(userEntity);
    }

    public List<UserEntry> findAll() {
        List<UserEntry> userEntityList = new ArrayList<>();
        for (UserEntity ue : getUserDAO().findAll()) {
            userEntityList.add(convertToEntry(ue));
        }
        return userEntityList;
    }

    //TODO : how to check if instead of number a string is passed in age. Bad request 400 is send by Spring, is that enough
    //TODO : how to check for duplicate entries [do we need extra checks apart from making it unique in db]
    public UserEntry create(UserEntry userEntry) throws BaseException {
        UserEntity userEntity = null;
        if (StringUtils.isEmpty(userEntry.getName()))
            throw new BaseException("Name field cannot be null or Empty.");
        if (StringUtils.isEmpty(userEntry.getEmail()))
            throw new BaseException("Email field cannot be null or Empty.");
        try {
            userEntity = getUserDAO().save(convertToEntity(userEntry));
            log.info("New UserEntity with id = " + userEntity.getId() + " created successfully");
        } catch (Exception e1) {
            String message = "";
            if (userEntry == null)
                message = "UserEntry cannot be null";
            else
                // TODO : Should this be else or should I put an if condition.
                message = "Duplication of email";
            log.error(e1.getMessage());
            throw new BaseException("ERROR while creating new user. " + message);
        }
        return convertToEntry(userEntity);
    }

    public UserEntry update(UserEntry userEntry, Long id) throws BaseException {
        userEntry.setId(id);
        UserEntity userEntity = null;
        if (!getUserDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        if (StringUtils.isEmpty(userEntry.getName()))
            throw new BaseException("Name field cannot be null or Empty.");
        if (StringUtils.isEmpty(userEntry.getEmail()))
            throw new BaseException("Email field cannot be null or Empty.");
        try {
            userEntity = getUserDAO().save(convertToEntity(userEntry));
            log.info("UserEntity with id = " + userEntity.getId() + " updated successfully");
        } catch (Exception e1) {
            String message = "";
            if (id == null)
                message = "Id cannot be null";
            else if (userEntry == null)
                message = "UserEntry cannot be null";
            else
                message = "Duplication of email";
            log.error(e1.getMessage());
            throw new BaseException("ERROR while updating user entity. " + message);
        }
        return convertToEntry(userEntity);
    }

    public void delete(Long id) throws BaseException {
        if (!getUserDAO().exists(id))
            throw new BaseException("Id doesn't exists in the database");
        try {
            getUserDAO().delete(id);
            log.info("UserEntity with id = " + id + " deleted successfully");
        } catch (Exception e1) {
            log.error(e1.getMessage());
            throw new BaseException("ERROR while deleting user entity as the given id is null. ");
        }
    }

    //TODO : how to handle exception here
    public UserEntry convertToEntry(UserEntity userEntity) {
        UserEntry userEntry = null;
        if (userEntity != null) {
            userEntry = new UserEntry();
            try {
                BeanUtils.copyProperties(userEntity, userEntry);
            } catch (BeansException ex) {
                log.error("Error while converting to entry = ", ex);
                return null;
            }
        }
        return userEntry;
    }

    public UserEntity convertToEntity(UserEntry userEntry) {
        UserEntity userEntity = null;
        if (userEntry != null) {
            userEntity = new UserEntity();
            try {
                BeanUtils.copyProperties(userEntry, userEntity);
            } catch (BeansException ex) {
                log.error("error while converting to entity");
                return null;
            }
        }
        return userEntity;
    }
}
