package com.jsw.auditSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonEmployeeObject {

    private String id;

    private String name;

    private String role;

    private String operation;

    private String createdBy;

    private Date createdDate;
}
