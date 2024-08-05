package training;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "demo")
public interface ExampleConfig {

    String message();
}
