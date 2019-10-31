package com.nyt.javademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class JavaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDemoApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	CommandLineRunner getArticles(@Value("${top.url}") String topURL,
								  ArticleRepository articleRepository,
								  RestTemplate restTemplate) {
		return args -> {
			List<Article> articles = restTemplate.getForObject(topURL, ArticleList.class).getResults();
			articles.forEach(articleRepository::save);
			articleRepository.findAll().forEach(System.out::println);
		};
	}

}
