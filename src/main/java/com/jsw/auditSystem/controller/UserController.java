package com.jsw.auditSystem.controller;

import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
private UserService userService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserInfo> createAccount(@RequestBody UserInfo userInfo)
        {
            UserInfo saveAccount = userService.createAccount(userInfo);
            return new ResponseEntity<>(saveAccount, HttpStatus.CREATED);
         }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<UserInfo> getAllUserInfo()
    {
        return  userService.getAllUserInfo();
    }
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo updateUser(
            @PathVariable("id") Long id, @RequestBody UserInfo userInfo) {
        return userService.editUserInfo(id, userInfo);
    }
}
