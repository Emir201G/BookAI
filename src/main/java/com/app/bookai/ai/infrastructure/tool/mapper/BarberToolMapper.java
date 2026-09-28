package com.app.bookai.ai.infrastructure.tool.mapper;

import com.app.bookai.ai.infrastructure.tool.dto.barber.*;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarberToolMapper {

    @Mapping(target = "success", constant = "true")
    @Mapping(target = "message", constant = "Operación realizada correctamente.")
    BarberToolResponse toBarberToolResponse(Barber barber);
    @Mapping(target = "success", constant = "true")
    @Mapping(target = "message", constant = "Operación realizada correctamente.")
    List<BarberToolResponse> toBarberToolResponses(List<Barber> barbers);

    WorkingHour toDomainWorkingHour(WorkingHourToolRequest request);
    Barber toDomainBarber(BarberToolRequest request);
    DayOff toDomainDayOff(DayOffToolRequest request);
    WorkingHourOverride toDomainWorkingHourOverride(WorkingHourOverrideToolRequest request);
    List<WorkingHoursToolResponse> toWorkingHoursResponses(List<WorkingHour> workingHours);
    List<DayOffToolResponse> toDayOffResponses(List<DayOff> dayOffs);
    List<WorkingHourOverrideToolResponse> toWorkingHourOverridesResponses(List<WorkingHourOverride> workingHourOverrides);
    List<WorkingHour> toDomainWorkingHours(List<WorkingHourToolRequest> workingHourToolRequests);
    DayOffToolResponse toDayOffToolResponse(DayOff dayOff);
    WorkingHourOverrideToolResponse toWorkingHourOverrideToolResponse(WorkingHourOverride  workingHourOverride);
}
