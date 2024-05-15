package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Chip;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "player_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "chip"})
@ToString(of = {"id", "chip"})
public class PlayerChipSet {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
}
