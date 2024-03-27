package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "table_sessions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"table", "dealer", "openedBy", "closedBy", "sessions"})
@ToString(exclude = {"table", "dealer", "openedBy", "closedBy", "sessions"})
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;

    @ManyToOne
    @Column(name = "dealer_id",
            nullable = false)
    private Dealer dealer;

    @Column(name = "min_bet",
            nullable = false)
    private Integer minBet;

    @Column(name = "max_bet",
            nullable = false)
    private Integer maxBet;

    @Column(name = "opened_at",
            nullable = false)
    private LocalDateTime openedAt;

    @ManyToOne
    @JoinColumn(name = "opened_by",
            nullable = false)
    private User openedBy;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @ManyToOne
    @Column(name = "closed_by")
    private User closedBy;

    @OneToMany(mappedBy = "tableSession")
    private List<Session> sessions = new ArrayList<>();
}
