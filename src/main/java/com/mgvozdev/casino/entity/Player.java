package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.PlayerStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type",
            nullable = false)
    private MembershipType membershipType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private PlayerStatus status;

    @Column(name = "total_deposit")
    private BigDecimal totalDeposit;

    @Column(name = "total_winnings")
    private BigDecimal totalWinnings;

    @OneToOne(mappedBy = "player")
    private Profile profile;

    @OneToMany(mappedBy = "player")
    private List<PlayerSession> playerSessions = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<Reward> rewards = new ArrayList<>();
}

