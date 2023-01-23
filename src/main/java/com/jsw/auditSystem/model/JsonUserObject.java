package com.jsw.auditSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonUserObject {

    private String id;

    private String operation;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private boolean isAccountFinished;

    private String createdBy;

    private String createdDate;
}
