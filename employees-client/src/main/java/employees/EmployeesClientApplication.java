package employees;

import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.core.GenericType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@QuarkusMain
public class EmployeesClientApplication implements QuarkusApplication {

    private final EmployeeResource employeeResource;

    public EmployeesClientApplication(@RestClient EmployeeResource employeeResource) {
        this.employeeResource = employeeResource;
    }

    @Override
    public int run(String... args) throws Exception {
        Log.debug("Application started");

        var employees = employeeResource.getEmployees(Optional.empty());

        Log.debugf("Employees: %s", employees.getStatus());

        Log.debugf("Employees: %s", employees.readEntity(new GenericType<List<Employee>>(){}));

        return 0;
    }
}
