package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    
}