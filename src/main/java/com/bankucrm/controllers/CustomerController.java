package com.bankucrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankucrm.entities.CustomerEntity;
import com.bankucrm.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody CustomerEntity customerEntity) {
		ResponseEntity<Object> responseEntity  = customerService.createCustomer(customerEntity);
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/remove")
	public ResponseEntity<Object> remove(@RequestBody CustomerEntity customerEntity) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		
		if (customerService.removeCustomer(customerEntity.getIdentification())) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody CustomerEntity customerEntity) {
		ResponseEntity<Object> responseEntity = customerService.updateCustomer(customerEntity);
		
		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		ResponseEntity<Object> responseEntity = customerService.findAllCustomers();

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/findByidentification/{id}")
	public ResponseEntity<Object> findByidentification(@PathVariable int id) {
		ResponseEntity<Object> responseEntity = customerService.findByidentification(id);

		if (responseEntity.hasBody()) {
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}
		return responseEntity;
	}
	
}
