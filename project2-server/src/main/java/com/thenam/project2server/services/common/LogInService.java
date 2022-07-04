package com.thenam.project2server.services.common;

import com.thenam.project2server.entity.UserEntity;
import com.thenam.project2server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInService {

    @Autowired
    UserRepository userRepository;

    public int handleLogIn(String userName, String password){
        List<UserEntity> userByName = userRepository.getAccountByUserName(userName);
        if(userByName.size() == 0) return 1;
        List<UserEntity> userByPassword = userRepository.getAccountByPassword(password);
        if(userByPassword.size() == 0) return 2;

        return 0;
    }
}
