package com.thenam.project2server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topic_id")
    private Integer id;

    @Column(name="topic_name")
    private String name;
}

