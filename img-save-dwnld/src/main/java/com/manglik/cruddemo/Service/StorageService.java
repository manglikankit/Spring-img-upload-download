package com.manglik.cruddemo.Service;

import com.manglik.cruddemo.Entity.ImageData;
import com.manglik.cruddemo.Repo.StorageRepo;
import com.manglik.cruddemo.Util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepo storageRepo;
    private final String FOLDER_PATH = "/Users/apple/Downloads/Software/bckp/Spring boot /Practices/img-save-dwnld/src/main/resources/MyImages/"; // "/Users/apple/Desktop/MyFiles/";


    public String uploadImage(MultipartFile file) throws IOException{
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        ImageData imageData = storageRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                        .filePath(filePath).build());
        file.transferTo(new File(filePath));
        if(imageData != null){
            return "file uploaded successfully "+ filePath;
        }
        return null;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Optional<ImageData> imageData = storageRepo.findByName(fileName);
        String filePath = imageData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath()); //ImageUtils.decompressImage(imageData.get().getImageData());
        return images;
    }
}
