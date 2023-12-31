package com.example.catapp.repository;

import com.example.catapp.entity.CatImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatImageRepository extends JpaRepository<CatImage, Long> {
}
