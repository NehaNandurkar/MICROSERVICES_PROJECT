package com.eazycode.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazycode.accounts.entity.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account,Integer> {

	Optional<Account> findByCustomerId(Integer customerId);

	void deleteByCustomerId(Integer customerId);

}
