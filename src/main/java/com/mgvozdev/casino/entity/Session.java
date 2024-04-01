package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "startedAt"})
@ToString(of = {"id", "startedAt"})
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

    public void setPlayerSession(PlayerSession playerSession) {
        this.playerSession = playerSession;
        this.playerSession.getSessions().add(this);
    }

    public void setTableSession(TableSession tableSession) {
        this.tableSession = tableSession;
        this.tableSession.getSessions().add(this);
    }
}
