package com.manglik.cruddemo;

import com.manglik.cruddemo.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
public class CruddemoApplication {

	@Autowired
	private StorageService storageService;
	@PostMapping("uploadImage")
	public ResponseEntity<?> uploadImage(@RequestParam(name = "image") MultipartFile file) throws IOException {
		String uploadImage =  storageService.uploadImage(file);
		ResponseEntity<String> body = ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
		return body;

	}

	@GetMapping("downloadImage/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException{
		byte[] imageData = storageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

}
