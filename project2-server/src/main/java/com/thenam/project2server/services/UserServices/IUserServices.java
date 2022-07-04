package com.thenam.project2server.services.UserServices;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IUserServices {

    public void updateUserTopicCount(UUID userId, int topicId);
}
