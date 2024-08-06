package employees;

import jakarta.ws.rs.core.Response;
import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.net.URI;

public class EmployeesResource implements EmployeesApi {

    private final EmployeesService employeesService;

    public EmployeesResource(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public Response listEmployees() {
        return Response.ok(employeesService.listEmployees()).build();
    }

    @Override
    public Response findEmployeeById(Long id) {
        return Response.ok(employeesService.findEmployeeById(id)).build();
    }

    @Override
    public Response createEmployee(EmployeeDto employeeDto) {
        var created = employeesService.createEmployee(employeeDto);
        return Response.created(URI.create("/api/employees/%d".formatted(created.getId()))).entity(created).build();
    }
}
