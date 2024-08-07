package employees;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

@ApplicationScoped
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    private final MutinyEmitter<EmployeeHasBeenCreatedEvent> eventEmitter;

    public EmployeesService(EmployeesRepository employeesRepository,
                            @Channel("employees-events")
                            MutinyEmitter<EmployeeHasBeenCreatedEvent> eventEmitter) {
        this.employeesRepository = employeesRepository;
        this.eventEmitter = eventEmitter;
    }

    private static EmployeeDto toDto(Employee entity) {
        return new EmployeeDto().id(entity.getId()).name(entity.getName());
    }

    public List<EmployeeDto> listEmployees(String prefix) {
        return employeesRepository.findEmployeesByPrefix(prefix)
                .map(EmployeesService::toDto)
                .toList();
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employeesRepository.findByIdOptional(id)
                .map(EmployeesService::toDto)
                .orElseThrow(notFound(id));
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var existingEmployeeWithName = employeesRepository.findEmployeeByName(employeeDto.getName());
        if (existingEmployeeWithName.isPresent()) {
            throw new IllegalArgumentException("Employee with name %s already exists".formatted(employeeDto.getName()));
        }

        var entity = new Employee(employeeDto.getName());
        employeesRepository.persist(entity);

        eventEmitter
                .sendAndAwait(new EmployeeHasBeenCreatedEvent("Employee with name %s created".formatted(employeeDto.getName())));

        return toDto(entity);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        var entity = employeesRepository.findByIdOptional(employeeDto.getId())
                .orElseThrow(notFound(employeeDto.getId()));
        entity.setName(employeeDto.getName());
        return toDto(entity);
    }

    public static Supplier<EntityNotFoundException> notFound(long id) {
        return () -> new EntityNotFoundException("Employee not found with id " + id, Employee.class, id);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }
}
