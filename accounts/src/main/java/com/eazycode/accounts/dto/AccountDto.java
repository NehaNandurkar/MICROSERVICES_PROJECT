package com.eazycode.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//Creating the dto to avoid sending customer_id if someone requested Account details 
//@Schema Provides metadata for Swagger UI for schemas at the bottom 

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDto {
	
	@Schema(
            description = "Account Number of  Bank account", example = "3454433243"
    )
	@NotEmpty(message="Account number cannot be null or empty")
	@Pattern(regexp="(^$|[0-9]{10})",message="Account number ")
    private Integer accountNumber;

	@Schema(
            description = "Account type of  Bank account", example = "Savings"
    )
	@NotEmpty(message="Account type cannot be null or empty")
    private String accountType;

	 @Schema(
	            description = "Bank branch address", example = "123 NewYork"
	    )
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
