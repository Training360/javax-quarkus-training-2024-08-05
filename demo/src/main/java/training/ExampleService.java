package training;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;

@ApplicationScoped // Quarkus javaslata, proxyzott, lazy
public class ExampleService {

    private final ExampleConfig exampleConfig;

    private final Event<HelloEvent> event;

    public ExampleService(ExampleConfig exampleConfig, Event<HelloEvent> event) {
        Log.info("Creating ExampleService");
        this.exampleConfig = exampleConfig;
        this.event = event;
    }

    public String hello() {
        event.fire(new HelloEvent("Hello Event"));
        return exampleConfig.message();
    }
}
