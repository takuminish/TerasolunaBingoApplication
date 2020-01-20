package com.example.bingo.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * ビンゴの抽選結果のEntity bingoResultテーブル
 * 
 * @author takuminv
 *
 */
@Entity
@Data
@Table(name = "bingoResult")
public class BingoResult {

    /**
     * 主キーとなるId(自動インクリメント)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bingoId;

    /**
     * 抽選結果の値(0~99)
     */
    @NotNull
    private String bingoValue;

    /**
     * 登録日時
     */
    @NotNull
    private Date createdAt;

    /**
     * 紐付くBingoRoom
     */
    @OneToOne
    @JoinColumn(name = "bingoRoomId", referencedColumnName = "bingoRoomId")
    @NotNull
    private BingoRoom bingoRoom;
}
