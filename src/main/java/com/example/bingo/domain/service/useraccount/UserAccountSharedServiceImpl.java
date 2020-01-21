package com.example.bingo.domain.service.useraccount;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.useraccount.UserAccountRepository;

/**
 * ユーザ用 サービス実装クラス
 * 
 * @author takuminv
 *
 */
@Service
public class UserAccountSharedServiceImpl implements UserAccountSharedService {

    @Inject
    UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserAccount findOne(String userName) {

        UserAccount userAccount = userAccountRepository.findByUserName(userName).orElse(null);

        if (userAccount == null) {
            throw new ResourceNotFoundException("User Not Found. userName=" + userName);
        }

        return userAccount;
    }

}
