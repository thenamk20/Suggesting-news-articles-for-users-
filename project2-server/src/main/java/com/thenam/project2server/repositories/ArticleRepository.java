package com.thenam.project2server.repositories;

import com.thenam.project2server.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    @Query(value = "SELECT * FROM articles  WHERE topic_id = ?1", nativeQuery = true)
    public List<ArticleEntity> getArticlesByTopic(int t);
}
