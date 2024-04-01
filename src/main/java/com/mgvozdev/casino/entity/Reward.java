package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.RewardStatus;
import com.mgvozdev.casino.entity.enums.RewardType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reward")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"player", "user"})
@ToString(exclude = {"player", "user"})
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",
            nullable = false)
    private RewardType type;

    @Column(name = "given_at",
            nullable = false)
    private LocalDateTime givenAt;

    @Column(name = "expires_at",
            nullable = false)
    private LocalDate expiresAt;

    @Column(name = "redeemed_at")
    private LocalDateTime redeemedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private RewardStatus status;

    @ManyToOne
    @JoinColumn(name = "player_id",
            nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false)
    private User user;
}
