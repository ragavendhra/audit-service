package com.jsw.auditSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Employee")
@Immutable
@Where(clause = "DELETED = 0")
public class Employee {

    @Id
    @Column
    @Logger(value = "id", showData = true)
    private Long id;

    @Column
    @Logger(value = "name", showData = true)
    private String name;

    @Column
    @Logger(value = "role", showData = true)
    private String role;

    @Column
    private Integer deleted = 0;

    // getter setter

    public void setDeleted() {
        this.deleted = 1;
    }
}