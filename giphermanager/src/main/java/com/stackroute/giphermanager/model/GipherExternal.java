package com.stackroute.giphermanager.model;

public class GipherExternal {
	
	private String id;
	private String embed_url;
	
	public GipherExternal() {}
	
	public GipherExternal(String id, String embed_url) {
		super();
		this.id = id;
		this.embed_url = embed_url;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmbed_url() {
		return embed_url;
	}
	public void setEmbed_url(String embed_url) {
		this.embed_url = embed_url;
	}

	@Override
	public String toString() {
		return "GipherExternal [id=" + id + ", embed_url=" + embed_url + "]";
	}
	
	

}
