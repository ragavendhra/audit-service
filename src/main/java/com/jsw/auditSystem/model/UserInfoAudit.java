package com.jsw.auditSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_info_audit")
@Builder
public class UserInfoAudit {

    private String id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String operation;

    private List<UserInfo> userInfoList;

}
