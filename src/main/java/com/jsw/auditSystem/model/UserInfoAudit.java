package com.jsw.auditSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "user_info")
public class UserInfoAudit {

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private boolean accountSetupFinished;
}
