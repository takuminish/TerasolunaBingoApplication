package com.example.bingo.domain.service.bingo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bingo.domain.model.Bingo;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.repository.bingo.BingoRepository;

@Service
@Transactional
public class BingoServiceImpl implements BingoService {

	@Inject
	BingoRepository bingoRepository;
	
	@Override
	public Bingo create(Bingo bingo) {
		bingoRepository.save(bingo);
		return bingo;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Bingo> findAllByBingoRoom(BingoRoom bingoRoom) {
		List<Bingo> bingoList = bingoRepository.findAllByBingoRoomBingoRoomId(bingoRoom.getBingoRoomId());
		return bingoList;
	}

}
