package com.thenam.project2server.services.AdminServices;

import com.thenam.project2server.dto.UserDTO;
import com.thenam.project2server.entity.PageSourceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    public List<UserDTO> showAllUser();

    public List<PageSourceEntity> showAllPageSource();

    public void handleUploadNewsList(String filePath, int pageId);
}
