package com.thenam.project2server.services.ArticleServices;

import com.thenam.project2server.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleServices {

    public List<ArticleDTO> showAll();

    public List<ArticleDTO> showByTopic(int topicId);
}
