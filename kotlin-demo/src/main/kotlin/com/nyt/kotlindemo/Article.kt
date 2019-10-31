package com.nyt.kotlindemo

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Article(@Id @GeneratedValue var id: Long? = null,
                   var title: String,
                   @JsonProperty("abstract") var articleAbstract: String)