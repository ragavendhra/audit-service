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

    @Autowired
    UserInfoAudit userInfoAudit;

    @Override
    public UserInfo createAccount(UserInfo userInfo) {

        //setting up the data for the userInfoAudit object
        userInfoAudit.setEmail(userInfo.getEmail());
        userInfoAudit.setPassword(userInfo.getPassword());
        userInfoAudit.setFirstname(userInfo.getFirstname());
        userInfoAudit.setLastname(userInfo.getLastname());
        userInfoAudit.setAccountSetupFinished(userInfo.isAccountSetupFinished());

        //saving the data in the mongodb collection user_info
        userInfoMongoRepository.save(userInfoAudit);

        //saving the data in the postgres and returning
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
