package com.thenam.project2server.repositories;

import com.thenam.project2server.entity.UserTopicEntity;
import org.modelmapper.internal.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserTopicRepository extends JpaRepository<UserTopicEntity, Pair<UUID, Integer>>  {

}
