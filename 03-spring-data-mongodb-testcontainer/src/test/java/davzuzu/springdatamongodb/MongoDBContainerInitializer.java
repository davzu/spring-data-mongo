package davzuzu.springdatamongodb;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    {
        mongoDBContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of("spring.data.mongodb.uri" + mongoDBContainer.getReplicaSetUrl()).applyTo(applicationContext);
    }

}
