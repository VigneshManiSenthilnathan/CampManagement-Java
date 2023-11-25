package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Upload {

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
            headerRow.createCell(12).setCellValue("Withdrawn Students");
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
                row.createCell(11).setCellValue(String.join(" ", camp.getCommiteeUserID()));
                row.createCell(12).setCellValue(String.join(" ", camp.getBlackList()));
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
                row.createCell(11).setCellValue(String.join(" ", camp.getCommiteeUserID()));
                row.createCell(12).setCellValue(String.join(" ", camp.getBlackList()));
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

    public static void updateEnquiryStringInExcel(String oldEnquiryString, String newEnquiryString) {
        String filePath = "OOPproj2002/src/pkg_camp/enquiries.xlsx";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet("Enquiries");

            for (Row row : sheet) {
                Cell enquiryStringCell = row.getCell(2);
                if (enquiryStringCell != null && enquiryStringCell.getCellType() == CellType.STRING) {
                    String existingCampName = enquiryStringCell.getStringCellValue();
                    if (existingCampName.equals(oldEnquiryString)) {
                        // Update the camp name in the Excel sheet
                        enquiryStringCell.setCellValue(newEnquiryString);
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

    public static void enquiriesWriter() throws IOException {

        String filePath = "OOPproj2002/src/pkg_camp/enquiries.xlsx";

        Workbook workbook;

        // We initialize a list of enquiries across all camps made by all students
        // This list will be in order of camps

        List<Enquiry> allEnquiries = new ArrayList<>();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getEnquiryList().size() > 0) {
                allEnquiries.addAll(camp.getEnquiryList());

            }
        }

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

        Sheet sheet_enquiry = workbook.getSheet("Enquiries");

        // If the sheet doesn't exist, create it
        if (sheet_enquiry == null) {
            sheet_enquiry = workbook.createSheet("Enquiries");

            // Create header row
            Row headerRow = sheet_enquiry.createRow(0);
            headerRow.createCell(0).setCellValue("Camp Name");
            headerRow.createCell(1).setCellValue("Student User ID");
            headerRow.createCell(2).setCellValue("Enquiry");
            headerRow.createCell(3).setCellValue("Replied By");
            headerRow.createCell(4).setCellValue("Replier Type (Staff / Committee)");
            headerRow.createCell(5).setCellValue("Reply");
        }

        // If the sheet exists, delete it
        Iterator<Row> rowIterator = sheet_enquiry.iterator();
        List<Row> rowsToRemove = new ArrayList<>();

        // Make the iterator skip the header row
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell campNameCell = row.getCell(0);

            if (campNameCell != null) {
                rowsToRemove.add(row);
            }
        }

        // Remove the collected rows outside the loop
        for (Row rowToRemove : rowsToRemove) {
            sheet_enquiry.removeRow(rowToRemove);
        }

        // Should always be 1 because of the header row ??
        int rowNum = 1;

        for (Enquiry enquiry : allEnquiries) {
            // Check if the enquiry already exists in the Excel sheet

            // Add new row
            Row row = sheet_enquiry.createRow(rowNum++);

            row.createCell(0).setCellValue(enquiry.getCampName());
            row.createCell(1).setCellValue(enquiry.getStudentID());
            row.createCell(2).setCellValue(enquiry.getEnquiryString());
            row.createCell(3).setCellValue(enquiry.getRepliedBy());
            row.createCell(4).setCellValue(enquiry.getReplierType());
            row.createCell(5).setCellValue(enquiry.getReply());

        }

        try (
                FileOutputStream outputStream = new FileOutputStream(filePath, true)) {
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

    public static void suggestionsWriter() throws IOException {

        String filePath = "OOPproj2002/src/pkg_camp/suggestions.xlsx";
        Workbook workbook;

        // We initialize a list of suggestions across all camps made by all students
        // This list will be in order of camps

        List<Suggestion> allSuggestions = new ArrayList<>();
        List<Camp> allCamps = CampsList.getCreatedCampsList();

        for (Camp camp : allCamps) {
            if (camp.getSuggestionList().size() > 0) {
                System.out.println(camp.getSuggestionList().size());
                allSuggestions.addAll(camp.getSuggestionList());
            } else {
                // System.out.println(camp.getCampName() + " has no suggestions!");
            }
        }

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

        // Get or create the "Suggestions" sheet
        Sheet sheet_suggestion = workbook.getSheet("Suggestions");
        if (sheet_suggestion == null) {
            sheet_suggestion = workbook.createSheet("Suggestions");

            // Create header row if the sheet is newly created
            Row headerRow = sheet_suggestion.createRow(0);
            headerRow.createCell(0).setCellValue("Camp Name");
            headerRow.createCell(1).setCellValue("Committee Member User ID");
            headerRow.createCell(2).setCellValue("Suggestion");
            headerRow.createCell(3).setCellValue("Approval Status"); // boolean
        }

        else {
            Upload.deleteAll(filePath);
        }

        // Add new suggestions
        int rowNum = 1;

        for (Suggestion suggestion : allSuggestions) {
            Row row = sheet_suggestion.createRow(rowNum++);

            row.createCell(0).setCellValue(suggestion.getCampName());
            row.createCell(1).setCellValue(suggestion.getStudentID());
            row.createCell(2).setCellValue(suggestion.getSuggestionString());
            row.createCell(3).setCellValue(suggestion.getApproved());
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath, true)) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }

    public static void deleteAll(String filePath) throws IOException {
        // method to delete all camps from excel file

        Workbook workbook;
        File file = new File(filePath);
        workbook = WorkbookFactory.create(file);
        Sheet sheet_suggestion = workbook.getSheet("Suggestions");

        // Clear existing data by removing all rows except the header
        for (int i = sheet_suggestion.getLastRowNum(); i > 0; i--) {
            sheet_suggestion.removeRow(sheet_suggestion.getRow(i));
        }

        // check if deletion is truly successful by checking if there are any rows left
        if (sheet_suggestion.getLastRowNum() == 0) {
            // System.out.println("All suggestions deleted successfully");
        } else {
            System.out.println("Error deleting suggestions");
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath, true)) {
            workbook.write(outputStream);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // method to delete an entire workbook
    public static void deleteWorkbook(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}
