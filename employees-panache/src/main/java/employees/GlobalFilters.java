package employees;

import io.quarkus.logging.Log;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

public class GlobalFilters {

    @ServerRequestFilter
    public void logRequestId(ContainerRequestContext context) {
        var requestId = context.getHeaderString("Request-Id");
        Log.debug("Request-Id: " + requestId);
    }
}
