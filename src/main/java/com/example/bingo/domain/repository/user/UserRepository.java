package com.example.bingo.domain.repository.user;

import java.util.Optional;

import com.example.bingo.domain.model.User;

public interface UserRepository {

	Optional<User> findByUserName(String userName);
	
}
