package com.eazycode.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Creating dto to avoid sending the customer_id

public class CustomerDto {
 
		@NotEmpty(message="Name cannot be null or empty")// If name is null then this msg will be shown 
		@Size(min=3,max=30,message="The length of the customer name should be between 3 and 50")
	    private String name;
	    
		@NotEmpty(message="Email cannot be null or empty")
		@Email(message="Email address should be a valid value")
	    private String email;

		@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	    private String mobileNumber;

	    private AccountDto accountsDto;
	    
	    
		public AccountDto getAccountsDto() {
			return accountsDto;
		}

		public void setAccountsDto(AccountDto accountsDto) {
			this.accountsDto = accountsDto;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		@Override
		public String toString() {
			return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + "]";
		}
	    
	    
}
