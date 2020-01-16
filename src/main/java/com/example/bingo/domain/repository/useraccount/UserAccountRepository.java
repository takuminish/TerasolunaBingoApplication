package com.example.bingo.domain.repository.useraccount;

import java.util.Optional;

import com.example.bingo.domain.model.UserAccount;

public interface UserAccountRepository {

	Optional<UserAccount> findByUserName(String userName);
	
}
