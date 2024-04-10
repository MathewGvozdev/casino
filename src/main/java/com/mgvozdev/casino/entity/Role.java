package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"users", "authorities"})
@ToString(exclude = {"users", "authorities"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title",
            unique = true,
            nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
