package com.thenam.project2server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private UUID id;

    private String link;

    private String srcImage;

    private String title;

    private String subContent;


}
