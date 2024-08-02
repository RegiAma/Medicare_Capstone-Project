package com.capstone.medicare.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.simplilearn.Medicare_App.model.Category;
import com.simplilearn.Medicare_App.model.Medicine;
import com.simplilearn.Medicare_App.repository.MedicineRepository;
import com.simplilearn.Medicare_App.service.MedicineService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "medicines")
public class MedicineContr {

	@Autowired
	private MedicineService medicineService;
	//private byte[] bytes;


	//add category
	@PostMapping("/add")
	public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine){		
		Medicine medicine1 = this.medicineService.addMedicine(medicine);	
		return ResponseEntity.ok(medicine1);
	}

	//get category
	@GetMapping("/{medicineId}")
	public Medicine getMedicine(@PathVariable("medicineId") Long medicineId){			
		return this.medicineService.getMedicine(medicineId);		 
	}

	// get all categories

	@GetMapping("/")
	public ResponseEntity<?> getAllMedicines(){	
		return ResponseEntity.ok(this.medicineService.getMedicines());	
	}

	@PutMapping("/")
	public Medicine updateMedicine(@RequestBody Medicine medicine) {
		return this.medicineService.updateMedicine(medicine);
	}

	@DeleteMapping("/{id}")
	public Medicine deleteMedicine(@PathVariable("id") Long id) {
		return this.medicineService.deleteMedicine(id);	
	}

	//	@DeleteMapping("/{name}")
	//	public void deleteMedicine(@PathVariable("name") String name) {
	//		this.medicineService.deleteByName(name);	
	//	}

	@GetMapping("/category/{cid}")
	public List<Medicine> getMedicinesOfCategory(@PathVariable("cid") Long cid){

		Category category = new Category();
		category.setCid(cid);
		return this.medicineService.MedicinesOfCategory(category);

	}

	@GetMapping("/active")
	public List<Medicine> getActiveMedicines(){	
		return this.medicineService.getActiveMedicine();	
	}

	@GetMapping("/category/active/{cid}")
	public List<Medicine> getActiveMedicinesOfCategory(@PathVariable("cid") Long cid){

		Category category = new Category();
		category.setCid(cid);
		return this.medicineService.getActiveMedicinesOfCategory(category);

	}



	@GetMapping("/active/{name}")
	public List<Medicine> searchByName(@PathVariable("name") String name){
		return this.medicineService.searchByName(name);        
	}


	//	@PostMapping("/upload")
	//	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
	//		this.bytes = file.getBytes();
	//	}

	//	@PostMapping("/add")
	//	public void addMedicine(@RequestBody Medicine medicine) throws IOException {
	//		medicine.setPicByte(this.bytes);
	//		medicineRepository.save(medicine);
	//		this.bytes = null;
	//	}
	//	@CrossOrigin(origins = "http://localhost:4200",methods = RequestMethod.POST)
	//	@PostMapping("/medicines")
	//	public Medicine addMedicine(@RequestBody Medicine medicine) {
	//		return medicineRepository.save(medicine);
	//	}
}
