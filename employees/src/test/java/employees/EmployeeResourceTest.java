package employees;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class EmployeeResourceTest {

//    @Inject
    @InjectMock
    EmployeesService employeesService;

//    @BeforeEach
//    public void deleteAllEmployees() {
//        employeesService.clearAll();
//    }

    @Test
    void createEmployee() {
        when(employeesService.createEmployee(any())).thenReturn(new EmployeeDto().id(1L).name("John Doe"));

        var response = given()
                .contentType(ContentType.JSON)
                .body(new EmployeeDto().name("John Doe"))
                .when()
                .post("/api/employees")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().body().as(EmployeeDto.class);

        assertNotNull(response.getId());
        assertEquals("John Doe", response.getName());
    }

    @Test
    void listEmployees() {
        when(employeesService.listEmployees(any())).thenReturn(
                List.of(
                        new EmployeeDto().id(1L).name("John Doe"),
                        new EmployeeDto().id(2L).name("Jane Doe")
                )
        );

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
}
