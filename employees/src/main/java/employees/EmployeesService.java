package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class EmployeesService {

    private AtomicLong sequence = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(
            new ArrayList<>(List.of(
                    new Employee(sequence.incrementAndGet(), "John Doe"),
                    new Employee(sequence.incrementAndGet(), "Jane Doe")
            ))
    );

    private static EmployeeDto toDto(Employee entity) {
        return new EmployeeDto().id(entity.getId()).name(entity.getName());
    }

    public List<EmployeeDto> listEmployees() {
        return employees.stream()
                .map(EmployeesService::toDto)
                .toList();
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .map(EmployeesService::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found  + id"));
    }
}
