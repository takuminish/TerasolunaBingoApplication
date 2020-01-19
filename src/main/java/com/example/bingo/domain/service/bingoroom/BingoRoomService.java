package com.example.bingo.domain.service.bingoroom;

import java.util.List;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;

public interface BingoRoomService {

    List<BingoRoom> findAllByCreateUser(UserAccount userAccount);

    BingoRoom findByBingoRoomId(long bingoId);

    BingoRoom create(BingoRoom bingoRoom);

    BingoRoom update(BingoRoom bingoRoom);

    BingoRoom Start(Long bingoRoomId);

    BingoRoom Finish(Long bingoRoomId);

    void delete(long bingoRoomId);

}
