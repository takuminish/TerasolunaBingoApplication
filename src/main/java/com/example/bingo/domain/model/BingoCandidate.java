package com.example.bingo.domain.model;

import lombok.Data;

@Data
public class BingoCandidate {

    private String bingoValue;

    private boolean resulted;

    public BingoCandidate(String bingoValue, boolean resulted) {
        super();
        this.bingoValue = bingoValue;
        this.resulted = resulted;
    }

}
