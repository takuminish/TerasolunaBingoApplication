package com.example.bingo.app.bingo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * ビンゴゲーム画面用 Form
 * 
 * @author takuminv
 *
 */
@Data
public class BingoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 抽選結果の値(0~99)
     */
    @Min(0)
    @Max(99)
    @NotNull
    private String bingoValue;

    /**
     * 該当するbingoRoomのID
     */
    @NotNull
    private Long bingoRoomId;

}
