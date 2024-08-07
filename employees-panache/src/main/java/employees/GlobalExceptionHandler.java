package employees;

import com.tietoevry.quarkus.resteasy.problem.HttpProblem;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.net.URI;

public class GlobalExceptionHandler {

    @ServerExceptionMapper
    public Response handleException(EntityNotFoundException entityNotFoundException) {
        return Response.status(Response.Status.NOT_FOUND).entity(
                HttpProblem.builder()
                        .withTitle("Not found")
                        .withType(URI.create("errors/not-found"))
                        .withStatus(Response.Status.NOT_FOUND)
                        .withDetail(entityNotFoundException.getMessage())
                        .with("class", entityNotFoundException.getClazz().getName())
                        .with("id", entityNotFoundException.getId())
                        .build()
        )
                .header("Content-Type", "application/problem+json")
                .build();
    }
}
