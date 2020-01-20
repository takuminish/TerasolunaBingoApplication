package com.example.bingo.domain.repository.useraccount;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bingo.domain.model.UserAccount;

/**
 * UserAccount Entity用 Repository
 * 
 * @author takuminv
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    /**
     * ユーザ名に該当するUserAccountを取得する
     * 
     * @param userName
     * @return
     */
    Optional<UserAccount> findByUserName(String userName);

}
