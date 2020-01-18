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

@Entity
@Data
@Table(name="bingoRoom")
public class BingoRoom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bingoRoomId;
	
	@NotNull
	private String roomName;
	
	@NotNull
	private boolean started;
	
	@NotNull
	private boolean finished;
	
	@NotNull
	private Date createdAt;
	
	@NotNull
	private Date updatedAt;
	
	@OneToOne
	@JoinColumn(name="createUserId", referencedColumnName = "userId")
	private UserAccount createUserAccount;
	
}
