package com.cheche;

import com.cheche.config.ElasticsearchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.cheche")
@EnableAutoConfiguration(exclude = {ElasticsearchConfiguration.class})
public class SearchProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchProjectApplication.class, args);
	}
}
