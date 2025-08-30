package com.eazycode.accounts.service;

import org.springframework.http.ResponseEntity;

import com.eazycode.accounts.dto.CustomerDto;

public interface IAccountsService {

	 void createAccountForCustomer(CustomerDto customerDto);

	 CustomerDto fetchAccountDetails(String mobileNumber);

	 Boolean updateAccount(CustomerDto customerDto);

	 Boolean deleteAcountDetails(String mobileNumber);

}
