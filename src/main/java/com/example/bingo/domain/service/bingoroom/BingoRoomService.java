package com.example.bingo.domain.service.bingoroom;

import java.util.List;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;

public interface BingoRoomService {
	
	List<BingoRoom> findAllByCreateUser(UserAccount userAccount);
	
	BingoRoom findByBingoId(String bingoId);
	
	BingoRoom create(BingoRoom bingoRoom);
	
	BingoRoom Start(BingoRoom bingoRoom);
	
	BingoRoom Finish(BingoRoom bingoRoom);
	
	void delete(String bingoId);

}
