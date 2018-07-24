package com.bee.sample.ch1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value={"file:conf/application.properties"})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class ch1Application {
	public static void main(String[] args) {
		SpringApplication.run(ch1Application.class, args);
	}

}
