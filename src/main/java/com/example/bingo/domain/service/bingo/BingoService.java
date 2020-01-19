package com.example.bingo.domain.service.bingo;

import java.util.List;

import com.example.bingo.domain.model.Bingo;
import com.example.bingo.domain.model.BingoRoom;

public interface BingoService {

    public Bingo create(Bingo bingo);

    public List<Bingo> findAllByBingoRoom(BingoRoom bingoRoom);
}
