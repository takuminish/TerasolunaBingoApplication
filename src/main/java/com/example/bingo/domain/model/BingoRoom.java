package com.example.bingo.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="bingoRoom")
public class BingoRoom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bingoRoomId;
	
	private String roomName;
	
	private boolean started;
	
	private boolean finished;
	
	@OneToOne
	@JoinColumn(name="createUserId", referencedColumnName = "userId")
	private UserAccount createUserAccount;
	
	
	
	
}