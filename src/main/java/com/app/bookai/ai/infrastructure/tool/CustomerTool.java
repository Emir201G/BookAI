package com.app.bookai.ai.infrastructure.tool;

import com.app.bookai.ai.infrastructure.tool.dto.customer.CustomerToolRequest;
import com.app.bookai.ai.infrastructure.tool.dto.customer.CustomerToolResponse;
import com.app.bookai.ai.infrastructure.tool.mapper.CustomerToolMapper;
import com.app.bookai.customer.application.dto.UpdateNameRequestDTO;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerTool {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final GetCustomerByPhoneNumberUseCase getCustomerByPhoneNumberUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateNameCustomerUseCase updateNameCustomerUseCase;
    private final CustomerToolMapper customerToolMapper;

    @Tool(description = """
            Crear nuevo Cliente en el sistema bookai.
            """)
    public CustomerToolResponse createCustomer(
            @ToolParam(description = """
                    Datos del cliente para poder crearlo y guardarlo en el sistema bookai.
                    """)
            CustomerToolRequest customerToolRequest
    ) {
        Customer customer = customerToolMapper.toDomainCustomer(customerToolRequest);

        Customer customerSave = createCustomerUseCase
                .createCustomer(customer);

        return customerToolMapper
                .toDomainCustomerToolResponse(customerSave);
    }

    @Tool(description = """
            Obtener toda las lista de los clientes.
            """)
    public List<CustomerToolResponse> getAllCustomers() {

        List<Customer> customers = getAllCustomerUseCase
                .getAllCustomers();

        return customerToolMapper
                .toDomainCustomerToolResponseList(customers);
    }

    @Tool(description = """
            Buscar un cliente por su numero
            """)
    public CustomerToolResponse getCustomer(
            @ToolParam(description = """
                    Numero del cliente
                    """)
            String phoneNumber
    ) {

        Customer customer = getCustomerByPhoneNumberUseCase
                .getCustomerByName(phoneNumber);

        return customerToolMapper
                .toDomainCustomerToolResponse(customer);
    }

    @Tool(description = """
            Eliminar un cliente del sistema bookai, con el numero de celular
            """)
    public void deleteCustomer(
            @ToolParam(description = """
                    Numero del cliente
                    """)
            String phoneNumber
    ) {
        deleteCustomerUseCase.delete(phoneNumber);
    }

    @Tool(description = """
            Actualizar nombre del cliente
            """)
    public CustomerToolResponse updateName(
            @ToolParam(description = """
                    Numero del cliente
                    """)
            String phoneNumber,
            @ToolParam(description = """
                    Nuevo nombre del cliente a actualizar
                    """)
            String name
    ) {

        Customer customer = updateNameCustomerUseCase
                .updateCustomer(
                        new UpdateNameRequestDTO(
                                phoneNumber,
                                name)
                );

        return customerToolMapper
                .toDomainCustomerToolResponse(customer);
    }

}
