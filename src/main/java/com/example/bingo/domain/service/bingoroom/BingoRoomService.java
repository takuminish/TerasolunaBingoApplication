package com.example.bingo.domain.service.bingoroom;

import java.util.List;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;

/**
 * ビンゴルーム用 サービスクラス
 * 
 * @author takuminv
 *
 */
public interface BingoRoomService {

    /**
     * 指定したユーザが作成したBingoRoomを全て取得
     * 
     * @param userAccount
     * @return ユーザが作成した全てのBingoRoom
     */
    List<BingoRoom> findAllByCreateUser(UserAccount userAccount);

    /**
     * Idに該当するBingoRoomを取得
     * 
     * @param bingoId
     * @return
     */
    BingoRoom findByBingoRoomId(long bingoId);

    /**
     * BingoRoomをDBに登録
     * 
     * @param bingoRoom
     * @return 登録したBingoRoom
     */
    BingoRoom create(BingoRoom bingoRoom);

    /**
     * BingoRoomを更新
     * 
     * @param bingoRoom
     * @return 更新したBingoRoom
     */
    BingoRoom update(BingoRoom bingoRoom);

    /**
     * 指定したBingoRoomのゲームを開始する
     * 
     * @param bingoRoomId
     * @return 開始したBingoRoom
     */
    BingoRoom Start(Long bingoRoomId);

    /**
     * 指定したBingoRoomを終了する
     * 
     * @param bingoRoomId
     * @return 終了したBingoRoom
     */
    BingoRoom Finish(Long bingoRoomId);

    /**
     * Idで指定したBingoRoomを削除する
     * 
     * @param bingoRoomId
     */
    void delete(long bingoRoomId);

}
