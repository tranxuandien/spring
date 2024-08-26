package com.example.lab.common.report;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.lab.model.ChemicalInfo;
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

	@Autowired
	BarcodeFactory factory;

	private List<ChemicalInfo> listChemical;

	public BarCodePDFExporter(List<ChemicalInfo> listChemical) {
		this.listChemical = listChemical;
	}

	private void writeTableData(PdfPTable table)
			throws OutputException, BadElementException, IOException, BarcodeException {
		for (ChemicalInfo chemical : listChemical) {
			PdfPCell cell1 = new PdfPCell();
			Barcode bc = BarcodeFactory.createCode128(chemical.getCode());
			bc.setBarHeight(60);
//			bc.setBarWidth(2);
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.addElement(new Paragraph(chemical.getName()));

			cell1.addElement(new Paragraph("      "));
			cell1.addElement(com.lowagie.text.Image.getInstance(BarcodeImageHandler.getImage(bc), null));
			Paragraph code = new Paragraph(chemical.getCode());
			code.setAlignment(Element.ALIGN_CENTER);
			cell1.addElement(code);
			table.addCell(cell1);
			table.addCell(cell1);
			table.addCell(cell1);
			table.addCell(cell1);
			table.addCell(cell1);
			table.addCell(cell1);
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
}