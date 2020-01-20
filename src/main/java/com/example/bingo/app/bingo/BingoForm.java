package com.example.bingo.app.bingo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BingoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Min(0)
    @Max(99)
    @NotNull
    private String bingoValue;

    @NotNull
    private Long bingoRoomId;

}
