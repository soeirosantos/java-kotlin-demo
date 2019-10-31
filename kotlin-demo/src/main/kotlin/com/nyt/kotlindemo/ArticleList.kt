package com.nyt.kotlindemo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ArticleList(val results: List<Article>)