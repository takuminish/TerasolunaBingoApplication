package com.example.bingo.domain.service.bingo;

import java.util.List;

import com.example.bingo.domain.model.BingoResult;
import com.example.bingo.domain.model.BingoRoom;

/**
 * ビンゴゲーム用 Service
 * 
 * @author takuminv
 *
 */
public interface BingoResultService {

    /**
     * ビンゴの抽選結果をDBに登録
     * 
     * @param bingo
     * @return 登録した抽選結果
     */
    public BingoResult create(BingoResult bingo);

    /**
     * bingoRoomに該当する抽選結果を全て表示
     * 
     * @param bingoRoom
     * @return bingoRoomに該当する抽選結果のリスト
     */
    public List<BingoResult> findAllByBingoRoom(BingoRoom bingoRoom);
}
