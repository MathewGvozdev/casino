package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "player")
@ToString(exclude = "player")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

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

    @Column(name = "document_image",
            unique = true,
            nullable = false)
    private String documentImage;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "player_id",
            unique = true,
            nullable = false)
    private Player player;
}
