package com.example.bingo.domain.service.bingo;

import java.util.List;

import com.example.bingo.domain.model.BingoResult;
import com.example.bingo.domain.model.BingoRoom;

public interface BingoService {

    public BingoResult create(BingoResult bingo);

    public List<BingoResult> findAllByBingoRoom(BingoRoom bingoRoom);
}
