package com.thenam.project2server.mapper;

import com.thenam.project2server.dto.ArticleDTO;
import com.thenam.project2server.entity.ArticleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesMapper {

    @Autowired
    private ModelMapper mapper;

    public ArticleDTO convertToDTO(ArticleEntity entity){
        ArticleDTO dto = mapper.map(entity, ArticleDTO.class);
        return dto;
    }

}
