package com.thenam.project2server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserTopicKeyComposite.class)
public class UserTopicEntity {

    @Id
    @Column(name="user_id")
    private UUID userId;

    @Id
    @Column(name="topic_id")
    private int topicId;

    @Column(name="count")
    private int count;
}
