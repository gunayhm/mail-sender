package com.example.demo.util;

import com.example.demo.dto.EmployeeDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileGenerator {

  private static final String[] HEADERS = {"ID", "NAME", "SURNAME"};
  private static final String SHEET_NAME = "Sheet";

  public byte[] generateExcelFile(EmployeeDto employeeDto) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet(SHEET_NAME);
    createHeaderRow(sheet);
    populateDataRow(sheet, employeeDto);
    return generateExcelContent(workbook);
  }

  private void createHeaderRow(Sheet sheet) {
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < HEADERS.length; i++) {
      Cell cell = headerRow.createCell(i);
      cell.setCellValue(HEADERS[i]);
    }
  }

  private void populateDataRow(Sheet sheet, EmployeeDto employeeDto) {
    Row dataRow = sheet.createRow(1);
    dataRow.createCell(0).setCellValue(employeeDto.getId());
    dataRow.createCell(1).setCellValue(employeeDto.getName());
    dataRow.createCell(2).setCellValue(employeeDto.getSurname());
  }

  private byte[] generateExcelContent(Workbook workbook) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      workbook.write(outputStream);
      return outputStream.toByteArray();
    } catch (IOException e) {
      log.error("Error generating Excel file: {}", e.getMessage());
      throw new RuntimeException("Error generating Excel file", e);
    }
  }

}
