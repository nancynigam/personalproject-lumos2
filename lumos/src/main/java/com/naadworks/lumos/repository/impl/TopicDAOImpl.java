package com.naadworks.lumos.repository.impl;

import com.naadworks.lumos.entity.TopicEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDAOImpl extends CrudRepository<TopicEntity,Long> {
}