package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

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
    @GenericGenerator(name = "UUID", type = UuidGenerator.class)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "table_id",
            nullable = false)
    private Table table;

    @ManyToOne
    @JoinColumn(name = "dealer_id",
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
    @JoinColumn(name = "closed_by")
    private User closedBy;

    @OneToMany(mappedBy = "tableSession")
    private List<PlayerTableSession> playerTableSessions = new ArrayList<>();
}
