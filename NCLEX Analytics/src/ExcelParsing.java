
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * A dirty simple program that reads an Excel file.
 * 
 * @author www.codejava.net
 *
 */
public class ExcelParsing {
	Hashtable<String, Student> hash;

	public Hashtable<String, Student> parse() throws IOException {
		
		String excelFilePath = "nclex.xls"; //file to read data from
		
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		DataFormatter formatter = new DataFormatter();
		Sheet sheet;
		hash = new Hashtable<String, Student>();
		String[] studentArray = new String[10];
		sheet = workbook.getSheetAt(0);
		int numRows = 0;
		int numCols = 0;
		
		//get number of rows
		for (;;) {
			String text = formatter.formatCellValue(sheet.getRow(numRows + 1).getCell(0));
			if (text.length() > 0) {
				numRows++;
			} else
				break;
		}
		
		//get number of columns
		for (;;) {
			String text = formatter.formatCellValue(sheet.getRow(0).getCell(numCols));
			if (text.length() > 0) {
				numCols++;
			} else
				break;
		}
		System.out.println("Rows: " + numRows + " Columns: " + numCols);
		System.out.println(" ");

		for (int r = 1; r <= numRows + 1; r++) {

			studentArray = new String[10];

			for (int c = 0; c < 10; c++) {
				studentArray[c] = formatter.formatCellValue(sheet.getRow(r).getCell(c));
				System.out.print(studentArray[c]);
				System.out.print("\t\t");

			}
			System.out.println("");
		}

		workbook.close();
		inputStream.close();
		System.out.println("----------------------------------------------------------------------------------");

		return hash;
	}

}