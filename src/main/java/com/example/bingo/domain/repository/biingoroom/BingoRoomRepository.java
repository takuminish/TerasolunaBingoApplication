package com.example.bingo.domain.repository.biingoroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.BingoRoom;

/**
 * BingoRoom Entity用 Repository
 * 
 * @author takuminv
 *
 */
public interface BingoRoomRepository extends JpaRepository<BingoRoom, Long> {

    /**
     * 指定したユーザが登録したBingoRoomを全て取得
     * 
     * @param userId
     * @return 指定したユーザが登録したBingoRoom
     */
    List<BingoRoom> findAllByCreateUserAccountUserId(long userId);
}
