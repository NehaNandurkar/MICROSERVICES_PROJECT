package com.eazycode.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//Creating the dto to avoid sending customer_id if someone requested Account details 

public class AccountDto {
	
	@NotEmpty(message="Account number cannot be null or empty")
	@Pattern(regexp="(^$|[0-9]{10})",message="Account number ")
    private Integer accountNumber;

	@NotEmpty(message="Account type cannot be null or empty")
    private String accountType;

	@NotEmpty(message="Branch Address cannot be null or empty")
    private String branchAddress;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "AccountDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", branchAddress="
				+ branchAddress + "]";
	}
    
    
}
