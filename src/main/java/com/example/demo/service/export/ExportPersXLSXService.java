package com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;

@Service
public class ExportPersXLSXService {

	public void export (OutputStream os, FactureDTO facture) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");
        XSSFSheet sheetf = workbook.createSheet("facture"); 
        
        //Feuille client
        XSSFRow headerR = sheet.createRow(0);
        XSSFCell cellPr = headerR.createCell(0);
        cellPr.setCellValue("Prénom");
        XSSFCell cellN = headerR.createCell(1);
        cellN.setCellValue("Nom");
        
        XSSFRow headerRow = sheet.createRow(1);
        XSSFCell cellPrenom = headerRow.createCell(0);
        cellPrenom.setCellValue(facture.getClient().getPrenom().replaceAll(";", ""));
        XSSFCell cellNom = headerRow.createCell(1);
        cellNom.setCellValue(facture.getClient().getNom().replaceAll(";", ""));
        
        //Feuille facture
        List<LigneFactureDTO> tab = facture.getLigneFactures();
        Double total= 0.0;
        XSSFRow headerfRow = sheetf.createRow(0);
        XSSFCell cellD = headerfRow.createCell(0);
        cellD.setCellValue("Désignation");
        XSSFCell cellQ = headerfRow.createCell(1);
        cellQ.setCellValue("Quantité");
        XSSFCell cellP = headerfRow.createCell(2);
        cellP.setCellValue("Prix Unitaire");
        
        
        Integer i=1;
        for(LigneFactureDTO t:tab) {
        	XSSFRow lineRow = sheetf.createRow(i);
        	XSSFCell cellDesignation = lineRow.createCell(0);
        	cellDesignation.setCellValue(t.getDesignation());
        	
        	XSSFCell cellQuantite = lineRow.createCell(1);
        	cellQuantite.setCellValue(t.getQuantite());
        	
        	XSSFCell cellPrixUnitaire = lineRow.createCell(2);
        	cellPrixUnitaire.setCellValue(t.getPrixUnitaire());
        	total+=t.getQuantite()*t.getPrixUnitaire();
        	i++;
        	
        }
        BigDecimal bd = new BigDecimal(total);
		bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
		total = bd.doubleValue();
        XSSFRow totalRow = sheetf.createRow(i+1);
        XSSFCell cellT = totalRow.createCell(1);
        cellT.setCellValue("Total");
        XSSFCell cellTotal = totalRow.createCell(2);
        cellTotal.setCellValue(total);
        
        workbook.write(os);
        workbook.close();
	}
}
