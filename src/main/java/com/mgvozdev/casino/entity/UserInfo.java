package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Shift;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
public class UserInfo {

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
    @Column(name = "shift")
    private Shift shift;

    @Column(name = "hired_on")
    private LocalDate hiredOn;

    @Column(name = "salary")
    private BigDecimal salary;

    @OneToOne
    @JoinColumn(name = "user_id",
            unique = true,
            nullable = false)
    private User user;
}
