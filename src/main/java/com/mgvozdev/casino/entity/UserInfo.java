package com.mgvozdev.casino.entity;

import com.mgvozdev.casino.entity.enums.Shift;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id",
            unique = true,
            nullable = false)
    private User user;

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

}
