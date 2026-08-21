package com.app.bookai.barber.infrastructure.controller;

import com.app.bookai.barber.application.dto.*;
import com.app.bookai.barber.application.mapper.BarberMapper;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/barbers")
public class BarberController {

    private final CreateBarberUseCase createBarberUseCase;
    private final GetBarberByPhoneNumberUseCase getBarberByPhoneNumberUseCase;
    private final DeleteBarberUseCase deleteBarberUseCase;
    private final GetAllBarbersUseCase getAllBarbersUseCase;
    private final CreateDayOffUseCase createDayOffUseCase;
    private final UpdateWorkingHourUseCase updateWorkingHourUseCase;
    private final UpdateDayOffUseCase updateDayOffUseCase;
    private final UpdateNameUseCase updateNameUseCase;
    private final BarberMapper barberMapper;

    @PostMapping("/create")
    public ResponseEntity<BarberResponseDTO> createBarber(@RequestBody CreateBarberRequestDTO requestDTO) {

        Barber barber = barberMapper.toDomainDayOffs(requestDTO);
        createBarberUseCase.createBarber(barber);

        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

    @GetMapping("/barber/{phone-number}")
    public ResponseEntity<BarberResponseDTO> getBarberByPhoneNumber(@PathVariable("phone-number") String parameter) {
        Barber barber = getBarberByPhoneNumberUseCase.getBarberByPhoneNumber(parameter);
        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

    @DeleteMapping("delete/{phone-number}")
    public ResponseEntity<?> deleteBarber(@PathVariable("phone-number") String parameter) {

        Barber barber = getBarberByPhoneNumberUseCase.getBarberByPhoneNumber(parameter);
        deleteBarberUseCase.delete(barber);
        return ResponseEntity.ok("delete barber");
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BarberResponseDTO>> getAllBarbers() {
        List<Barber> barbers = getAllBarbersUseCase.getAllBarbers();
        return ResponseEntity.ok(barberMapper.toResponseDTO(barbers));
    }

    @PostMapping("/create/day-offs/{name}")
    public ResponseEntity<BarberResponseDTO> createDayOffs(
            @PathVariable String name,
            @RequestBody List<CreateDayOffDTO> createDayOffDTOS
    ) {

        List<DayOff> dayOffs = barberMapper.toDomainDayOffs(createDayOffDTOS);
        Barber barber = createDayOffUseCase.createDayOff(dayOffs, name);
        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

    @PostMapping("/update/working-hours/{name}")
    public ResponseEntity<BarberResponseDTO> updateWorkingHours(
            @PathVariable String name,
            @RequestBody List<WorkingHourRequestDTO> workingHourDTOS
    ) {
        List<WorkingHour> workingHours = barberMapper.toDomainWorkingHours(workingHourDTOS);
        Barber barber = updateWorkingHourUseCase.update(name, workingHours);
        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

    @PostMapping("/update/day-off/{name}/{date}")
    public ResponseEntity<BarberResponseDTO> updateDayOff(
            @PathVariable String name,
            @PathVariable LocalDate date,
            @RequestBody DayOff dayOff
    ) {
        Barber barber = updateDayOffUseCase.updateDayOff(name, date, dayOff);
        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

    @PostMapping("/update/name/{name}/{newName}")
    public ResponseEntity<BarberResponseDTO> updateName(@PathVariable String name,@PathVariable String newName) {
        Barber barber = updateNameUseCase.updateName(name, newName);
        return ResponseEntity.ok(barberMapper.toDTO(barber));
    }

}
