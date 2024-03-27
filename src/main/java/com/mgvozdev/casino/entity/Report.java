package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id",
            nullable = false)
    private User user;

    @Column(name = "id",
            nullable = false)
    private LocalDate date;

    @Column(name = "id",
            nullable = false)
    private String notes;

    @Column(name = "id")
    private BigDecimal totalDropIn;

    @Column(name = "id")
    private BigDecimal totalWinnings;
}
