package com.app.bookai.customer.infrastructure.persistence.entity;

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
public class JpaCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(unique = true, name = "created_at")
    private LocalDateTime createdAt;
}
