package com.nyt.kotlindemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/sentiment")
class SentimentController(val articleRepository: ArticleRepository,
                          val restTemplate: RestTemplate,
                          @Value("\${sentiment.url}") val sentimentURL: String) {

    @RequestMapping("{id}")
    fun get(@PathVariable id: Long): Sentiment {

        val article = articleRepository.findById(id).get()

        val params = LinkedMultiValueMap<String, String>()
        params["text"] = article.articleAbstract

        val sentiment = restTemplate.postForObject(sentimentURL, params, Sentiment::class.java)
        sentiment!!.title = article.title

        return sentiment
    }

    @RequestMapping("/")
    fun all(): List<Sentiment> {
        var sentiments = mutableListOf<Sentiment>()

        articleRepository.findAll().forEach {
            val params = LinkedMultiValueMap<String, String>()
            params["text"] = it.articleAbstract

            var sentiment = restTemplate.postForObject(sentimentURL, params, Sentiment::class.java)
            sentiment!!.title = it.title

            sentiments.add(sentiment)
        }

        return sentiments
    }
}