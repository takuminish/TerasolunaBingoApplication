package com.example.bingo.domain.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.bingo.domain.model.UserAccount;

public class UserDetails extends User {
	
	private static final long serialVersionUID = 1L;
	
	private final UserAccount userAccount;
	
	public UserDetails(UserAccount userAccount) {
		super(userAccount.getUserName(), userAccount.getPassword(), 
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.userAccount = userAccount;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

}
