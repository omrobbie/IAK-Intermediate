package com.omrobbie.newsapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse{

	@SerializedName("sortBy")
	private String sortBy;

	@SerializedName("source")
	private String source;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	@Override
 	public String toString(){
		return 
			"APIResponse{" + 
			"sortBy = '" + sortBy + '\'' + 
			",source = '" + source + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

    public APIResponse() {
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ArticlesItem> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}