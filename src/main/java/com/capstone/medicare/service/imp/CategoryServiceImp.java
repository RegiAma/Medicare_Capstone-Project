package com.capstone.medicare.service.imp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.medicare.model.Category;
import com.capstone.medicare.service.CategoryService;
import com.capstone.medicare.repository.CategRepo;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategRepo categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<Category>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		return this.categoryRepository.getById(categoryId);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		this.categoryRepository.deleteById(categoryId);
		
	}

}

