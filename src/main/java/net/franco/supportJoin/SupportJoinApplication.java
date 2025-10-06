package net.franco.supportJoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SupportJoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportJoinApplication.class, args);
	}

}
