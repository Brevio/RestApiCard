package com.brevio.restapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest {
	
	private final int TIME_OUT = 50000;
	
	
	public String get(String url){
		try{
			HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
			try {
				request.setDoOutput(true);
				request.setRequestMethod("GET");
				request.setConnectTimeout(TIME_OUT);
				
				return readResponse(request);
			} finally {
				request.disconnect();
			}	
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	public String get(String url, String json){
		try{
			HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
			try {
				request.setDoInput(true);
				request.setDoOutput(true);
				request.setRequestProperty("Content-Type", "application/json");
				request.setRequestMethod("GET");
				request.setConnectTimeout(TIME_OUT);
				try (OutputStream outputStream = request.getOutputStream()){
					outputStream.write(json.getBytes("UTF-8"));
				}
				return readResponse(request);
			} finally {
				request.disconnect();
			}	
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String post(String url, String json){
		try{
			HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
			try {
				request.setDoInput(true);
				request.setDoOutput(true);
				request.setRequestProperty("Content-Type", "application/json");
				request.setRequestMethod("POST");
				request.setConnectTimeout(TIME_OUT);
				try (OutputStream outputStream = request.getOutputStream()){
					outputStream.write(json.getBytes("UTF-8"));
				}
				return readResponse(request);
			} finally {
				request.disconnect();
			}	
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String put(String url, String json){
		try{
			HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
			try {
				request.setDoInput(true);
				request.setDoOutput(true);
				request.setRequestProperty("Content-Type", "application/json");
				request.setRequestMethod("PUT");
				request.setConnectTimeout(TIME_OUT);
				try (OutputStream outputStream = request.getOutputStream()){
					outputStream.write(json.getBytes("UTF-8"));
				}
				return readResponse(request);
			} finally {
				request.disconnect();
			}	
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String delete(String url, String json){
		try{
			HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
			try {
				request.setDoInput(true);
				request.setDoOutput(true);
				request.setRequestProperty("Content-Type", "application/json");
				request.setRequestMethod("DELETE");
				request.setConnectTimeout(TIME_OUT);
				try (OutputStream outputStream = request.getOutputStream()){
					outputStream.write(json.getBytes("UTF-8"));
				}
				return readResponse(request);
			} finally {
				request.disconnect();
			}	
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	private String readResponse(HttpURLConnection request) throws IOException {
		ByteArrayOutputStream os;
		
		try(InputStream is = request.getInputStream()){
			os = new ByteArrayOutputStream();
			int b ;
			while((b=is.read())!=-1){
				os.write(b);
			}
		}
		return os.toString("UTF-8");
	}

}
