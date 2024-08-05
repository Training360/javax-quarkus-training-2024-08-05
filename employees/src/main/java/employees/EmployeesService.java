package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.List;

@ApplicationScoped
public class EmployeesService {

    public List<EmployeeDto> listEmployees() {
        return List.of(
                new EmployeeDto().id(1L).name("John Doe"),
                new EmployeeDto().id(2L).name("Jane Doe")
        );
    }
}
