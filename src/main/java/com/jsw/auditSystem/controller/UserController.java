package com.jsw.auditSystem.controller;

import com.jsw.auditSystem.model.Response;
import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        public ResponseEntity<Response<UserInfo>> createAccount(@RequestBody UserInfo userInfo)
        {
            UserInfo saveAccount = userService.createAccount(userInfo);
            return ResponseEntity.ok(Response.of(saveAccount));
         }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Response<List<UserInfo>>> getAllUserInfo()
    {
        return ResponseEntity.ok(Response.of(userService.getAllUserInfo()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/get-audit-data")
    public ResponseEntity<Response<List<UserInfoAudit>>> getAllAuditData(){
        return ResponseEntity.ok(Response.of(userService.getAllUsersFromMongo()));
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserInfo>> updateUser(
            @PathVariable("id") Long id, @RequestBody UserInfo userInfo) {
       UserInfo user = userService.editUserInfo(id, userInfo);
    return ResponseEntity.ok(Response.of(user));
    }
}
