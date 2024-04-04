package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "player_tables_session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "startedAt"})
@ToString(of = {"id", "startedAt"})
public class PlayerTableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = UuidGenerator.class)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id",
            nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "table_session_id",
            nullable = false)
    private TableSession tableSession;

    @Column(name = "started_at",
            nullable = false)
    private LocalDateTime startedAt;

    public void setPlayer(Player player) {
        this.player = player;
        this.player.getPlayerTableSessions().add(this);
    }

    public void setTableSession(TableSession tableSession) {
        this.tableSession = tableSession;
        this.tableSession.getPlayerTableSessions().add(this);
    }
}
