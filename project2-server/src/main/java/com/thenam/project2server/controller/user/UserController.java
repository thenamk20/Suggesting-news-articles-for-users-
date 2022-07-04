package com.thenam.project2server.controller.user;

import com.thenam.project2server.entity.UserTopicEntity;
import com.thenam.project2server.repositories.UserTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/user")
public class UserController {

    @Autowired
    UserTopicRepository userTopicRepo;

    @GetMapping(value = "/{userId}/{topicId}")
    public String updateUserTopicCount(
            @PathVariable UUID userId,
            @PathVariable int topicId
    ){

        List<UserTopicEntity> userTopic = userTopicRepo.findAll();
        System.out.println("thenam: " + userTopic.size());

        return "";
    }
}
