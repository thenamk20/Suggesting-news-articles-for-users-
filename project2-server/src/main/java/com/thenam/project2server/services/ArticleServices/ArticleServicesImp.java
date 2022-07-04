package com.thenam.project2server.services.ArticleServices;

import com.thenam.project2server.dto.ArticleDTO;
import com.thenam.project2server.entity.ArticleEntity;
import com.thenam.project2server.repositories.ArticleRepository;
import com.thenam.project2server.utils.MapperUtils;
import com.thenam.project2server.utils.ReadHTMLDomUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServicesImp implements  ArticleServices{
    @Autowired
    ArticleRepository articleRepo;
    @Autowired
    ReadHTMLDomUtil readHTMLDomUtil;

    @Override
    public List<ArticleDTO> showAll(){
        List<ArticleEntity> allArticles = articleRepo.findAll();
        List<ArticleDTO> result = MapperUtils.mapAll(allArticles, ArticleDTO.class);
        return result;
    }

    public List<ArticleDTO> showByTopic(int topicId){
        List<ArticleEntity> allArticlesByID = articleRepo.getArticlesByTopic(topicId);
        List<ArticleDTO> result = MapperUtils.mapAll(allArticlesByID, ArticleDTO.class);
        return result;
    }
}
