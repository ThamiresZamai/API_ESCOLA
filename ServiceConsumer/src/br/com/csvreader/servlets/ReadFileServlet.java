package br.com.csvreader.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.csvreader.model.School;
import br.com.csvreader.util.CSVReader;

@Path("/readFile")
public class ReadFileServlet {
	
	@GET // http://localhost:8080/ServiceConsumer/rest/readFile/getFile
	@Path("/getFile")
	@Produces("text/plain")
	public Response getFile() {
		CSVReader reader = new CSVReader();
		String file = "Documentos/Faculdade/SD/CSVReaderProject/arquivo_dados.csv";
		
		if (reader.fileExists(file)) {
			List<List<String>> fileData = reader.readFile(file);
			
			int sucessos = 0;
			int falhas = 0;
			
			for (List<String> linha : fileData) {
				try {
					School school = new School();
					
					school.setId(linha.size() > 0 ? Integer.parseInt(linha.get(0)) : 0);
					school.setSchoolCode(linha.size() > 1 ? linha.get(1) : "");
					school.setSchoolName(linha.size() > 2 ? linha.get(2) : "");
					school.setAddress(linha.size() > 3 ? linha.get(3) : "");
					school.setCity(linha.size() > 4 ? linha.get(4) : "");
					school.setStateCode(linha.size() > 5 ? linha.get(5) : "");
					school.setZipCode(linha.size() > 6 ? linha.get(6) : "");
					school.setProvince(linha.size() > 7 ? linha.get(7) : "");
					school.setCountry(linha.size() > 8 ? linha.get(8) : "");
					school.setPostalCode(linha.size() > 9 ? linha.get(9) : "");
					
					if (saveFile(school)) {
						sucessos++;
					} else {
						falhas++;
					}
				} catch (NumberFormatException e) {
					falhas++;
				}
			}
			
			return Response.status(200).entity("Leitura do arquivo finalizada. Sucessos: " + String.valueOf(sucessos) + ", Falhas: " + String.valueOf(falhas)).build();
		} else {
			return Response.status(200).entity("Arquivo não encontrado").build();
		}
	}
	
	private static boolean saveFile(School school) {
		boolean sucesso;
		
		try {
			URL url = new URL("http://localhost:8080/ServiceConsumer/rest/database/");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = school.toJson();
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				sucesso = false;
			} else {
				sucesso = true;
			}
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			sucesso = false;
		} catch (IOException e) {
			sucesso = false;
		} catch (RuntimeException e) {
			sucesso = false;
		}
		
		return sucesso;
	}

}
