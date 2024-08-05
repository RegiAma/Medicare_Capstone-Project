package com.capstone.medicare.service;

import java.util.List;
import java.util.Set;

import com.capstone.medicare.model.Category;
import com.capstone.medicare.model.Medicin;


public interface MedicinService {
	
	public Medicin addMedicin(Medicin medicine);
	
	public Medicin updateMedicin(Medicin medicine);
	
	public Set<Medicin> getMedicin();
	
	public Medicin getMedicin(Long medicineId);
	
	public Medicin deleteMedicin(Long id);
	
	public void deleteByName(String name);

	public List<Medicin> MedicinesOfCategory(Category category);
	
	public List<Medicin> getActiveMedicine();
	public List<Medicin> getActiveMedicinesOfCategory(Category category);
	
	public List<Medicin> searchByName(String name);
	
}