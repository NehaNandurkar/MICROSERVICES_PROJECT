package com.eazycode.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path="/api")
@Validated
/**
 *You put @NotNull, @Email, etc. on entity/DTO fields → these are just rules written on the class.
 *But rules don’t run automatically.
 *@Validated (or @Valid) on the controller tells Spring: “Hey, before this request goes into the method, actually apply those rules and throw an error if they fail.”
 *@Valid Works only for basic bean validation annotations (@NotNull, @Email, @Size, etc.).
 *@Validated Does everything @Valid does + adds support for validation groups (advanced feature where you can validate differently in different scenarios).
 */
public class AccountsController {
	
	@Autowired
	IAccountsService iAccountsService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>createAccountForCustomer(@Valid @RequestBody CustomerDto customerDto){
		iAccountsService.createAccountForCustomer(customerDto);
		return  ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.MESSAGE_201,AccountsConstants.MESSAGE_201));
               
    } 
	
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message="Mobile number must be 10 digits") String mobileNumber){
		CustomerDto customerDto=iAccountsService.fetchAccountDetails(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(customerDto);
	}
	

	@PutMapping("/update")
	//They can change any of the data except account number
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
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
	public ResponseEntity<ResponseDto> deleteAcountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message="Mobile number must be 10 digits") String mobileNumber){
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
