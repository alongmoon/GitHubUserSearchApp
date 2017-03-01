package com.example.githubsearchusers;

import java.util.List;

import android.graphics.Bitmap;

public class GitHubUser {

	String total_count;
	String incomplete_results;
	List<ItemsEntity> items;
	
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getIncomplete_results() {
		return incomplete_results;
	}
	public void setIncomplete_results(String incomplete_results) {
		this.incomplete_results = incomplete_results;
	}
	
	public List<ItemsEntity> getItems() {
		return items;
	}
	public void setItems(List<ItemsEntity> items) {
		this.items = items;
	}
	
	 public static class ItemsEntity {
	        private String login;
	        private String avatar_url;
	        private Bitmap photo;

	        public void setLogin(String login) {
	            this.login = login;
	        }
	        public String getLogin() {
	            return login;
	        }
	        public void setavatar_url(String avatar_url) {
	            this.avatar_url = avatar_url;
	        }
	        public String getAvatar_url() {
	            return avatar_url;
	        }
	        public void setphoto(Bitmap photo){
	        	this.photo = photo;
	        }
	        public Bitmap getPhoto(){
	        	return photo;
	        }
	 }

}
