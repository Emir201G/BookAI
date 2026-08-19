package com.app.bookai.barber.domain.model;

import com.app.bookai.shared.enums.RoleType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Barber {
    private Long id;
    private String name;
    private String phoneNumber;
    private RoleType role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Builder.Default
    private Boolean isActive = true;
    private List<WorkingHour> workingHours;
    private List<DayOff> dayOffs;
    private List<WorkingHourOverride> workingHourOverrides;


    void updateBarber(Barber barber) {
        if (barber != null ) {
            this.isActive = barber.isActive;
            this.name = barber.getName();
            this.phoneNumber = barber.getPhoneNumber();
            this.role = barber.getRole();
            this.workingHours = barber.getWorkingHours();
            this.dayOffs = barber.getDayOffs();
            this.workingHours = barber.getWorkingHours();
            this.updatedAt = LocalDateTime.now();
        }
    }
}
