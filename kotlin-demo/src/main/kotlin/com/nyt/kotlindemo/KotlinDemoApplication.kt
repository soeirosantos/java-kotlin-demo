package com.nyt.kotlindemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class KotlinDemoApplication {

	@Bean
	fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate = restTemplateBuilder.build();

	@Bean
	fun getArticles(@Value("\${top.url}") topURL: String,
					articleRepository: ArticleRepository,
					restTemplate: RestTemplate): CommandLineRunner {

		return CommandLineRunner {
			var articles = restTemplate.getForObject(topURL, ArticleList::class.java)!!.results
			articles.forEach {articleRepository.save(it)}
			articleRepository.findAll().forEach(::println)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinDemoApplication>(*args)
}