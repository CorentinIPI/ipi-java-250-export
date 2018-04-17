package com.example.demo.service.export;


import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;

@Service
public class ExportCSVService {

	public void export(Writer writer, List<ClientDTO> clients) throws IOException {
		for(ClientDTO client : clients){
			writer.write(client.getNom().replaceAll(";","")+";"+client.getPrenom().replaceAll(";", "")+";");
		}
	}

}
