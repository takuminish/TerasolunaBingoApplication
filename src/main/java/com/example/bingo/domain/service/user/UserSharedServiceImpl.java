package com.example.bingo.domain.service.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.bingo.domain.model.User;
import com.example.bingo.domain.repository.user.UserRepository;

@Service
public class UserSharedServiceImpl implements UserSharedService {

	@Inject
	UserRepository userRepository;
	
	@Transactional(readOnly=true)
	@Override
	public User findOne(String userName) {
		
		User user = userRepository.findByUserName(userName).orElse(null);
		
		if(user == null) {
			throw new ResourceNotFoundException("User Not Found. userName=" + userName);
		}
		
		return user;
	}

}
