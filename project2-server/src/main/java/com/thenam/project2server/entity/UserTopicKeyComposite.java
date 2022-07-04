package com.thenam.project2server.entity;


import java.io.Serializable;
import java.util.UUID;

public class UserTopicKeyComposite  implements Serializable {

    private UUID userId;

    private int topicId;
}
