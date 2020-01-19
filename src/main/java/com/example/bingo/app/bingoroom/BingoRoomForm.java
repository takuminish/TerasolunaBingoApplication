package com.example.bingo.app.bingoroom;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BingoRoomForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public static interface BingoRoomCreate {

    };

    public static interface BingoRoomUpdate {

    }

    public static interface BingoRoomDelete {

    }

    @Size(min = 1, groups = { BingoRoomCreate.class, BingoRoomUpdate.class })
    @NotNull(groups = { BingoRoomCreate.class, BingoRoomUpdate.class })
    private String roomName;

}
