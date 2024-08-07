package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import training.dto.EmployeeDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceTest {

    @Mock
    EmployeesRepository employeesRepository;

    @InjectMocks
    EmployeesService employeesService;

    @Test
    void createEmployee() {
        var created = employeesService.createEmployee(new EmployeeDto().name("Jack Doe"));
        assertEquals("Jack Doe", created.getName());
    }

    @Test
    void createExistingEmployee () {
        when(employeesRepository.findEmployeeByName(any())).thenReturn(Optional.of(new Employee("Jack Doe")));
        assertThrows(IllegalArgumentException.class, () ->
            employeesService.createEmployee(new EmployeeDto().name("Jack Doe"))
        );
    }
}
