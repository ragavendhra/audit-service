package com.jsw.auditSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonAddressObject {

    private String id;

    private String addressName;

    private String operation;

    private String createdBy;

    private String createdDate;

}
