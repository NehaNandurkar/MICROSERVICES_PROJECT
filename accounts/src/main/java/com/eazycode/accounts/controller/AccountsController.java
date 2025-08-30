package com.eazycode.accounts.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazycode.accounts.constants.AccountsConstants;
import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.dto.ResponseDto;
import com.eazycode.accounts.service.IAccountsService;

@RestController
@RequestMapping(path="/api")
public class AccountsController {
	
	@Autowired
	IAccountsService iAccountsService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>createAccountForCustomer(@RequestBody CustomerDto customerDto){
		iAccountsService.createAccountForCustomer(customerDto);
		return  ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.MESSAGE_201,AccountsConstants.MESSAGE_201));
               
    } 
	
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam String mobileNumber){
		CustomerDto customerDto=iAccountsService.fetchAccountDetails(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(customerDto);
	}
	

	@PutMapping("/update")
	//They can change any of the data except account number
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        Boolean isUpdated=iAccountsService.updateAccount(customerDto);
        
        if(isUpdated) {
        	return ResponseEntity
        			.status(HttpStatus.OK)
        			.body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.MESSAGE_200));
        }else {
        	 return ResponseEntity
                     .status(HttpStatus.EXPECTATION_FAILED)
                     .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
        
    }
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAcountDetails(@RequestParam String mobileNumber){
		 Boolean isDeleted=iAccountsService.deleteAcountDetails(mobileNumber);
		 
		 if(isDeleted) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
	        }else {
	        	 return ResponseEntity
	                     .status(HttpStatus.EXPECTATION_FAILED)
	                     .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	        }
	}
	

}
