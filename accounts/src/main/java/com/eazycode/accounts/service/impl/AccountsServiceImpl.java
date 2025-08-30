package com.eazycode.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eazycode.accounts.constants.AccountsConstants;
import com.eazycode.accounts.dto.AccountDto;
import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.entity.Account;
import com.eazycode.accounts.entity.Customer;
import com.eazycode.accounts.exception.CustomerAlreadyExistsException;
import com.eazycode.accounts.exception.ResourceNotFoundException;
import com.eazycode.accounts.mapper.AccountsMapper;
import com.eazycode.accounts.mapper.CustomerMapper;
import com.eazycode.accounts.repository.AccountsRepository;
import com.eazycode.accounts.repository.CustomerRepository;
import com.eazycode.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountsRepository accountsRepository;

	@Override
	public void createAccountForCustomer(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
		}
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Anonymous");
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));

	}

	private Account createNewAccount(Customer customer) {
		Account newAccount = new Account();
		newAccount.setCustomerId(customer.getCustomerId());
		// Generate a random 9-digit number (always within int range)
		Integer randomAccNumber = 100_000_000 + new Random().nextInt(900_000_000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCreatedBy("Anonymous");

		return newAccount;
	}

	@Override
	public CustomerDto fetchAccountDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		 Account accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
	                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
	        );
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountDto()));

		return customerDto;
	}


	@Override
	public Boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null) {
        	Account accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
        			() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));
        	
        	AccountsMapper.mapToAccounts(accountsDto, accounts);
        	accounts.setUpdatedAt(LocalDateTime.now());
        	accounts.setUpdatedBy("Anonymous");
        	accounts=accountsRepository.save(accounts);
        	
        	Integer customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("Anonymous");
            customerRepository.save(customer);
            isUpdated = true;
        }
        

		return isUpdated;
		
	}

	@Override
	@Transactional
	public Boolean deleteAcountDetails(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		 accountsRepository.deleteByCustomerId(customer.getCustomerId());
	     customerRepository.deleteById(customer.getCustomerId());

	    return true;
	}
	

}
