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
@Table(name = "Ladle")
@Immutable
public class Ladle {
    @Id
    @GeneratedValue(generator = "ladle_id_seq", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Logger(value = "Employee Name", showData = true)
    private Long ladleQuantity;

    @Column(nullable = false)
    @Logger(value = "ladleLength", showData = true)
    private Long ladleLength;

    @Column(nullable = false)
    private String ladleLocation;

}
