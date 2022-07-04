package com.thenam.project2server.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UploadFileUtils {
    public File handleUploadFiles(MultipartFile uploadedFile) {
        String folderPath = "D:\\20212\\Project 2\\project2-server\\src\\main\\webapp\\storage";

        File myUploadFolder = new File(folderPath);

        // kiểm tra thư mục lưu trữ file có tồn tại? nếu không thì tạo mới
        if(!myUploadFolder.exists()) {
            myUploadFolder.mkdirs();
        }

        File savedFile = null;

        try {
            // Lưu file vào thư mục đã chọn
            String fileName = uploadedFile.getOriginalFilename();
            savedFile = new File(myUploadFolder, fileName);
            uploadedFile.transferTo(savedFile);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return savedFile;
    }
}
