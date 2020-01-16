package com.example.bingo.domain.service.useraccountdetails;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.service.useraccount.UserAccountSharedService;

@Service
public class UserAccountDetailsService implements UserDetailsService {

	@Inject
	UserAccountSharedService userAccountSharedService;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
		    UserAccount userAccount = userAccountSharedService.findOne(username);
		    return new UserAccountDetails(userAccount);
		} catch(ResourceNotFoundException e) {
			throw new UsernameNotFoundException("user NotFOund", e);
		}
	}

}
