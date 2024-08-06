package employees;

import io.quarkus.logging.Log;
import jakarta.ws.rs.HeaderParam;
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
    public Response listEmployees(@HeaderParam("Request-Id") String requestId) {
        Log.debugf("Request id: %s", requestId);
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

    @Override
    public Response updateEmployee(Long id, EmployeeDto employeeDto) {
        if (!id.equals(employeeDto.getId())) {
            throw new IllegalArgumentException("Ids must be the same %d != %d".formatted(id, employeeDto.getId()));
        }
        return Response.ok(employeesService.updateEmployee(employeeDto)).build();
    }

    // ModifyEmployeeCommand - String name
    // {"name": null}
    // ModifyEmployeeCommand nem akarom
    // {"name": "John Doe"}
    // csak a módosítandókat akarom beküldeni?
    // PATCH


    @Override
    public Response deleteEmployee(Long id) {
        employeesService.deleteEmployee(id);
        return Response.noContent().build();
    }
}
