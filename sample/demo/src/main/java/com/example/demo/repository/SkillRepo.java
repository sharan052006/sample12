package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
    
}
