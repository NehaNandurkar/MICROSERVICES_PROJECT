package com.eazycode.accounts.mapper;

import com.eazycode.accounts.dto.AccountDto;
import com.eazycode.accounts.entity.Account;

public class AccountsMapper {

	 public static AccountDto mapToAccountsDto(Account accounts, AccountDto accountsDto) {
	        accountsDto.setAccountNumber(accounts.getAccountNumber());
	        accountsDto.setAccountType(accounts.getAccountType());
	        accountsDto.setBranchAddress(accounts.getBranchAddress());
	        return accountsDto;
	    }

	    public static Account mapToAccounts(AccountDto accountsDto, Account accounts) {
	        accounts.setAccountNumber(accountsDto.getAccountNumber());
	        accounts.setAccountType(accountsDto.getAccountType());
	        accounts.setBranchAddress(accountsDto.getBranchAddress());
	        return accounts;
	    }
}
