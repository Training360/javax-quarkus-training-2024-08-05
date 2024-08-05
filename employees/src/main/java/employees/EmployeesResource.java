package employees;

import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.util.List;

public class EmployeesResource implements EmployeesApi {

    private final EmployeesService employeesService;

    public EmployeesResource(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public List<EmployeeDto> listEmployees() {
        return employeesService.listEmployees();
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        return employeesService.findEmployeeById(id);
    }
}
