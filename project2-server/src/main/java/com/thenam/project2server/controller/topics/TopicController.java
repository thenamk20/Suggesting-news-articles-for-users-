package com.thenam.project2server.controller.topics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thenam.project2server.dto.ArticleDTO;
import com.thenam.project2server.entity.TopicEntity;
import com.thenam.project2server.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TopicRepository topicRepo;

    @GetMapping(value="/all")
    public String showAll() {

        List<TopicEntity> result = topicRepo.findAll();
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
