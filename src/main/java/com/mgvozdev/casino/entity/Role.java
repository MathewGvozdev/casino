package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "roleAuthorities")
@ToString(exclude = "roleAuthorities")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title",
            unique = true,
            nullable = false)
    private String title;

    @OneToMany(mappedBy = "role")
    private List<RoleAuthority> roleAuthorities = new ArrayList<>();
}
