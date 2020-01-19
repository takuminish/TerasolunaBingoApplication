package com.example.bingo.domain.repository.biingoroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.BingoRoom;

public interface BingoRoomRepository extends JpaRepository<BingoRoom, Long> {

    List<BingoRoom> findAllByCreateUserAccountUserId(long userId);
}
