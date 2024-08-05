package training;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // Quarkus javaslata, proxyzott, lazy
public class ExampleService {

    public String hello() {
        return "Hello World";
    }
}
