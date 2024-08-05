package com.capstone.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.medicare.model.Category;

@Repository
public interface CategRepo extends JpaRepository<Category, Long>{

}

