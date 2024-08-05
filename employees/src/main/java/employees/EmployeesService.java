package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EmployeesService {

    private List<EmployeeDto> employees = Collections.synchronizedList(
            new ArrayList<>(List.of(
                    new EmployeeDto().id(1L).name("John Doe"),
                    new EmployeeDto().id(2L).name("Jane Doe")
            ))
    );

    public List<EmployeeDto> listEmployees() {
        return employees;
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found  + id"));
    }
}
