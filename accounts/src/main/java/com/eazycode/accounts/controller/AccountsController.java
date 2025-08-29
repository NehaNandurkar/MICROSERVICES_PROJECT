package com.eazycode.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.service.IAccountsService;

@RestController
@RequestMapping(path="/api")
public class AccountsController {
	
	@Autowired
	IAccountsService iAccountsService;
	
	@PostMapping("/create")
	public ResponseEntity<String>createAccountForCustomer(@RequestBody CustomerDto customerDto){
		iAccountsService.createAccountForCustomer(customerDto);
		return  new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
               
    } 
	
	@GetMapping("/fetch/{mobileNumber}")
	public ResponseEntity<CustomerDto>fetchAccountDetails(@PathVariable String mobileNumber){
		return new ResponseEntity<>(iAccountsService.fetchAccountDetails(mobileNumber),HttpStatus.OK);
	}


}
