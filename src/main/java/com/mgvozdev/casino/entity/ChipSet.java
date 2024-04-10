package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChipSet {

    @Enumerated(EnumType.STRING)
    @Column(name = "chip",
            nullable = false)
    private Chip chip;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private BigDecimal total;
}
