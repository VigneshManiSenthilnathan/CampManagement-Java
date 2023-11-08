package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class Upload {

    public void updateCampName(String campName, String newName) {
        updateCellValue(campName, newName, 0);
    }

    public void updateDates(String campName, String newDate) {
        updateCellValue(campName, newDate, 1);
    }

    public void updateClosingDate(String campName, String newDate) {
        updateCellValue(campName, newDate, 2);
    }

    public void updateFaculty(String campName, String newFaculty) {
        updateCellValue(campName, newFaculty, 3);
    }

    public void updateLocation(String campName, String newLocation) {
        updateCellValue(campName, newLocation, 4);
    }

    public void updateAttendeeSlots(String campName, String newSlots) {
        updateCellValue(campName, newSlots, 5);
    }

    public void updateCommitteeSlots(String campName, String newSlots) {
        updateCellValue(campName, newSlots, 6);
    }

    public void updateDescription(String campName, String newDesc) {
        updateCellValue(campName, newDesc, 7);
    }

    public void updateStaffInCharge(String campName, String staff) {
        updateCellValue(campName, staff, 8);
    }

    // Need to figure out how to update attendees and camp committee list still.

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

        Sheet sheet = workbook.getSheet("Camps");

        // If the sheet doesn't exist, create it
        if (sheet == null) {
            sheet = workbook.createSheet("Camps");

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
            headerRow.createCell(8).setCellValue("Visibilty");
            headerRow.createCell(9).setCellValue("Staff In Charge");

            headerRow.createCell(10).setCellValue("Attendees");
            headerRow.createCell(11).setCellValue("Committee Members");
        }

        int lastRowNum = sheet.getLastRowNum();
        int rowNum = lastRowNum + 1;

        for (CampInfoController camp : campList) {

            // Check if the camp already exists in the Excel sheet
            boolean campExists = false;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    String existingCampName = row.getCell(0).getStringCellValue();
                    if (existingCampName.equals(camp.getCampName())) {
                        campExists = true;
                        break;
                    }
                }
            }

            if (!campExists) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(camp.getCampName());
                row.createCell(1).setCellValue(camp.getCampDate());
                row.createCell(2).setCellValue(camp.getClosing());
                row.createCell(3).setCellValue(camp.getFaculty());
                row.createCell(4).setCellValue(camp.getLocation());
                row.createCell(5).setCellValue(camp.getTotalSlots());
                row.createCell(6).setCellValue(camp.getCommitteeSlots());
                row.createCell(7).setCellValue(camp.getDescription());
                row.createCell(8).setCellValue(camp.getVisibility());
                row.createCell(9).setCellValue(camp.getStaff());

                row.createCell(10).setCellValue(String.join(" ", camp.getAttendeeUserID()));
                row.createCell(11).setCellValue(String.join(" ", camp.getCampCommitteeUserID()));
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCamp(String campNameToDelete) {

        String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        Workbook workbook;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Sheet sheet = workbook.getSheet("Camps");

        if (sheet == null) {
            System.out.println("No camps exist!");
            return;
        }

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell campNameCell = row.getCell(0);

            if (campNameCell != null && campNameCell.getCellType() == CellType.STRING) {
                String campName = campNameCell.getStringCellValue();
                if (campName.equalsIgnoreCase(campNameToDelete)) {
                    rowIterator.remove(); // Remove the row with the matching camp name
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCellValue(String campName, String newValue, int columnIndex) {

        String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Handle the case where the file doesn't exist
                System.out.println("The Excel file doesn't exist.");
                return;
            }

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Camps");

            boolean campFound = false;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    String existingCampName = row.getCell(0).getStringCellValue();
                    if (existingCampName.equals(campName)) {
                        campFound = true;
                        Cell cell = row.createCell(columnIndex);
                        cell.setCellValue(newValue);
                        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                            workbook.write(outputStream);
                            System.out.println("Database successfully updated!");
                        }
                        return; // Exit the loop once the camp is updated
                    }
                }
            }

            if (!campFound) {
                System.out.println("Camp does not exist in database. Check camp name!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
