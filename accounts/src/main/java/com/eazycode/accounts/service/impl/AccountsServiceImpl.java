package com.eazycode.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.entity.Account;
import com.eazycode.accounts.entity.Customer;
import com.eazycode.accounts.mapper.CustomerMapper;
import com.eazycode.accounts.repository.AccountsRepository;
import com.eazycode.accounts.repository.CustomerRepository;
import com.eazycode.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountsRepository  accountsRepository;

	@Override
	 public void createAccountForCustomer(CustomerDto customerDto) { 
		 Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		 Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		    if (existingCustomer.isPresent()) {
		        throw new RuntimeException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists.");
		    }
		    customer.setCreatedAt(LocalDateTime.now());
		    customer.setCreatedBy("Anonymous");
		 Customer savedCustomer=customerRepository.save(customer);
		 accountsRepository.save(createNewAccount(savedCustomer));
		 
		
	}
	
	private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
     // Generate a random 9-digit number (always within int range)
        Integer randomAccNumber = 100_000_000 + new Random().nextInt(900_000_000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType("SAVINGS");
        newAccount.setBranchAddress("123 Main Street, New York");
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        
        return newAccount;
    }


}
