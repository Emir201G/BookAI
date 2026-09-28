package com.app.bookai.ai.infrastructure.tool;

import com.app.bookai.ai.infrastructure.tool.dto.barber.*;
import com.app.bookai.ai.infrastructure.tool.mapper.BarberToolMapper;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;



@Component
@RequiredArgsConstructor
public class BarberTool {

    private final CreateBarberUseCase createBarberUseCase;
    private final AddWorkingHourUseCase addWorkingHourUseCase;
    private final AddDayOffUseCase addDayOffUseCase;
    private final AddWorkingHourOverrideUseCase addWorkingHourOverrideUseCase;
    private final GetAllBarbersUseCase getAllBarbersUseCase;
    private final GetBarberByNameUseCase getBarberByNameUseCase;
    private final GetWorkingHoursByBarberUseCase getWorkingHourOfBarberUseCase;
    private final GetDayOffsByBarberUseCase getDayOffsByBarberUseCase;
    private final GetWorkingHourOverridesByBarberUseCase getWorkingHourOverridesByBarberUseCase;
    private final UpdateNameUseCase updateNameUseCase;
    private final UpdateWorkingHourUseCase updateWorkingHourUseCase;
    private final UpdateDayOffUseCase updateDayOffUseCase;
    private final UpdateWorkingHourOverrideUseCase updateWorkingHourOverrideUseCase;
    private final DeleteBarberUseCase deleteBarberUseCase;
    private final BarberToolMapper barberToolMapper;


    @Tool(description = """
            Registra un nuevo barbero en BookAi.
            Utiliza esta herramienta cuando el usuario solicite crear, registrar o agregar un nuevo barbero.
            El nombre y el número de teléfono son obligatorios.
            No utilices esta herramienta para modificar un barbero existente.
            """)
    public BarberToolResponse createBarber(
            @ToolParam(description = """
                    Datos del nuevo barbero.
                    Debe contener el nombre y el número de teléfono del barbero.
                    """)
            BarberToolRequest barberRequest
    ) {
        Barber barber = barberToolMapper
                .toDomainBarber(barberRequest);

        Barber createdBarber = createBarberUseCase
                .createBarber(barber);

        return barberToolMapper
                .toBarberToolResponse(createdBarber);
    }


    @Tool(description = """
            Agrega un horario de trabajo semanal a un barbero.
            Utiliza esta herramienta cuando el usuario quiera agregar un nuevo día y horario
            de trabajo sin reemplazar los horarios semanales que el barbero ya tiene configurados.
            El horario corresponde a un día de la semana y una hora de inicio y finalización.
            """)
    public BarberToolResponse addWorkingHour(
            @ToolParam(description = """
                    Nombre exacto del barbero al que se le agregará el horario.
                    Debe corresponder a un barbero existente en BookAi.
                    """)
            String name,

            @ToolParam(description = """
                    Horario de trabajo que se desea agregar.
                    Debe indicar el día de la semana, la hora de inicio y la hora de finalización.
                    """)
            WorkingHourToolRequest createWorkingHourRequest
    ) {
        WorkingHour workingHour = barberToolMapper
                .toDomainWorkingHour(createWorkingHourRequest);

        Barber updateBarber = addWorkingHourUseCase
                .addWorkingHour(
                        name,
                        workingHour
                );

        return barberToolMapper
                .toBarberToolResponse(updateBarber);
    }


    @Tool(description = """
            Registra un día libre para un barbero.
            Utiliza esta herramienta cuando el usuario indique que un barbero no trabajará
            en una fecha específica.
            """)
    public BarberToolResponse addDayOff(
            @ToolParam(description = """
                    Nombre exacto del barbero al que se le asignará el día libre.
                    Debe corresponder a un barbero existente.
                    """)
            String name,

            @ToolParam(description = """
                    Fecha y datos del día libre que se desea registrar.
                    """)
            DayOffToolRequest createDayOff
    ) {
        DayOff dayOff = barberToolMapper
                .toDomainDayOff(createDayOff);

        Barber barber = addDayOffUseCase
                .addDayOff(
                        name,
                        dayOff
                );

        return barberToolMapper
                .toBarberToolResponse(barber);
    }


    @Tool(description = """
            Registra una excepción al horario semanal habitual de un barbero para una fecha específica.
            Utiliza esta herramienta cuando el horario de un día concreto sea diferente al horario semanal normal.
            """)
    public BarberToolResponse addWorkingHourOverride(
            @ToolParam(description = """
                    Nombre exacto del barbero al que se aplicará la excepción de horario.
                    """)
            String name,

            @ToolParam(description = """
                    Excepción de horario que se desea registrar.
                    Debe indicar la fecha específica y el horario correspondiente.
                    """)
            WorkingHourOverrideToolRequest workingHourOverrideRequest
    ) {
        WorkingHourOverride override = barberToolMapper
                .toDomainWorkingHourOverride(
                        workingHourOverrideRequest
                );

        Barber barber = addWorkingHourOverrideUseCase
                .addWorkingHourOverride(
                        name,
                        override
                );

        return barberToolMapper
                .toBarberToolResponse(barber);
    }


    @Tool(description = """
            Obtiene todos los barberos registrados en BookAi.
            Utiliza esta herramienta cuando el usuario solicite consultar, listar o conocer
            todos los barberos disponibles.
            """)
    public List<BarberToolResponse> getAllBarbers() {
        List<Barber> barbers = getAllBarbersUseCase
                .getAllBarbers();

        return barberToolMapper
                .toBarberToolResponses(barbers);
    }


    @Tool(description = """
            Obtiene todos los horarios de trabajo semanales configurados para un barbero.
            Utiliza esta herramienta cuando el usuario quiera conocer los días y horarios
            habituales en los que trabaja un barbero.
            No devuelve días libres ni excepciones de fechas específicas.
            """)
    public List<WorkingHoursToolResponse> getWorkingHourOfBarber(
            @ToolParam(description = """
                    Nombre exacto del barbero cuyos horarios semanales se desean consultar.
                    """)
            String name
    ) {
        List<WorkingHour> workingHours = getWorkingHourOfBarberUseCase
                .getWorkingHourOfBarber(name);

        return barberToolMapper
                .toWorkingHoursResponses(workingHours);
    }


    @Tool(description = """
            Obtiene los días libres registrados para un barbero.
            Utiliza esta herramienta cuando el usuario quiera consultar las fechas
            en las que el barbero no trabajará.
            """)
    public List<DayOffToolResponse> getDayOffTheBarber(
            @ToolParam(description = """
                    Nombre exacto del barbero cuyos días libres se desean consultar.
                    """)
            String name
    ) {
        List<DayOff> dayOff = getDayOffsByBarberUseCase
                .getDayOffsByBarber(name);

        return barberToolMapper
                .toDayOffResponses(dayOff);
    }


    @Tool(description = """
            Obtiene las excepciones de horario registradas para un barbero.
            Utiliza esta herramienta cuando el usuario quiera consultar cambios de horario
            aplicados a fechas específicas.
            """)
    public List<WorkingHourOverrideToolResponse> getWorkingHourOverrideUseCase(
            @ToolParam(description = """
                    Nombre exacto del barbero cuyas excepciones de horario se desean consultar.
                    """)
            String name
    ) {
        List<WorkingHourOverride> workingHourOverrides =
                getWorkingHourOverridesByBarberUseCase
                        .getWorkingHourOverridesByBarber(name);

        return barberToolMapper
                .toWorkingHourOverridesResponses(workingHourOverrides);
    }


    @Tool(description = """
            Actualiza el nombre de un barbero existente.
            Utiliza esta herramienta únicamente cuando el usuario solicite cambiar
            o corregir el nombre de un barbero.
            """)
    public BarberToolResponse updateBarberName(
            @ToolParam(description = """
                    Nombre actual y exacto del barbero que se desea modificar.
                    """)
            String name,

            @ToolParam(description = """
                    Nuevo nombre que tendrá el barbero.
                    """)
            String newName
    ) {
        Barber barber = updateNameUseCase
                .updateName(name, newName);

        return barberToolMapper
                .toBarberToolResponse(barber);
    }


    @Tool(description = """
            Elimina un barbero existente de BookAi.
            Utiliza esta herramienta únicamente cuando el usuario solicite explícitamente
            eliminar o borrar un barbero.
            No utilices esta herramienta para modificar sus horarios.
            """)
    public void deleteBarber(
            @ToolParam(description = """
                    Nombre exacto del barbero que se desea eliminar.
                    """)
            String name
    ) {
        Barber barber = getBarberByNameUseCase
                .getBarberByName(name);

        deleteBarberUseCase.delete(barber);
    }


    @Tool(description = """
            Reemplaza la configuración completa de horarios semanales de un barbero.
            Utiliza esta herramienta cuando el usuario solicite actualizar, cambiar o modificar
            sus horarios habituales y proporcione la nueva configuración de días y horarios.
            
            La lista recibida representa la configuración FINAL de horarios del barbero.
            Los horarios que no estén incluidos en la nueva lista dejarán de formar parte
            de su horario semanal.
            
            Puede contener varios horarios para un mismo día cuando el barbero tenga
            diferentes franjas horarias durante ese día.
            """)
    public List<WorkingHoursToolResponse> updateWorkingHours(
            @ToolParam(description = """
                    Nombre exacto del barbero cuyos horarios semanales se desean actualizar.
                    Debe corresponder a un barbero existente.
                    """)
            String name,

            @ToolParam(description = """
                    Nueva configuración completa de horarios semanales del barbero.
                    Cada elemento debe indicar el día de la semana, la hora de inicio
                    y la hora de finalización.
                    
                    Esta lista representa los horarios FINALES que debe tener el barbero.
                    No incluya días en los que el barbero no trabajará.
                    Puede incluir más de un horario para el mismo día.
                    """)
            List<WorkingHourToolRequest> workingHourToolRequests
    ) {
        List<WorkingHour> newWorkingHours = barberToolMapper
                .toDomainWorkingHours(workingHourToolRequests);

        List<WorkingHour> workingHour = updateWorkingHourUseCase
                .update(
                        name,
                        newWorkingHours
                );

        return barberToolMapper
                .toWorkingHoursResponses(workingHour);
    }


    @Tool(description = """
            Actualiza un día libre existente de un barbero.
            Utiliza esta herramienta cuando el usuario quiera modificar los datos
            de un día libre que ya está registrado.
            """)
    public DayOffToolResponse updateDayOffs(
            @ToolParam(description = """
                    Nombre exacto del barbero cuyo día libre se desea modificar.
                    """)
            String name,

            @ToolParam(description = """
                    Fecha actual del día libre que se desea localizar y modificar.
                    """)
            LocalDate date,

            @ToolParam(description = """
                    Nuevos datos que tendrá el día libre después de la actualización.
                    """)
            DayOffToolRequest dayOffToolRequest
    ) {
        DayOff newDayOff = barberToolMapper
                .toDomainDayOff(dayOffToolRequest);

        DayOff dayOff = updateDayOffUseCase
                .updateDayOff(
                        name,
                        date,
                        newDayOff
                );

        return barberToolMapper
                .toDayOffToolResponse(dayOff);
    }


    @Tool(description = """
            Actualiza una excepción de horario existente para un barbero en una fecha específica.
            Utiliza esta herramienta cuando el usuario quiera modificar un cambio de horario
            que ya está registrado para una fecha determinada.
            """)
    public WorkingHourOverrideToolResponse updateWorkingHourOverride(
            @ToolParam(description = """
                    Nombre exacto del barbero cuya excepción de horario se desea modificar.
                    """)
            String name,

            @ToolParam(description = """
                    Fecha actual de la excepción de horario que se desea localizar y modificar.
                    """)
            LocalDate date,

            @ToolParam(description = """
                    Nuevos datos de horario que tendrá la excepción después de la actualización.
                    """)
            WorkingHourOverrideToolRequest workingHourOverrideToolRequest
    ) {
        WorkingHourOverride newOverride = barberToolMapper
                .toDomainWorkingHourOverride(
                        workingHourOverrideToolRequest
                );

        WorkingHourOverride workingHourOverride =
                updateWorkingHourOverrideUseCase
                        .updateWorkingHourOverride(
                                date,
                                name,
                                newOverride
                        );

        return barberToolMapper
                .toWorkingHourOverrideToolResponse(workingHourOverride);
    }


}