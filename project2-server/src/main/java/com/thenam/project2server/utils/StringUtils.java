package com.thenam.project2server.utils;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {
    private StringUtils() { }

    public  boolean isNullOrEmpty(String s){
        if(s==null || s.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isNullOrWhiteSpace(String s){
        if(s==null || s.trim().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isBlank(String s){
        if(s==null || s.trim().isEmpty() || s.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
