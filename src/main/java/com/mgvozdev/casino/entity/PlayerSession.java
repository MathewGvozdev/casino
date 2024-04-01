package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "player_session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"player", "sessions"})
@ToString(exclude = {"player", "sessions"})
public class PlayerSession {

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

    @Column(name = "cashed_out")
    private BigDecimal cashedOut;

    @Column(name = "avg_bet")
    private Integer avgBet;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "playerSession")
    private List<Session> sessions = new ArrayList<>();
}
