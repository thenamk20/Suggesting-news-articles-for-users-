package com.thenam.project2server.repositories;

import com.thenam.project2server.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {
}
