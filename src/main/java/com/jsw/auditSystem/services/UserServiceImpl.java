package com.jsw.auditSystem.services;

import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import com.jsw.auditSystem.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    @Override
    public UserInfo createAccount(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    //implementing the following method to get all the users data from mongodb
    @Override
    public List<UserInfoAudit> getAllUsersFromMongo(){
        return userInfoMongoRepository.findAll();
    }

    @Override
    public UserInfo editUserInfo(Long id, UserInfo userInfo) {
        UserInfo user = userInfoRepository.findById(id).orElseThrow(()-> new RuntimeException("user not fount"));
        userInfo.setId(id);
        return userInfoRepository.save(userInfo);
    }
}
