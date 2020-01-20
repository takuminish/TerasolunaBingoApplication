package com.example.bingo.domain.repository.bingo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.BingoResult;

/**
 * BingoResult Entity用 Repository
 * 
 * @author takuminv
 *
 */

public interface BingoResultRepository extends JpaRepository<BingoResult, Long> {

    /**
     * 指定したBingoRoomに紐付く全てのBingoResultを取得
     * 
     * @param bingoRoomId
     * @return 指定したBingoRoomに紐付く全てのBingoResult
     */
    List<BingoResult> findAllByBingoRoomBingoRoomId(long bingoRoomId);
}
