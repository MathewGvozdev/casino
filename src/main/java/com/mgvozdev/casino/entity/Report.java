package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "date",
            nullable = false)
    private LocalDate date;

    @Column(name = "notes",
            nullable = false)
    private String notes;

    @Column(name = "total_drop_in")
    private BigDecimal totalDropIn;

    @Column(name = "total_winnings")
    private BigDecimal totalWinnings;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false)
    private User user;
}
