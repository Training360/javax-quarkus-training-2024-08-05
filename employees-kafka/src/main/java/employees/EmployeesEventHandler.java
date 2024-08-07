package employees;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EmployeesEventHandler {

    @Incoming("incoming-employees-events")
    public void handle(EmployeeHasBeenCreatedEvent event) {
        Log.debugf("Received event: %s", event.toString());
    }
}
