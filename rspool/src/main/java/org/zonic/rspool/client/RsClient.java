package org.zonic.rspool.client;

import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;

public class RsClient {
	
	private Future<?> future;
	private Client client;
	
	public <T> void postAsync(String target, String path, Entity<T> entity, InvocationCallback<T> callback) {
		client = ClientBuilder.newClient();
		future = client.target(target).path(path).request().async().post(entity, callback);
	}
	
	public static <T> Entity<T> entity(T entity, String type) {
		return Entity.entity(entity, type);
	}
	
	public void shutDown() {
		try {
			if(future != null) {
				while(!future.isDone()) {
				}
			}
			
			if(client != null) {
				client.close();
			}
		} catch (Exception e) {
		}
		
		future = null;
		client = null;
	}
	
	public boolean isReady() {
		if(future != null) {
			if(!future.isDone()) {
				return false; 
			} else {
				shutDown();
			}
		}
		
		return true;
	}
}
