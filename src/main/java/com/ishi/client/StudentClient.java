package com.ishi.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentClient {
	
	private Client client;
	
	public StudentClient() {
		client = ClientBuilder.newClient();
	}	
	
	public void delete(Integer studentId) {
		WebTarget target = client.target("http://localhost:8080/services/webapi/");
		
		Response response = target.path("internship/" + studentId).request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " : There was an error in the server!");
		}
	}
}
