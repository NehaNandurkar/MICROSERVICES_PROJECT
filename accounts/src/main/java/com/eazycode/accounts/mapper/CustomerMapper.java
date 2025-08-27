package com.eazycode.accounts.mapper;

import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.entity.Customer;

public class CustomerMapper {

	 public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
	        customerDto.setName(customer.getName());
	        customerDto.setEmail(customer.getEmail());
	        customerDto.setMobileNumber(customer.getMobileNumber());
	        return customerDto;
	    }

	    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
	        customer.setName(customerDto.getName());
	        customer.setEmail(customerDto.getEmail());
	        customer.setMobileNumber(customerDto.getMobileNumber());
	        return customer;
	    }

}
