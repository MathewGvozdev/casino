package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"profile", "playerTableSessions", "chips"})
@ToString(exclude = {"profile", "playerTableSessions", "chips"})
public class Player {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = UuidGenerator.class)
    @Column(name = "id")
    private UUID id;

    @Column(name = "opened_at",
            nullable = false)
    private LocalDateTime openedAt;

    @Column(name = "buy_in",
            nullable = false)
    private BigDecimal buyIn;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "avg_bet")
    private Integer avgBet;

    @ManyToOne
    @JoinColumn(name = "profile_id",
            nullable = false)
    private Profile profile;

    @OneToMany(mappedBy = "player")
    private List<PlayerTableSession> playerTableSessions = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private Set<PlayerChipSet> chips = new HashSet<>();
}

