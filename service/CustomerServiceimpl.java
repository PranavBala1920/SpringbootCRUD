package com.example.springboot.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springboot.entity.CustomerModel;
import com.example.springboot.repository.CustomerRepository;

import exception.ApiRequestException;

@Service
public class CustomerServiceimpl {

	@Autowired
	CustomerRepository customerRepository;

	// Unique Email Id Validation
	public List<CustomerModel> uniqMobileEmail(String email, String mobile, Long id) {
		List<CustomerModel> emaiIDList = customerRepository.findEmaiList(email, mobile, id);
		return emaiIDList;
	}

	// get All data
	public List<CustomerModel> getAllData() {
		return customerRepository.findAll();
	}

	// Save User
	public CustomerModel saveData(@RequestBody @Valid CustomerModel customerModel) {
		return customerRepository.save(customerModel);
	}

	// get customer details by ID
	public ResponseEntity<CustomerModel> getId(@PathVariable Long id) {
		CustomerModel customerModel = customerRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(customerModel);
	}

	// Update User
	public ResponseEntity<CustomerModel> updateData(@PathVariable Long id,
			@RequestBody @Valid CustomerModel customerDetails) {
		try {
			CustomerModel customerModel = customerRepository.findById(id).orElseThrow();

			customerModel.setFirstName(customerDetails.getFirstName());
			customerModel.setLastName(customerDetails.getLastName());
			customerModel.setDateOfBirth(customerDetails.getDateOfBirth());
			customerModel.setAddressOne(customerDetails.getAddressOne());
			customerModel.setAddressTwo(customerDetails.getAddressTwo());
			customerModel.setAge(customerDetails.getAge());
			customerModel.setGender(customerDetails.getGender());
			customerModel.setEmail(customerDetails.getEmail());

			customerDetails = customerRepository.save(customerModel);
		} catch (NoSuchElementException exception) {
			throw new ApiRequestException("User is not available");
		}
		return ResponseEntity.ok(customerDetails);
	}

	// Delete User
	public ResponseEntity<Map<String, Boolean>> deleteData(@PathVariable Long id) {
		Map<String, Boolean> responce = new HashMap<>();
		try {
			CustomerModel customerModel = customerRepository.findById(id).orElseThrow();
			customerRepository.delete(customerModel);
			responce.put("deleted", Boolean.TRUE);
		} catch (NoSuchElementException exception) {
			throw new ApiRequestException("User is not available");
		}
		return ResponseEntity.ok(responce);
	}

}