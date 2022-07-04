package com.thenam.project2server.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thenam.project2server.dto.UserDTO;
import com.thenam.project2server.entity.PageSourceEntity;
import com.thenam.project2server.services.AdminServices.AdminServices;
import com.thenam.project2server.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.File;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminServices adminServices;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UploadFileUtils uploadFileUtils;

    @GetMapping(value="/all_users")
    public String showAllUsers(){
        System.out.println("thenam: ");
        List<UserDTO> allUsers = adminServices.showAllUser();
        try {
            return mapper.writeValueAsString(allUsers);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @GetMapping(value = "/all_page_source")
    public String showAllPageSource(){
        List<PageSourceEntity> allPage = adminServices.showAllPageSource();
        Collections.shuffle(allPage);
        try {
            return mapper.writeValueAsString(allPage);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @GetMapping(value="/test")
    public String test(){
       return "test succes";
    }

    @PostMapping(value="/uploadListNews")
    public ResponseEntity<String> uploadListNews(
            @RequestParam("upload_file") MultipartFile uploadedFile,
            @RequestParam("page_source_id") String pageID
    ) {

        File file = this.uploadFileUtils.handleUploadFiles(uploadedFile);
        int pageId = Integer.parseInt(pageID);
        System.out.println(pageID);

        adminServices.handleUploadNewsList(file.getPath(), pageId);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
