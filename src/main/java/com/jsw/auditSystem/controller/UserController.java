package com.jsw.auditSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsw.auditSystem.model.JsonUserObject;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import com.jsw.auditSystem.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    @GetMapping("/users/{id}")
    public UserInfo getUser(@PathVariable("id") long id){
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }

    @Logger("User updated.")
    @PutMapping("/update-user/{id}")
    public UserInfo updateUser(@PathVariable("id") long id, @RequestBody UserInfo newUser){
        return userInfoRepository.findById(id)
                .map(user -> {
                    user.setFirstname(newUser.getFirstname());
                    user.setLastname(newUser.getLastname());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setAccountSetupFinished(newUser.isAccountSetupFinished());
                    return userInfoRepository.save(newUser);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userInfoRepository.save(newUser);
                });
    }


    @Logger("User created.")
    @PostMapping("/user")
    public UserInfo createUser(@RequestBody UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

    @GetMapping("/users/mongo/{id}")
    List<JsonUserObject> getByIdFromMongo(@PathVariable("id") String id){
        List<UserInfoAudit> infoUser = userInfoMongoRepository.findByUserId(id);
        List<Map<String, String>> listOfMaps = new ArrayList<>();

        for(UserInfoAudit userInfoAudit : infoUser){
            listOfMaps.add(userInfoAudit.getLogElements());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonUserObject> jsonUserObjectsList = listOfMaps.stream().map(map1 ->
                objectMapper.convertValue(map1, JsonUserObject.class)).collect(Collectors.toList());

        return jsonUserObjectsList;
    }
}
