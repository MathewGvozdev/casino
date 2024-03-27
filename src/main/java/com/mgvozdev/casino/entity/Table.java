package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Game;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "id")
    private Game game;

    @Column(name = "id",
            unique = true,
            nullable = false)
    private Integer number;

    @Column(name = "grey5000")
    private Integer grey5000;

    @Column(name = "orange1000")
    private Integer orange1000;

    @Column(name = "purple500")
    private Integer purple500;

    @Column(name = "black100")
    private Integer black100;

    @Column(name = "green25")
    private Integer green25;

    @Column(name = "yellow20")
    private Integer yellow20;

    @Column(name = "red5")
    private Integer red5;

    @Column(name = "pink2_50")
    private Integer pink2_50;

    @Column(name = "white1")
    private Integer white1;

    @Column(name = "quarter0_25")
    private Integer quarter0_25;

    //TODO MapKeyEnumerated -> private Map<Chip, Integer> rack = new HashMap<>();
}
