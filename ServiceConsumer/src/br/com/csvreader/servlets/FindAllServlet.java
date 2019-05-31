package br.com.csvreader.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/findAll")
public class FindAllServlet {
	
	@GET
	@Path("/")
	@Produces("application/json")
	public Response getAll() {
		// return getAllFromDB();
		return Response.status(200).entity(getAllFromDB()).build();
	}
	
	private static String getAllFromDB() {
		String erro = "";
		
		try {
			URL url = new URL("http://localhost:8080/ServiceConsumer/rest/database/");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Erro: " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String output;
			String response = "";
			
			while ((output = br.readLine()) != null) {
				response += output;
			}
			
			conn.disconnect();
			
			return response;
		} catch (MalformedURLException e) {
			erro = "MalformedURLException: " + e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			erro = "IOException: " + e.getMessage();
			e.printStackTrace();
		} catch (RuntimeException e) {
			erro = "RuntimeException: " + e.getMessage();
			e.printStackTrace();
		}
		
		return erro;
	}

}
