package com.jsw.auditSystem.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee_info")
@Builder
public class EmployeeInfo /*extends Auditable*/{
    private String  empId;
    private Map<String, String> logElements;
}