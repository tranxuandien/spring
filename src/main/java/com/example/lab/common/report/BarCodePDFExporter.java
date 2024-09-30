package com.example.lab.common.report;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class BarCodePDFExporter {

	public static final Integer CHEMICAL_CODE_LENGTH = 4;
	public static final Integer CHEMICAL_LOT_LENGTH = 6;
	
	@Autowired
	BarcodeFactory factory;

	private Integer chemicalId;

	private Integer number;
	
	private String[] printLst;
	
	private String chemicalName;
	
	private String barcode;

	public BarCodePDFExporter(Integer chemicalId, Integer number, String[] printLst, String chemicalName) {
		super();
		this.chemicalId = chemicalId;
		this.number = number;
		this.printLst = printLst;
		this.chemicalName = chemicalName;
	}

	public BarCodePDFExporter(String barcode, String chemicalName) {
		this.barcode = barcode;
		this.chemicalName = chemicalName;
		this.number = 1;
	}

	private void writeTableData(PdfPTable table)
			throws OutputException, BadElementException, IOException, BarcodeException {
		for (int i = 0; i < this.number+(5-this.number%5); i++) {
			PdfPCell cell1 = new PdfPCell();
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (i >= this.number) {
				table.addCell(cell1);
				continue;
			}
			String chemicalCode = "0".repeat(CHEMICAL_CODE_LENGTH - chemicalId.toString().length())+chemicalId.toString();
			String chemicalLot = "0".repeat(CHEMICAL_LOT_LENGTH - printLst[i].length())+printLst[i];
			Barcode bc = BarcodeFactory.createCode128(chemicalCode+chemicalLot);
			bc.setBarHeight(60);
			cell1.addElement(new Paragraph(chemicalName));
			cell1.addElement(new Paragraph("      "));
			cell1.addElement(com.lowagie.text.Image.getInstance(BarcodeImageHandler.getImage(bc), null));
			Paragraph code = new Paragraph(chemicalCode+chemicalLot);
			code.setAlignment(Element.ALIGN_CENTER);
			cell1.addElement(code);
			table.addCell(cell1);
		}
	}

	public void export(HttpServletResponse response)
			throws DocumentException, IOException, OutputException, BarcodeException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		PdfPTable table = new PdfPTable(5);
		writeTableData(table);

		document.add(table);

		document.close();
	}

	public void exportOne(HttpServletResponse response) throws DocumentException, IOException, OutputException, BarcodeException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		PdfPTable table = new PdfPTable(5);
		writeTableDataOne(table);

		document.add(table);

		document.close();
	}

	private void writeTableDataOne(PdfPTable table) throws OutputException, BadElementException, IOException, BarcodeException {
		for (int i = 0; i < this.number+(5-this.number%5); i++) {
			PdfPCell cell1 = new PdfPCell();
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			if (i >= this.number) {
				table.addCell(cell1);
				continue;
			}
			Barcode bc = BarcodeFactory.createCode128(barcode);
			bc.setBarHeight(60);
			cell1.addElement(new Paragraph(chemicalName));
			cell1.addElement(new Paragraph("      "));
			cell1.addElement(com.lowagie.text.Image.getInstance(BarcodeImageHandler.getImage(bc), null));
			Paragraph code = new Paragraph(barcode);
			code.setAlignment(Element.ALIGN_CENTER);
			cell1.addElement(code);
			table.addCell(cell1);
		}
	}
}