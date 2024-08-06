package employees;

import jakarta.ws.rs.core.Response;
import training.api.EmployeesApi;

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
}
