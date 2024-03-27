package com.mgvozdev.casino.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "roles_authorities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
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

    public void setRole(Role role) {
        this.role = role;
        this.role.getRoleAuthorities().add(this);
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
        this.authority.getRoleAuthorities().add(this);
    }
}
