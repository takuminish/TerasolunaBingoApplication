package com.example.bingo.domain.service.user;

import com.example.bingo.domain.model.User;

public interface UserSharedService {

	User findOne(String userName);
	
}
