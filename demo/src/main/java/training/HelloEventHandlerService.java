package training;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class HelloEventHandlerService {

    public void handle(@Observes HelloEvent event) {
            Log.debugf("Received HelloEvent: %s", event.toString());
    }
}
