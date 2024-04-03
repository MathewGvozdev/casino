package com.mgvozdev.casino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "player_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "player", callSuper = false)
@ToString(exclude = "player")
public class PlayerChipSet extends ChipSet {

    @ManyToOne
    private Player player;
}
