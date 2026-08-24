package com.app.bookai.treatment.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Treatment {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer durationMinutes;
    @Builder.Default
    private Boolean isActive=true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void updatePrice (BigDecimal price){
        this.price=price;
        this.updatedAt=LocalDateTime.now();
    }
}
