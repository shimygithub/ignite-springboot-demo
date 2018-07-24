package com.bee.sample.ch1.dataconfig;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration

public class MybatisDatasourceConfig {

	@MapperScan(basePackages = { "com.bee.sample.ch1.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory1")
	public class MybatisDBConfig {

		@Autowired
		@Qualifier("mysqldatsource1")
		private DataSource mysqldatsource1;

		@Bean
		public SqlSessionFactory sqlSessionFactory1() throws Exception {
			SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
			factoryBean.setDataSource(mysqldatsource1); // 使用本地据源, 连接本地的库

			return factoryBean.getObject();

		}

		@Bean("sqlSessionTemplate")
		public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
			SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
			return template;
		}
	}

}
