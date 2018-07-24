package com.bee.sample.ch1.dataconfig;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class DatasourceConfig {

	@Bean(name="mysqldatsource1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource() {
		
		return DataSourceBuilder.create().build();
		
	}
}
