package com.example.springboot.controller;

import java.util.HashMap;

import java.util.List;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.CustomerModel;
import com.example.springboot.service.CustomerServiceimpl;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceimpl customerService;

	Boolean isresponseFalse = false;
	Boolean isresponseTrue = true;

	// get All data
	@GetMapping(path = "/", produces = "application/json")
	public List<CustomerModel> getAllData() {
		return customerService.getAllData();
	}

	// Save User
	@PostMapping("/save")
	public ResponseEntity<CustomerModel> saveupdateCustomer(@RequestBody @Valid CustomerModel customerModel) {
		CustomerModel customer = customerService.saveData(customerModel);
		return new ResponseEntity<CustomerModel>(customer, HttpStatus.CREATED);
	}

	// get customer details by ID
	@GetMapping("/{id}")
	public ResponseEntity<CustomerModel> getCustomerEntityId(@PathVariable Long id) {
		return customerService.getId(id);
	}

	// Update User
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id,
			@RequestBody @Valid CustomerModel customerDetails) {
		return customerService.updateData(id, customerDetails);
	}

	// Delete User
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id) {
		return customerService.deleteData(id);
	}

	// Unique Email Id
	@GetMapping(path = "/email/{emailPath}/{mobilePath}/{id}", produces = "application/json")
	public ResponseEntity<Map<String, Boolean>> uniqueEmailandMobile(@PathVariable String emailPath,
			@PathVariable String mobilePath, @PathVariable Long id) {
		List<CustomerModel> mobileEmailList = customerService.uniqMobileEmail(emailPath, mobilePath, id);
		Map<String, Boolean> responce = new HashMap<>();
		if (mobileEmailList.isEmpty()) {
			responce.put("User is not exist", isresponseFalse);
		} else {
			responce.put("User is exist", isresponseTrue);
		}
		return ResponseEntity.ok(responce);
	}

}