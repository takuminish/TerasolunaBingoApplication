package com.example.bingo.app.bingoroom;

import java.io.Serializable;

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
		
	private String roomName;
	
}
