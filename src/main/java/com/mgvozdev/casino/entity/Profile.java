package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.DocumentType;
import com.mgvozdev.casino.entity.enums.MembershipType;
import com.mgvozdev.casino.entity.enums.ProfileStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"playerSessions", "rewards"})
@ToString(exclude = {"playerSessions", "rewards"})
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type",
            nullable = false)
    private DocumentType documentType;

    @Column(name = "country",
            nullable = false)
    private String country;

    @Column(name = "document_number",
            nullable = false)
    private String documentNumber;

    @Column(name = "first_name",
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            nullable = false)
    private String lastName;

    @Column(name = "date_of_birth",
            nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "issue_date",
            nullable = false)
    private LocalDate issueDate;

    @Column(name = "expiration_date",
            nullable = false)
    private LocalDate expirationDate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type",
            nullable = false)
    private MembershipType membershipType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private ProfileStatus status;

    @Column(name = "total_deposit")
    private BigDecimal totalDeposit;

    @Column(name = "total_winnings")
    private BigDecimal totalWinnings;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Player> playerSessions = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Reward> rewards = new ArrayList<>();
}
