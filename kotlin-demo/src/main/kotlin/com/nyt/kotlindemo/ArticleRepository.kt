package com.nyt.kotlindemo

import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long>