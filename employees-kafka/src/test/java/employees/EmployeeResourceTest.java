package employees;

import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class EmployeeResourceTest {

//    @BeforeEach
//    public void deleteAllEmployees() {
//        employeesService.clearAll();
//    }

    @Inject
    EmployeesRepository employeesRepository;

    @BeforeEach
    @Transactional
    void deleteEmployees() {
        employeesRepository.deleteAll();
    }

    @Test
    void createEmployee() {
        var name = "John Doe " + UUID.randomUUID();
        var response = given()
                .contentType(ContentType.JSON)
                .body(new EmployeeDto().name(name))
                .when()
                .post("/api/employees")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().body().as(EmployeeDto.class);

        assertNotNull(response.getId());
        assertEquals(name, response.getName());
    }

    @Test
    void listEmployees() {

        createEmployees();

        List<EmployeeDto> employees = given()
                .when()
                .get("/api/employees")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(new TypeRef<>() {});

//        assertLinesMatch(List.of("John Doe", "Jane Doe"),
//            employees.stream().map(EmployeeDto::getName).toList());

//        assertEquals(List.of("John Doe", "Jane Doe"),
//                employees.stream().map(EmployeeDto::getName).toList());

        assertThat(employees)
                .extracting(EmployeeDto::getName)
                .containsExactly("John Doe", "Jane Doe");
    }

    @Transactional
    void createEmployees() {
        employeesRepository.persist(new Employee("John Doe"), new Employee("Jane Doe"));
    }
}
