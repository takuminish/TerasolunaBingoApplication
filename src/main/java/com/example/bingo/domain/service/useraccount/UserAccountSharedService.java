package com.example.bingo.domain.service.useraccount;

import com.example.bingo.domain.model.UserAccount;

public interface UserAccountSharedService {

    UserAccount findOne(String userName);

}
