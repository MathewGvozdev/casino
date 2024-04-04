package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.DealerStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dealer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "tableSessions")
@ToString(exclude = "tableSessions")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = UuidGenerator.class)
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

    @OneToMany(mappedBy = "dealer")
    private List<TableSession> tableSessions = new ArrayList<>();
}
