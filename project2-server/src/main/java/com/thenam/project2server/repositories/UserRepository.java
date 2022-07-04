package com.thenam.project2server.repositories;

import com.thenam.project2server.entity.ArticleEntity;
import com.thenam.project2server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "SELECT * FROM users  WHERE user_name = :userName", nativeQuery = true)
    public List<UserEntity> getAccountByUserName(String userName);

    @Query(value = "SELECT * FROM users  WHERE password = :password", nativeQuery = true)
    public List<UserEntity> getAccountByPassword(String password);
}
