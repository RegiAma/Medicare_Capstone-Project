package com.capstone.medicare.service.imp;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.medicare.model.Category;
import com.capstone.medicare.model.Medicin;
import com.capstone.medicare.repository.MedicinRepo;
import com.capstone.medicare.service.MedicinService;

@Service
public class MedicinServiceImp implements MedicinService{
	
	@Autowired
	private MedicinRepo medicineRepository;

	@Override
	public Medicin addMedicin(Medicin medicine) {
		return this.medicineRepository.save(medicine);
	}

	@Override
	public Medicin updateMedicin(Medicin medicine) {
		return this.medicineRepository.save(medicine);
	}

	@Override
	public Set<Medicin> getMedicin() {
		return new LinkedHashSet<Medicin>(this.medicineRepository.findAll());
	}

	@Override
	public Medicin getMedicin(Long medicineId) {
		return this.medicineRepository.findById(medicineId).get();
	}

	@Override
	public Medicin deleteMedicin(Long id) {
		Medicin medicine = this.medicineRepository.getOne(id); //only added line 1
		this.medicineRepository.deleteById(id);
		return medicine;
		
	}

	@Override
	public void deleteByName(String name) {
		this.medicineRepository.deleteByName(name);
		
	}

	@Override
	public List<Medicin> MedicinesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.medicineRepository.findByCategory(category);
	}

	@Override
	public List<Medicin> getActiveMedicine() {
		return this.medicineRepository.findByActive(true);
	}

	@Override
	public List<Medicin> getActiveMedicinesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.medicineRepository.findByCategoryAndActive(category, true);
	}
	
	@Override
    public List<Medicin> searchByName(String name) {
        // TODO Auto-generated method stub
        return this.medicineRepository.findByNameAndActive(name, true);
    }

}
