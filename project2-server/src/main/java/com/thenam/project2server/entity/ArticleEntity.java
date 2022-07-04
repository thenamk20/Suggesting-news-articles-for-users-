package com.thenam.project2server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="article_id")
    private UUID id;

    @Column(name="topic_id")
    private int topicId;

    @Column(name="link")
    private String link;

    @Column(name="title")
    private String title;

    @Column(name="sub_content")
    private String subContent;

    @Column(name="src_image")
    private String srcImage;

    @Column(name="page_source_id")
    private int pageSourceId;

}
