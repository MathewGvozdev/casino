package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.DealerStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "dealers")
@Getter
@Setter
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name",
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private DealerStatus status;
}
