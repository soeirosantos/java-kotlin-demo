package com.nyt.javademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    private ArticleRepository repository;
    private RestTemplate restTemplate;

    private String sentimentURL;

    public SentimentController(ArticleRepository repository,
                               RestTemplate restTemplate,
                               @Value("${sentiment.url}") String sentimentURL) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.sentimentURL = sentimentURL;
    }

    @RequestMapping("/{id}")
    public Sentiment get(@PathVariable Long id) {

        var article = repository.findById(id).get();

        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();
        parametersMap.add("text", article.getArticleAbstract());

        var sentiment = restTemplate.postForObject(sentimentURL, parametersMap, Sentiment.class);

        sentiment.setTitle(article.getTitle());

        return sentiment;

    }

    @RequestMapping("/")
    public List<Sentiment> all() {
        List<Sentiment> sentiments = new ArrayList<>();
        repository.findAll().forEach((article -> {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", article.getArticleAbstract());

            Sentiment sentiment = restTemplate.postForObject(sentimentURL, params, Sentiment.class);
            sentiment.setTitle(article.getTitle());
            sentiments.add(sentiment);
        }));

        return sentiments;
    }
}
