package com.automation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SpringConfiguration {
	@Primary
	@Qualifier("dataSourceUser")
	@Bean(name = "dataSourceUser")
	@ConfigurationProperties(prefix = "spring.datasource.user")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSourceBasic")
	@Qualifier("dataSourceBasic")
	@ConfigurationProperties(prefix = "spring.datasource.basic")
	public DataSource basicDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "userJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("dataSourceUser") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "basicJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("dataSourceBasic") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
}
