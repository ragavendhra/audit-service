package com.jsw.auditSystem.services;

import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.model.UserInfoAudit;

import java.util.List;

public interface UserService {
    UserInfo createAccount(UserInfo userInfo);

    List<UserInfo> getAllUserInfo();

    //The following method will retrieve all the users from the mongodb
    List<UserInfoAudit> getAllUsersFromMongo();

    UserInfo editUserInfo(Long id, UserInfo userInfo);
}
