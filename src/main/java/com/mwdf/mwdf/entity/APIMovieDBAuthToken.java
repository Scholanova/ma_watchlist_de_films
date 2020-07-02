package com.mwdf.mwdf.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class APIMovieDBAuthToken {

	private boolean success;
	private String expires_at;
	private String request_token;
	
	public APIMovieDBAuthToken() {
		
	}
	
	public APIMovieDBAuthToken(boolean success, String expires_at, String request_token) {
		super();
		this.success = success;
		this.expires_at = expires_at;
		this.request_token = request_token;
	}
		
	public boolean isSucces() {
		return success;
	}

	public void setSucces(boolean succes) {
		this.success = succes;
	}

	public String getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}

	public String getRequest_token() {
		return request_token;
	}

	public void setRequest_token(String request_token) {
		this.request_token = request_token;
	}
	
	public Date getExpires_AtAsDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.getExpires_at());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
	}
	
	@Override
	public String toString() {
		return "APIMovieDBAuthToken [success=" + success + ", expires_at=" + expires_at + ", request_token="
				+ request_token + "]";
	}
	
}
