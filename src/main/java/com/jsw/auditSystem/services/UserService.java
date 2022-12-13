package com.jsw.auditSystem.services;

import com.jsw.auditSystem.model.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo createAccount(UserInfo userInfo);

    List<UserInfo> getAllUserInfo();

    UserInfo editUserInfo(Long id, UserInfo userInfo);
}
