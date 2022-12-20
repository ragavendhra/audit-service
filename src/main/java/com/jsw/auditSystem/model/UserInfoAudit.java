package com.jsw.auditSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "user_info_audit")
public class UserInfoAudit {

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private boolean accountSetupFinished;
}
