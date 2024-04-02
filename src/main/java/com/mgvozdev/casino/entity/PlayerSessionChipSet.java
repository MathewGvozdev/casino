package com.mgvozdev.casino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "player_session_chip_set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "playerSession")
@ToString(exclude = "playerSession")
public class PlayerSessionChipSet extends ChipSet {

    @ManyToOne
    private PlayerSession playerSession;
}
