package com.nyt.kotlindemo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sentiment(var label: String, var title: String?)