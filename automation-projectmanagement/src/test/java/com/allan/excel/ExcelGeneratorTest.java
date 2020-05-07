package com.allan.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.junit.jupiter.api.Test;

public class ExcelGeneratorTest {
	@Test
	public void generateXlsTest() throws FileNotFoundException, IOException {
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		String sheetName = WorkbookUtil.createSafeSheetName("new sheet");
		Sheet sheet = wb.createSheet(sheetName);

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);

		cell.setCellValue(1);
		cell.setCellValue(new Date());
		row.createCell(1).setCellValue(1.2);
		row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
		row.createCell(3).setCellValue(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		cell = row.createCell(1);
		cell.setCellValue(new Date());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue(Calendar.getInstance());
		cell.setCellStyle(cellStyle);
		
		// Create a row and put some cells in it. Rows are 0 based.
		Row row2= sheet.createRow(1);
		// Aqua background
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		Cell cell2 = row2.createCell(1);
		cell2.setCellValue("X");
		cell2.setCellStyle(style);
		// Orange "foreground", foreground being the fill foreground not the font color.
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell2 = row.createCell(2);
		cell2.setCellValue("X");
		cell2.setCellStyle(style);
		
		Row row3 = sheet.createRow(2);
		Cell cell3 = row3.createCell(1);
		cell3.setCellValue("This is a test of merging");
		sheet.addMergedRegion(new CellRangeAddress(
		        2, //first row (0-based)
		        2, //last row  (0-based)
		        1, //first column (0-based)
		        2  //last column  (0-based)
		));
		try (OutputStream fileOut = new FileOutputStream(new File("workbook.xls"))) {
			wb.write(fileOut);
		}

		wb.close();
	}

	@Test
	public void readXlsTest() throws FileNotFoundException, IOException {
		Workbook wb = WorkbookFactory.create(new File("workbook.xls"));
		DataFormatter formatter = new DataFormatter();
		Sheet sheet1 = wb.getSheetAt(0);
		for (Row row : sheet1) {
			for (Cell cell : row) {
				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
				System.out.print(cellRef.formatAsString());
				System.out.print(" - ");
				// get the text that appears in the cell by getting the cell value and applying
				// any data formats (Date, 0.00, 1.23e9, $1.23, etc)
				String text = formatter.formatCellValue(cell);
				System.out.println(text);
				// Alternatively, get the value and format it yourself
				switch (cell.getCellType()) {
				case STRING:
					System.out.println(cell.getRichStringCellValue().getString());
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						System.out.println(cell.getDateCellValue());
					} else {
						System.out.println(cell.getNumericCellValue());
					}
					break;
				case BOOLEAN:
					System.out.println(cell.getBooleanCellValue());
					break;
				case FORMULA:
					System.out.println(cell.getCellFormula());
					break;
				case BLANK:
					System.out.println();
					break;
				default:
					System.out.println();
				}
			}
		}

	}
}
