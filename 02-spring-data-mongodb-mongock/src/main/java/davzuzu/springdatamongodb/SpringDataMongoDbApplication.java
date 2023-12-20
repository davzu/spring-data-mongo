package davzuzu.springdatamongodb;

import davzuzu.springdatamongodb.migration.SecondChangeDatabase;
import io.mongock.runner.springboot.EnableMongock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class SpringDataMongoDbApplication {

	static Logger logger = LoggerFactory.getLogger(SecondChangeDatabase.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoDbApplication.class, args);
	}

}