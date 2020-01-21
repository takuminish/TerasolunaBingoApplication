package com.example.bingo.domain.service.useraccount;

import com.example.bingo.domain.model.UserAccount;

/**
 * ユーザ用 サービスクラス
 * 
 * @author takuminv
 *
 */
public interface UserAccountSharedService {

    UserAccount findOne(String userName);

}
