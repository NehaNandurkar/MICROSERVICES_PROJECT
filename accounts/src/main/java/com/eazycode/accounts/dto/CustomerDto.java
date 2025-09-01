package com.eazycode.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Creating dto to avoid sending the customer_id

//@Schema Provides metadata for Swagger UI for schemas at the bottom 
@Schema(
		name="Customer",
		description="Schema to hold Customer and Account information")
public class CustomerDto {
 
		//@Schema For Swagger UI schema description and example for each field
		@Schema(
				description="Name of the customer",example="Alex Jorden")
		@NotEmpty(message="Name cannot be null or empty")// If name is null then this msg will be shown 
		@Size(min=3,max=30,message="The length of the customer name should be between 3 and 50")
	    private String name;
	    
		@Schema(
	            description = "Email address of the customer", example = "alex@gmail.com"
	    )
		@NotEmpty(message="Email cannot be null or empty")
		@Email(message="Email address should be a valid value")
	    private String email;

		@Schema(
	            description = "Mobile Number of the customer", example = "9345432123"
	    )
		@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	    private String mobileNumber;

		@Schema(
	            description = "Account details of the Customer"
	    )
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
