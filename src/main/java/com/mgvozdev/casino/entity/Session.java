package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @Column(name = "player_session_id",
            nullable = false)
    private PlayerSession playerSession;

    @ManyToOne
    @Column(name = "table_session_id",
            nullable = false)
    private TableSession tableSession;

    @Column(name = "started_at",
            nullable = false)
    private LocalDateTime startedAt;
}
