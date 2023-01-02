package com.jsw.auditSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@Document(collection = "user_info_audit")
public class UserInfoAudit /*extends Auditable<String>*/ {

    private Long id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String operation;

}
