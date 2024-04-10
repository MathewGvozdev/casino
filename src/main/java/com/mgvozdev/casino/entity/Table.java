package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Game;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "chips")
@ToString(exclude = "chips")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "game")
    private Game game;

    @Column(name = "number",
            unique = true,
            nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "table")
    private Set<TableChipSet> chips = new HashSet<>();
}
