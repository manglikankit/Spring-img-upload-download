package com.manglik.cruddemo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ImageData")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String filePath;

//    @Lob
//    @Column(name = "imageData")
//    private byte[] imageData;
}
