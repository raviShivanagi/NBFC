package net.javaguides.springbootsecurity.commons;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.javaguides.springbootsecurity.entities.ApplicantDetails;
import net.javaguides.springbootsecurity.repositories.CustomerLoanRepository;

@Component("pdfGenerator")
public class PDFGenerator<T> {

	@Value("${pdfDir}")
	private String pdfDir;

	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;

	@Value("${localDateFormat}")
	private String localDateFormat;

	@Value("${logoImgPath}")
	private String logoImgPath;

	@Value("${logoImgScale}")
	private String[] logoImgScale;

	@Autowired
	CustomerLoanRepository eRepo;

	private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

	public String generatePdfReport(List<T> objects, List<String> columnList, String reportFileName, String columnNames) {

		Document document = new Document();

		String fileName = getPdfNameWithDate(reportFileName);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			addLogo(document);
			addDocTitle(document, reportFileName);
			createTable(document, columnList.size(), objects, columnList , columnNames);
			addFooter(document, reportFileName);
			document.close();
			System.out.println("------------------Your PDF Report is ready!-------------------------");

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;

	}

	private void addLogo(Document document) {
		try {
			Image img = Image.getInstance(logoImgPath);
			img.scalePercent(Float.valueOf(logoImgScale[0]), Float.valueOf(logoImgScale[1]));
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document, String reportFileName) throws DocumentException {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph(reportFileName, COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));

		document.add(p1);

	}

	private void createTable(Document document, int noOfColumns, List<T> objects, List<String> columnList, String columnNames)
			throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		List<String> columns = Arrays.asList(columnNames.split(","));
		for (int i = 0; i < noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columns.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}

		table.setHeaderRows(1);
		getDbData(table, objects, columnList);
		document.add(table);
	}

	private void getDbData(PdfPTable table, List<T> objects, List<String> columnList) {

		// List<CustLoanDetails> list = eRepo.findAll();
		List<ApplicantDetails> list = new ArrayList<>();
		ApplicantDetails c = new ApplicantDetails();
		c.setApplicantFirstName("Ravi");
		c.setApplicantMidName("Mallinath");
		c.setApplicantLastName("Shivanagi");
		list.add(c);

		for (Object obj : objects) {
			for (String column : columnList) {
				try {
					PropertyDescriptor pd = new PropertyDescriptor(column, obj.getClass());
					Method getter = pd.getReadMethod();
					Object f = getter.invoke(obj);
					System.out.println(f);
					table.setWidthPercentage(100);
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

					table.addCell(f.toString());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void addFooter(Document document, String reportFileName) throws DocumentException {
		Paragraph p2 = new Paragraph();
		leaveEmptyLine(p2, 3);
		p2.setAlignment(Element.ALIGN_MIDDLE);
		p2.add(new Paragraph("------------------------End Of " + reportFileName + "------------------------",
				COURIER_SMALL_FOOTER));

		document.add(p2);
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private String getPdfNameWithDate( String reportFileName) {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
		return pdfDir + reportFileName + "-" + localDateString + ".pdf";
	}
}
