package com.nyt.javademo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sentiment {
    private String label;
    private String title;

    public Sentiment(String label) {
        this.label = label;
    }

    public Sentiment() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sentiment{");
        sb.append("label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
