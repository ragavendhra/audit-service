package com.jsw.auditSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee_info")
@Builder
public class EmployeeInfo {
    private Long empId;
    private Map<String, String> logElements;
}