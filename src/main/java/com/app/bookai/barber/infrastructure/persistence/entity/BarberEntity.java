package com.app.bookai.barber.infrastructure.persistence.entity;

import com.app.bookai.shared.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barbers_entity")
public class BarberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingHourEntity> workingHours = new ArrayList<>();
    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayOffEntity> dayOffs = new ArrayList<>();

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingHourOverrideEntity> workingHourOverrides = new ArrayList<>();
}