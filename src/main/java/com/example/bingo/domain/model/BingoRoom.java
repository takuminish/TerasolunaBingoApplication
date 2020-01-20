package com.example.bingo.domain.model;

import java.io.Serializable;
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
 * ビンゴルームのEntity bingoRoomテーブル
 * 
 * @author takuminv
 *
 */
@Entity
@Data
@Table(name = "bingoRoom")
public class BingoRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主キーとなるId(自動インクリメント)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bingoRoomId;

    /**
     * ルーム名
     */
    @NotNull
    private String roomName;

    /**
     * ゲーム開始済みか
     */
    @NotNull
    private boolean started;

    /**
     * ゲーム終了済みか
     */
    @NotNull
    private boolean finished;

    /**
     * 登録日時
     */
    @NotNull
    private Date createdAt;

    /**
     * 更新日時
     */
    @NotNull
    private Date updatedAt;

    /**
     * 紐付くユーザ
     */
    @OneToOne
    @JoinColumn(name = "createUserId", referencedColumnName = "userId")
    private UserAccount createUserAccount;

}
