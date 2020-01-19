package com.example.bingo.domain.repository.bingo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.Bingo;

public interface BingoRepository extends JpaRepository<Bingo, Long> {

    List<Bingo> findAllByBingoRoomBingoRoomId(long bingoRoomId);
}
