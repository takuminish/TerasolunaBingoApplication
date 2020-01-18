package com.example.bingo.domain.service.bingoroom;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.biingoroom.BingoRoomRepository;

public class BingoRoomServiceImpl implements BingoRoomService {

	@Inject
	BingoRoomRepository bingoRoomRepository;
	
	@Override
	public List<BingoRoom> findAllByCreateUser(UserAccount userAccount) {
		List<BingoRoom> bingoRoomList = bingoRoomRepository.findAllByCreateUserAccountUserId(userAccount.getUserId());
		return bingoRoomList;
	}

	@Override
	public BingoRoom findByBingoId(String bingoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BingoRoom create(BingoRoom bingoRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BingoRoom Start(BingoRoom bingoRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BingoRoom Finish(BingoRoom bingoRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String bingoId) {
		// TODO Auto-generated method stub

	}

}
