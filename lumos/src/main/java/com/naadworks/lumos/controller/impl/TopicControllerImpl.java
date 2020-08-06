package com.naadworks.lumos.controller.impl;

import com.naadworks.lumos.entry.TopicEntry;
import com.naadworks.lumos.exception.BaseException;
import com.naadworks.lumos.service.impl.TopicServiceImpl;
import com.naadworks.lumos.util.Status;
import com.naadworks.lumos.util.StatusType;
import com.naadworks.lumos.view.TopicView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicControllerImpl {

    private static final Logger log = LoggerFactory.getLogger(TopicControllerImpl.class);

    @Autowired
    private TopicServiceImpl topicService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
    public TopicView getById(@PathVariable("id") Long id) {
        Status status = null;
        List<TopicEntry> data = new ArrayList<>();
        try {
            TopicEntry entryFetched = getTopicService().findById(id);
            data.add(entryFetched);
            log.info("Topic entry with id = " + id + " found successfully");
            status = new Status(StatusType.SUCCESS);
        } catch (BaseException be) {
            log.error("Error during find by id = " + be + " ");
            be.printStackTrace();
            status = new Status(StatusType.ERROR);
            status.setMessage(status.getMessage() + ". " + be.getMessage());
        }
        return new TopicView(status, data);
    }

    @RequestMapping(value="/", method = RequestMethod.GET, produces = {"application/json"})
    public TopicView getAllTopics() {
        Status status = null;
        List<TopicEntry> data = new ArrayList<>();
        data = getTopicService().findAll();
        status = new Status(StatusType.SUCCESS);
        return new TopicView(status, data);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public TopicView create(@RequestBody TopicEntry topicEntry) {
        Status status = null;
        List<TopicEntry> data = new ArrayList<>();
        try {
            TopicEntry entryCreated = getTopicService().create(topicEntry);
            data.add(entryCreated);
            log.info("Topic entry with id = " + entryCreated.getId() + " created successfully");
            status = new Status(StatusType.SUCCESS);
        } catch (BaseException be) {
            log.error("ERROR during creating a new entry = " + be + " ");
            be.printStackTrace();
            status = new Status(StatusType.ERROR);
            status.setMessage(status.getMessage() + ". " + be.getMessage());
        }
        return new TopicView(status, data);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public TopicView update(@RequestBody TopicEntry topicEntry, @PathVariable("id") Long id) {
        Status status = null;
        List<TopicEntry> data = new ArrayList<>();
        try {
            TopicEntry entryUpdated = getTopicService().update(topicEntry, id);
            data.add(entryUpdated);
            log.info("Topic entry with id = " + entryUpdated.getId() + " updated successfully");
            status = new Status(StatusType.SUCCESS);
        } catch (BaseException be) {
            log.error("ERROR during updating entry = " + be + " ");
            be.printStackTrace();
            status = new Status(StatusType.ERROR);
            status.setMessage(status.getMessage() + ". " + be.getMessage());
        }
        return new TopicView(status, data);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TopicView delete(@PathVariable("id") Long id) {
        Status status = null;
        List<TopicEntry> data = new ArrayList<>();
        try {
            getTopicService().delete(id);
            log.info("Topic entry with id = " + id + " deleted successfully");
            status = new Status(StatusType.SUCCESS);
        } catch (BaseException be) {
            log.error("ERROR during deleting entry = " + be + " ");
            be.printStackTrace();
            status = new Status(StatusType.ERROR);
            status.setMessage(status.getMessage() + ". " + be.getMessage());
        }

        return new TopicView(status, data);
    }


    public TopicServiceImpl getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicServiceImpl topicService) {
        this.topicService = topicService;
    }
}
