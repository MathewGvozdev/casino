package com.mgvozdev.casino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@jakarta.persistence.Table(name = "table_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "table", callSuper = true)
@ToString(exclude = "table", callSuper = true)
public class TableChipSet extends ChipSet {

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;
}
