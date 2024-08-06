package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

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

    public List<EmployeeDto> listEmployees(String prefix) {
        return employees.stream()
                .filter(employee -> prefix == null || employee.getName().startsWith(prefix))
                .map(EmployeesService::toDto)
                .toList();
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .map(EmployeesService::toDto)
                .orElseThrow(notFound(id));
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var entity = new Employee(sequence.incrementAndGet(), employeeDto.getName());
        employees.add(entity);
        return toDto(entity);
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeDto.getId()))
                .peek(employee -> employee.setName(employeeDto.getName())) // Kerülendő: mellékhatás
                .findAny()
                .map(EmployeesService::toDto)
                .orElseThrow(notFound(employeeDto.getId()));
    }

    public static Supplier<EntityNotFoundException> notFound(long id) {
        return () -> new EntityNotFoundException("Employee not found with id " + id, Employee.class, id);
    }

    public void deleteEmployee(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }
}
