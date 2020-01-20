package com.example.bingo.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * ユーザ Entity
 * 
 * @author takuminv
 *
 */
@Entity
@Data
@Table(name = "userAccount")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主キーとなるId(自動インクリメント)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    /**
     * ユーザ名(ログインに使用)
     */
    @NotNull
    private String userName;

    /**
     * パスワード(ログインに使用)
     */
    @NotNull
    private String password;

}
