package com.comptroller;

import java.io.*;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

public class ConvertCSVtoXLSX {
	public static void main(String[] args) {
		try {
			String csvFileAddress = "C:\\Users\\Likhitha.batthula\\Desktop\\Comptroller Voucher documentation\\sec004_Contractual_Schedule_P00333_2017_11_13_14_44_41.csv"; // csv
																																											// file
																																											// address
			String xlsxFileAddress = "C:\\Users\\Likhitha.batthula\\Desktop\\Comptroller Voucher documentation\\sec004_Contractual_Schedule.xlsx"; // xlsx
																																					// file
																																					// address

			CSVReader reader = null;
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("sheet1");
			String[] currentLine;
			int RowNum = 0;
			reader = new CSVReader(new FileReader(csvFileAddress));
			// BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
			while ((currentLine = reader.readNext()) != null) {
				Row currentRow = sheet.createRow(RowNum++);
				for (int i = 0; i < currentLine.length; i++) {
					if (NumberUtils.isDigits(currentLine[i])) {
						currentRow.createCell(i).setCellValue(Integer.parseInt(currentLine[i]));
					} else if (NumberUtils.isNumber(currentLine[i])) {
						currentRow.createCell(i).setCellValue(Double.parseDouble(currentLine[i]));
					} else {
						currentRow.createCell(i).setCellValue(currentLine[i]);
					}
				}
			}

			FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddress);
			workBook.write(fileOutputStream);
			workBook.close();
			fileOutputStream.close();
			reader.close();
			System.out.println("Done");
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Exception in try");
		}
	}

}