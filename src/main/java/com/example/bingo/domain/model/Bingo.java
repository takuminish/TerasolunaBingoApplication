package com.example.bingo.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Bingo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bingoId;

    @NotNull
    String bingoValue;

    @OneToOne
    @JoinColumn(name = "bingoRoomId", referencedColumnName = "bingoRoomId")
    @NotNull
    BingoRoom bingoRoom;
}
