package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "player_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
@ToString(of = "id", callSuper = true)
public class PlayerChipSet extends ChipSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
