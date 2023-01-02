package com.jsw.auditSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "AddressInfo")
public class AddressInfo /*extends Auditable<String> */{

    private Long id;

    private String addressName;

    private String operation;

}
