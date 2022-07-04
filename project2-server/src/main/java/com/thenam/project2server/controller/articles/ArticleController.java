package com.thenam.project2server.controller.articles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thenam.project2server.dto.ArticleDTO;
import com.thenam.project2server.services.ArticleServices.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    ArticleServices articleServices;
    @Autowired
    ObjectMapper mapper;

    @GetMapping(value="/all")
    public String showAll() {

        List<ArticleDTO> result = articleServices.showAll();
        Collections.shuffle(result);
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @GetMapping(value="/all/{userId}")
    public String showRecommend(
            @PathVariable String userId
    ) {
        return "ID: " + userId;
    }

    @GetMapping(value="/topic/{id}")
    public String showByTopic(
            @PathVariable Integer id
    ){
        List<ArticleDTO> result = articleServices.showByTopic(id);
        Collections.shuffle(result);
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
