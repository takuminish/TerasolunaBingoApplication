package com.example.bingo.domain.repository.useraccount;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

	Optional<UserAccount> findByUserName(String userName);
	
}
