package com.nyt.javademo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleList {
    private List<Article> results;

    public ArticleList(List<Article> results) {
        this.results = List.copyOf(results);
    }

    public ArticleList() {
    }

    public List<Article> getResults() {
        return List.copyOf(results);
    }
}