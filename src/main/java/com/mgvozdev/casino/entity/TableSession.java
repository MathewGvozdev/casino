package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "tables_session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "minBet", "maxBet", "openedAt", "closedAt"})
@ToString(of = {"id", "minBet", "maxBet", "openedAt", "closedAt"})
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "min_bet",
            nullable = false)
    private Integer minBet;

    @Column(name = "max_bet",
            nullable = false)
    private Integer maxBet;

    @Column(name = "opened_at",
            nullable = false)
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;

    @ManyToOne
    @JoinColumn(name = "dealer_id",
            nullable = false)
    private Dealer dealer;

    @ManyToOne
    @JoinColumn(name = "opened_by",
            nullable = false)
    private User openedBy;

    @ManyToOne
    @JoinColumn(name = "closed_by")
    private User closedBy;

    @ManyToMany(mappedBy = "tableSessions")
    private List<Player> players = new ArrayList<>();
}
