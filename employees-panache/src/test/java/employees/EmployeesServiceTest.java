package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeesServiceTest {

    EmployeesService employeesService;

    @BeforeEach
    void createEmployeeService() {
        employeesService = new EmployeesService();
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
