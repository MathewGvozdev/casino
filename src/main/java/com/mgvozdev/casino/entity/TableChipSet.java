package com.mgvozdev.casino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@jakarta.persistence.Table(name = "table_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "table")
@ToString(exclude = "table")
public class TableChipSet extends ChipSet {

    @ManyToOne
    private Table table;
}
