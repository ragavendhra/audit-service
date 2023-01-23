package com.jsw.auditSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Address")
@Immutable
public class Address {
    @Id
    @Column
    @Logger(value = "id", showData = true)
    private Long id;

    @Column(nullable = false)
    @Logger(value = "addressName", showData = true)
    private String addressName;

}
