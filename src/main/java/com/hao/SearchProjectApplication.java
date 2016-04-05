package com.hao;

import com.hao.config.ElasticsearchConfiguration;
import com.hao.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.hao")
@EnableAutoConfiguration(exclude = {ElasticsearchConfiguration.class})
public class SearchProjectApplication implements CommandLineRunner {

	public static final Logger logger = LoggerFactory.getLogger(SearchProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SearchProjectApplication.class, args);
	}

	@Autowired
	private LogService logService;

	@Override
	public void run(String... strings) throws Exception {
		while (true) {
			logService.generateLog();
			Thread.sleep(1000);
//			logger.info("current thread:{},content:{}",Thread.currentThread().getName(), UUID.randomUUID().toString());
		}
	}
}
