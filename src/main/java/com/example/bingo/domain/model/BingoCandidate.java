package com.example.bingo.domain.model;

import lombok.Data;

/**
 * ビンゴの抽選候補クラス
 * 
 * @author takuminv
 *
 */
@Data
public class BingoCandidate {

    /**
     * ビンゴの結果となる値(0~99)
     */
    private String bingoValue;

    /**
     * すでに抽選結果として出ているか
     */
    private boolean resulted;

    /**
     * コンストラクタ
     * 
     * @param bingoValue
     * @param resulted
     */
    public BingoCandidate(String bingoValue, boolean resulted) {
        super();
        this.bingoValue = bingoValue;
        this.resulted = resulted;
    }

}
