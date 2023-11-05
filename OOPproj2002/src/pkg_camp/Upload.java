package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class Upload {

    public static void writeToExcel(List<CampInfoController> campList) {

        String filepath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        Workbook workbook;

        // Check if the Excel file already exists
        File file = new File(filepath);
        if (file.exists()) {
            try {
                workbook = WorkbookFactory.create(file);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet("Camps");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Camp Name");
        headerRow.createCell(1).setCellValue("Date");
        headerRow.createCell(2).setCellValue("Closing");
        headerRow.createCell(3).setCellValue("Faculty");
        headerRow.createCell(4).setCellValue("Location");
        headerRow.createCell(5).setCellValue("Total Slots");
        headerRow.createCell(6).setCellValue("Committee Slots");
        headerRow.createCell(7).setCellValue("Description");
        headerRow.createCell(8).setCellValue("Staff In Charge");

        headerRow.createCell(9).setCellValue("Attendees");
        headerRow.createCell(10).setCellValue("Committee Members");

        int rowNum = 1;
        for (CampInfoController camp : campList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(camp.getCampName());
            row.createCell(1).setCellValue(camp.getCampDate());
            row.createCell(2).setCellValue(camp.getClosing());
            row.createCell(3).setCellValue(camp.getFaculty());
            row.createCell(4).setCellValue(camp.getLocation());
            row.createCell(5).setCellValue(camp.getTotalSlots());
            row.createCell(6).setCellValue(camp.getCommitteeSlots());
            row.createCell(7).setCellValue(camp.getDescription());
            row.createCell(8).setCellValue(camp.getStaff());

            row.createCell(3).setCellValue(String.join(", ", camp.getAttendeeUserID()));
            row.createCell(4).setCellValue(String.join(", ", camp.getCampCommitteeUserID()));
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
