package com.example.bingo.domain.service.useraccountdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.bingo.domain.model.UserAccount;

public class UserAccountDetails extends User {
	
	private static final long serialVersionUID = 1L;
	
	private final UserAccount userAccount;
	
	public UserAccountDetails(UserAccount userAccount) {
		super(userAccount.getUserName(), userAccount.getPassword(), 
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.userAccount = userAccount;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

}
