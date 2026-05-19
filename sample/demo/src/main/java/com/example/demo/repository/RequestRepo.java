package com.example.demo.repository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
    
}
