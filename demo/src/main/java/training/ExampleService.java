package training;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // Quarkus javaslata, proxyzott, lazy
public class ExampleService {

    private final ExampleConfig exampleConfig;

    public ExampleService(ExampleConfig exampleConfig) {
        this.exampleConfig = exampleConfig;
    }

    public String hello() {
        return exampleConfig.message();
    }
}
