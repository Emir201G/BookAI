package com.app.bookai.customer.infrastructure.persistence.entity;

import com.app.bookai.shared.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "customers"
)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = false, name = "phone_number", nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column(unique = true, name = "created_at")
    private LocalDateTime createdAt;

    @Column(unique = true, name = "update_at")
    private LocalDateTime updatedAt;
}
