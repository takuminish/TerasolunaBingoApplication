package com.example.bingo.domain.service.bingoroom;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.biingoroom.BingoRoomRepository;

@Service
public class BingoRoomServiceImpl implements BingoRoomService {

	@Inject
	BingoRoomRepository bingoRoomRepository;
	
	@Override
	public List<BingoRoom> findAllByCreateUser(UserAccount userAccount) {
		List<BingoRoom> bingoRoomList = bingoRoomRepository.findAllByCreateUserAccountUserId(userAccount.getUserId());
		return bingoRoomList;
	}

	@Override
	public BingoRoom findByBingoRoomId(long bingoRoomId) {
		BingoRoom bingoRoom = bingoRoomRepository.findById(bingoRoomId).orElse(null);
		
		// BingoRoomが見つからなかったらException
		if (bingoRoom == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage
					.fromText("[E404] BingoRoom is NotFound"));
			throw new ResourceNotFoundException(messages);
		}
		return bingoRoom;
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
