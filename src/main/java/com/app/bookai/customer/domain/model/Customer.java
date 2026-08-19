package com.app.bookai.customer.domain.model;

import com.app.bookai.shared.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String phoneNumber;
    private RoleType role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void updateNane(String newName){
        if(newName != null && !newName.isEmpty()){
            this.name = newName;
            this.updatedAt = LocalDateTime.now();
        }
    }

}
