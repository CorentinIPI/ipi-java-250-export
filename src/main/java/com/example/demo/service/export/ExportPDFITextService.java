package com.example.demo.service.export;


import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



@Service
public class ExportPDFITextService {
	

	public void export(OutputStream os, FactureDTO facture) throws DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, os);
		document.open();
		
		Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLACK);
	    Chunk c = new Chunk("Facture de "+facture.getClient().getNom().replaceAll(";","")+" "+ facture.getClient().getPrenom().replaceAll(";",""), f);
	    c.setBackground(BaseColor.GRAY);
		List<LigneFactureDTO> tab = facture.getLigneFactures();
		Paragraph client = new Paragraph(c);
		PdfPTable table=new PdfPTable(3);
		
		table.addCell("Désignation");
        table.addCell("Quantité");
        table.addCell("Prix Unitaire");
        table.setHeaderRows(1);
        Double total = 0.0;
		for(LigneFactureDTO t : tab) {
			table.addCell(t.getDesignation());
			table.addCell(t.getQuantite().toString());
			table.addCell(t.getPrixUnitaire().toString());
			total+=t.getQuantite()*t.getPrixUnitaire();
		}
		BigDecimal bd = new BigDecimal(total);
		bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
		total = bd.doubleValue();
		table.addCell("");
		table.addCell("Total");
		table.addCell(total.toString());
		client.add(table);
		document.add(client);

		document.close();
	}

}
