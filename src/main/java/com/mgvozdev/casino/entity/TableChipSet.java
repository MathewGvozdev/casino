package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "table_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "table")
@ToString(exclude = "table")
public class TableChipSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "chip",
            nullable = false)
    protected Chip chip;

    @Column(name = "amount")
    protected Integer amount;

    @Column(name = "total")
    protected BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;
}
