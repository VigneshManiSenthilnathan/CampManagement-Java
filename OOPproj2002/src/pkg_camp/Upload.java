package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.String;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public abstract class Upload {

    /*
     * public void updateCampName(String campName, String newName) {
     * updateCellValue(campName, newName, 0);
     * }
     * 
     * public void updateDates(String campName, String newDate) {
     * updateCellValue(campName, newDate, 1);
     * }
     * 
     * public void updateClosingDate(String campName, String newDate) {
     * updateCellValue(campName, newDate, 2);
     * }
     * 
     * public void updateFaculty(String campName, String newFaculty) {
     * updateCellValue(campName, newFaculty, 3);
     * }
     * 
     * public void updateLocation(String campName, String newLocation) {
     * updateCellValue(campName, newLocation, 4);
     * }
     * 
     * public void updateAttendeeSlots(String campName, String newSlots) {
     * updateCellValue(campName, newSlots, 5);
     * }
     * 
     * public void updateCommitteeSlots(String campName, String newSlots) {
     * updateCellValue(campName, newSlots, 6);
     * }
     * 
     * public void updateDescription(String campName, String newDesc) {
     * updateCellValue(campName, newDesc, 7);
     * }
     * 
     * public void updateStaffInCharge(String campName, String staff) {
     * updateCellValue(campName, staff, 8);
     * }
     */

    // Need to figure out how to update attendees and camp committee list still.

    public static void writeToExcel(List<Camp> campList) throws IOException {

        String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        Workbook workbook;

        // Check if the Excel file already exists
        File file = new File(filePath);

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

        Sheet sheet_camps = workbook.getSheet("Camps");
        // Sheet sheet_attendees = workbook.getSheet("Attendees");

        // Camps Sheet
        // If the sheet doesn't exist, create it
        if (sheet_camps == null) {
            sheet_camps = workbook.createSheet("Camps");

            // Create header row
            Row headerRow = sheet_camps.createRow(0);
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
            headerRow.createCell(11).setCellValue("Camp Committee");
        }

        int lastRowNumCamp = sheet_camps.getLastRowNum();
        int rowNumCamp = lastRowNumCamp + 1;

        for (Camp camp : campList) {
            // Check if the camp already exists in the Excel sheet
            boolean campExists = false;
            int rowIndex = -1;

            for (int i = 1; i <= sheet_camps.getLastRowNum(); i++) {
                Row row = sheet_camps.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    String existingCampName = row.getCell(0).getStringCellValue();
                    if (existingCampName.equals(camp.getCampName())) {
                        campExists = true;
                        rowIndex = i;
                        break;
                    }
                }
            }

            if (campExists) {
                // Update existing row
                Row row = sheet_camps.getRow(rowIndex);
                row.createCell(1).setCellValue(camp.getDates().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                row.createCell(2).setCellValue(
                        camp.getRegistrationClosingDate().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                row.createCell(3).setCellValue(camp.getUserGroup());
                row.createCell(4).setCellValue(camp.getLocation());
                row.createCell(5).setCellValue(camp.getTotalSlots());
                row.createCell(6).setCellValue(camp.getCampCommitteeSlots());
                row.createCell(7).setCellValue(camp.getDescription());
                row.createCell(8).setCellValue(camp.getVisibility());
                row.createCell(9).setCellValue(camp.getStaffInCharge());
                row.createCell(10).setCellValue(String.join(" ", camp.getAttendeeUserID()));
                // row.createCell(11).setCellValue(String.join(" ",
                // camp.getCampCommitteeUserID()));
            } else {
                // Add new row
                Row row = sheet_camps.createRow(rowNumCamp++);
                row.createCell(0).setCellValue(camp.getCampName());
                row.createCell(1).setCellValue(camp.getDates().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                row.createCell(2).setCellValue(
                        camp.getRegistrationClosingDate().format(DateTimeFormatter.ofPattern("dd-MM-yy")));
                row.createCell(3).setCellValue(camp.getUserGroup());
                row.createCell(4).setCellValue(camp.getLocation());
                row.createCell(5).setCellValue(camp.getTotalSlots());
                row.createCell(6).setCellValue(camp.getCampCommitteeSlots());
                row.createCell(7).setCellValue(camp.getDescription());
                row.createCell(8).setCellValue(camp.getVisibility());
                row.createCell(9).setCellValue(camp.getStaffInCharge());
                row.createCell(10).setCellValue(String.join(" ", camp.getAttendeeUserID()));
                // row.createCell(11).setCellValue(String.join(" ",
                // camp.getCampCommitteeUserID()));
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath, true)) {
            try {
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                workbook.close();
            } catch (IOException e) {
                outputStream.flush();
                outputStream.close();
                workbook.close();
                e.printStackTrace();
            }
        } catch (IOException e) {
            workbook.close();
            e.printStackTrace();
        }
    }

    public static void deleteCamp(String campNameToDelete) {

        String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        Workbook workbook;

        System.out.println("reached deleteCamp");

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
        List<Row> rowsToRemove = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell campNameCell = row.getCell(0);

            if (campNameCell != null) {
                String campName = campNameCell.getStringCellValue();

                if (campName.equalsIgnoreCase(campNameToDelete)) {
                    System.out.println("Deleting camp: " + campName);
                    // Collect the row to be removed
                    rowsToRemove.add(row);
                }
            }
        }

        // Remove the collected rows outside the loop
        for (Row rowToRemove : rowsToRemove) {
            sheet.removeRow(rowToRemove);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCampNameInExcel(String oldCampName, String newCampName) {
        String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet("Camps");

            for (Row row : sheet) {
                Cell campNameCell = row.getCell(0);
                if (campNameCell != null && campNameCell.getCellType() == CellType.STRING) {
                    String existingCampName = campNameCell.getStringCellValue();
                    if (existingCampName.equals(oldCampName)) {
                        // Update the camp name in the Excel sheet
                        campNameCell.setCellValue(newCampName);
                        break;
                    }
                }
            }

            // Save the changes
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
