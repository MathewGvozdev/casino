package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "table_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
@ToString(of = "id", callSuper = true)
public class TableChipSet extends ChipSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;
}
