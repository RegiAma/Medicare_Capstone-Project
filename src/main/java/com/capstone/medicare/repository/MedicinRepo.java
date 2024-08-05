package com.capstone.medicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.medicare.model.Category;
import com.capstone.medicare.model.*;

@Repository
public interface MedicinRepo extends JpaRepository<Medicin,Long>{
	
	public void deleteByName(String name);
	
	public List<Medicin> findByCategory(Category category);
	
	public List<Medicin> findByActive(Boolean b);
	public List<Medicin> findByCategoryAndActive(Category category, Boolean b);
 
	public List<Medicin> findByNameAndActive(String name, Boolean b);

}