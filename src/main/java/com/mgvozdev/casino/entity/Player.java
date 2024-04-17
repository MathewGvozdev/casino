package com.mgvozdev.casino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"profile", "tableSessions", "chips"})
@ToString(exclude = {"profile", "tableSessions", "chips"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "player_table_session",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "table_session_id"))
    private List<TableSession> tableSessions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "player")
    private Set<PlayerChipSet> chips = new HashSet<>();
}

