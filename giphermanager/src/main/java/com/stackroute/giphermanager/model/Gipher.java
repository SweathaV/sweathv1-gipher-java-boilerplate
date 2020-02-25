package com.stackroute.giphermanager.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Gipher {
	
	@Id
	private String gipherId;
	private String embedURL;
	private String bookMarkedBy;
	private String favouritedBy;
	private String recommendBy;

	private String userId;
	
	public Gipher(){}

	public Gipher(String gipherId, String embedURL, String bookMarkedBy, String favouritedBy, String userId, String recommendBy) {
		this.gipherId = gipherId;
		this.embedURL = embedURL;
		this.bookMarkedBy = bookMarkedBy;
		this.favouritedBy = favouritedBy;
		this.recommendBy = recommendBy;
		this.userId = userId;
	}

	public String getGipherId() {
		return gipherId;
	}

	public void setGipherId(String gipherId) {
		this.gipherId = gipherId;
	}

	public String getEmbedURL() {
		return embedURL;
	}

	public void setEmbedURL(String embedURL) {
		this.embedURL = embedURL;
	}

	public String getBookMarkedBy() {
		return bookMarkedBy;
	}

	public void setBookMarkedBy(String bookMarkedBy) {
		this.bookMarkedBy = bookMarkedBy;
	}

	public String getFavouritedBy() {
		return favouritedBy;
	}

	public void setFavouritedBy(String favouritedBy) {
		this.favouritedBy = favouritedBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public String getRecommendBy() {
		return recommendBy;
	}

	public void setRecommendBy(String recommendBy) {
		this.recommendBy = recommendBy;
	}
}
