package com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ClientDTO;

@Service
public class ExportXLSXService {

	public void export (OutputStream os, List<ClientDTO> clients) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet  sheet = workbook.createSheet("clients");

        Integer i=1;
        
        XSSFRow headerR = sheet.createRow(0);
        XSSFCell cellPr = headerR.createCell(0);
        cellPr.setCellValue("Prénom");
        XSSFCell cellN = headerR.createCell(1);
        cellN.setCellValue("Nom");
        
        for(ClientDTO cl : clients){

            XSSFRow headerRow = sheet.createRow(i);
            XSSFCell cellPrenom = headerRow.createCell(0);
            cellPrenom.setCellValue(cl.getPrenom().replaceAll(";", ""));
            XSSFCell cellNom = headerRow.createCell(1);
            cellNom.setCellValue(cl.getNom().replaceAll(";", ""));
            i++;
        }
        workbook.write(os);
        workbook.close();
	}
}
