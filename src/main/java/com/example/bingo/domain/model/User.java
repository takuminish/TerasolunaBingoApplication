package com.example.bingo.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int userId;
	
	private String userName;
	
	private String password;
		
}
