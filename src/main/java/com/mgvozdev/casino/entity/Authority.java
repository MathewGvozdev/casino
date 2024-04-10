package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "permission",
            unique = true,
            nullable = false)
    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();
}
