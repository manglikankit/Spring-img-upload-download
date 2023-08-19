package com.manglik.cruddemo.Repo;

import com.manglik.cruddemo.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface StorageRepo extends JpaRepository<ImageData, Integer> {
    Optional<ImageData> findByName(String name);
}
