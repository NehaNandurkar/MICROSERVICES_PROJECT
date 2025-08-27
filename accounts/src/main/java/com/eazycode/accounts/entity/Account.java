package com.eazycode.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")    
public class Account extends BaseEntity{

    @Id
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

	public Account() {
		super();
	}

	public Account(Integer accountNumber, Integer customerId, String accountType, String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}
	
	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
		return "Account [accountNumber=" + accountNumber + ", customerId=" + customerId + ", accountType=" + accountType
				+ ", branchAddress=" + branchAddress + "]";
	}
    
    
}

