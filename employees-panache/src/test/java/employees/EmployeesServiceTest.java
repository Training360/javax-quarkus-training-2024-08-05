package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class EmployeesServiceTest {

    EmployeesService employeesService;

    @BeforeEach
    void createEmployeeService() {
        var employeesRepository = mock(EmployeesRepository.class);
        employeesService = new EmployeesService(employeesRepository);
    }

    @Test
    void createEmployee() {
        var created = employeesService.createEmployee(new EmployeeDto().name("Jack Doe"));
        assertEquals("Jack Doe", created.getName());
    }

    @Test
    void createExistingEmployee () {
        employeesService.createEmployee(new EmployeeDto().name("Jack Doe"));
        assertThrows(IllegalArgumentException.class, () ->
            employeesService.createEmployee(new EmployeeDto().name("Jack Doe"))
        );
    }
}
