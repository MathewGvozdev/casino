package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "roles_authorities")
@Getter
@Setter
@NoArgsConstructor
public class RoleAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @Column(name = "roles_id")
    private Role role;

    @ManyToOne
    @Column(name = "authority_id")
    private Authority authority;
}
