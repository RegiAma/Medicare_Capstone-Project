package com.capstone.medicare.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.medicare.model.Category;
import com.capstone.medicare.model.Medicin; // Ensure this is the correct class name
import com.capstone.medicare.repository.MedicinRepo; // Ensure this is the correct repository
import com.capstone.medicare.service.MedicinService;

@Service
public class MedicinServiceImp implements MedicinService {

    @Autowired
    private MedicinRepo medicinRepo; // Ensure this is the correct repository

    @Override
    public Medicin addMedicin(Medicin medicine) { // Changed Medicine to Medicin
        return this.medicinRepo.save(medicine);
    }

    @Override
    public Medicin updateMedicin(Medicin medicine) { // Changed Medicine to Medicin
        return this.medicinRepo.save(medicine);
    }

    @Override
    public Set<Medicin> getMedicin() {
        return new LinkedHashSet<Medicin>(this.medicinRepo.findAll()); // Changed Medicine to Medicin
    }

    @Override
    public Medicin getMedicin(Long medicineId) {
        return this.medicinRepo.findById(medicineId).get();
    }

    @Override
    public Medicin deleteMedicin(Long id) {
        Medicin medicine = this.medicinRepo.getOne(id); // Changed Medicine to Medicin
        this.medicinRepo.deleteById(id);
        return medicine;
    }

    @Override
    public void deleteByName(String name) {
        this.medicinRepo.deleteByName(name);
    }

    @Override
    public List<Medicin> MedicinesOfCategory(Category category) {
        return this.medicinRepo.findByCategory(category);
    }

    @Override
    public List<Medicin> getActiveMedicine() {
        return this.medicinRepo.findByActive(true);
    }

    @Override
    public List<Medicin> getActiveMedicinesOfCategory(Category category) {
        return this.medicinRepo.findByCategoryAndActive(category, true);
    }

    @Override
    public List<Medicin> searchByName(String name) {
        return this.medicinRepo.findByNameAndActive(name, true);
    }
}

