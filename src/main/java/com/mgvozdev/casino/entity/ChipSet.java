package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"table", "playerSession"})
@ToString(exclude = {"table", "playerSession"})
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

    @ManyToOne
    private Table table;

    @ManyToOne
    private PlayerSession playerSession;
}
