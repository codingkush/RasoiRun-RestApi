package com.kushg.rasoiRun.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{

   @Autowired
   private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {

        try {
            Map<?, ?> uploadData = cloudinary.uploader().upload(
                    file.getBytes(),
                    Map.of("folder", "foodie-app")
            );
            return uploadData.get("secure_url").toString(); //img url
        } catch (IOException e) {
            throw new RuntimeException("Image uploading failed!!");
        }
    }


    @Override
    public boolean deleteFile(String publicId) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(publicId, Map.of());
            return "ok".equals(result.get("result"));
        } catch (IOException e) {
            throw new RuntimeException("Image deletion failed!", e);
        }
    }
}
