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


    public void updateName(String newName) {
        if (newName != null) {
            this.name = newName;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        if (newPhoneNumber != null) {
            this.phoneNumber = newPhoneNumber;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void updateRole(RoleType newRole) {
        if (newRole != null) {
            this.role = newRole;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void updateWorkingHours(List<WorkingHour> newWorkingHours) {
        if (newWorkingHours != null) {
            this.workingHours = newWorkingHours;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void updateWorkingHoursOverride(List<WorkingHourOverride> newWorkingHourOverrides) {
        if (newWorkingHourOverrides != null) {
            this.workingHourOverrides = newWorkingHourOverrides;
            this.updatedAt = LocalDateTime.now();
        }
    }

}
