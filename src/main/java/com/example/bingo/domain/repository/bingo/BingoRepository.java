package com.example.bingo.domain.repository.bingo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.BingoResult;

public interface BingoRepository extends JpaRepository<BingoResult, Long> {

    List<BingoResult> findAllByBingoRoomBingoRoomId(long bingoRoomId);
}
