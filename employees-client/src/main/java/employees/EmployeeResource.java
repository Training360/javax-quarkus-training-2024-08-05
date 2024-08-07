package employees;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.Optional;

@RegisterRestClient(configKey = "employees-api")
@Path("/api/employees")
public interface EmployeeResource {

    @GET
    Response getEmployees(Optional<String> prefix);
}
