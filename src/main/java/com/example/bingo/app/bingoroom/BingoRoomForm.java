package com.example.bingo.app.bingoroom;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ビンゴルーム用Form
 * 
 * @author takuminv
 *
 */
@Data
public class BingoRoomForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * BingoRoom作成時のForm
     * 
     * @author takuminv
     *
     */
    public static interface BingoRoomCreate {

    };

    /**
     * BingoRoom更新時のForm
     * 
     * @author takuminv
     *
     */
    public static interface BingoRoomUpdate {

    }

    /**
     * BingoRoom削除時のForm
     * 
     * @author takuminv
     *
     */
    public static interface BingoRoomDelete {

    }

    /**
     * ルーム名
     */
    @Size(min = 1, groups = { BingoRoomCreate.class, BingoRoomUpdate.class })
    @NotNull(groups = { BingoRoomCreate.class, BingoRoomUpdate.class })
    private String roomName;

}
