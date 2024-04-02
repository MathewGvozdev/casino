package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChipSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "chip",
            nullable = false)
    private Chip chip;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private BigDecimal total;
}
