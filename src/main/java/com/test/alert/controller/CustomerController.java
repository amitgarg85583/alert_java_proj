package com.test.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.alert.dao.Customer;
import com.test.alert.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService custSvc;

	@GetMapping("/hello")
	public ResponseEntity<Customer> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
		Customer customer = custSvc.getCustomer(name);
		if (customer.name != null)
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		else
			return new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
	}
}