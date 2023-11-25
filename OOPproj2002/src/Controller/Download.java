package controller;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Download {
    // class to pull data from excel file into running program
    // Load into createdCamps the camps from the excel file camps.xlsx

    public static List<Camp> loadCamps(List<Camp> createdCampsList) {

        try {
            String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";
            Workbook workbook;

            // Check if the Excel file already exists
            File file = new File(filePath);
            if (!file.exists()) {
                return createdCampsList;
            }

            // The FileInputStream provides the necessary stream to read the bytes from the
            // file which is required for operations like reading an Excel workbook.

            FileInputStream excelFile = new FileInputStream(new File(filePath));

            // Upload --> Upload runtime memory to excel (Already have camps.xlsx, if not
            // create new one)
            // Download --> Download excel to runtime memory (Need to check if the excel
            // exists)

            workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheet("Camps");

            // Check if the file is empty
            if (sheet.getLastRowNum() <= 0) { // Assuming the header is in the first row
                excelFile.close();
                workbook.close();
                throw new IOException("The supplied file is empty.");
            } else {
                System.out.println(sheet.getLastRowNum());
            }

            for (Row row : sheet) {
                // ignore the initial header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                else {
                    String campName = row.getCell(0).getStringCellValue();

                    String dates = row.getCell(1).getStringCellValue();
                    LocalDate campDates = LocalDate.parse(dates, DateTimeFormatter.ofPattern("dd-MM-yy"));

                    String closedate = row.getCell(2).getStringCellValue();
                    LocalDate campRegClosingDate = LocalDate.parse(closedate, DateTimeFormatter.ofPattern("dd-MM-yy"));

                    String campFaculty = row.getCell(3).getStringCellValue();
                    String campLocation = row.getCell(4).getStringCellValue();
                    int campAttendeeSlots = (int) row.getCell(5).getNumericCellValue();
                    int campCommitteeSlots = (int) row.getCell(6).getNumericCellValue();
                    String campDescription = row.getCell(7).getStringCellValue();

                    Cell cell = row.getCell(8);
                    boolean campVisibility = true;
                    if (cell.getCellType() == CellType.BOOLEAN) {
                        campVisibility = row.getCell(8).getBooleanCellValue();
                    }

                    else if (cell.getCellType() == CellType.STRING) {
                        String campVisibilityStr = row.getCell(8).getStringCellValue();
                        campVisibility = true;
                        if (campVisibilityStr.equalsIgnoreCase("false")) {
                            campVisibility = false;
                        }
                    }

                    String campStaffInCharge = row.getCell(9).getStringCellValue();

                    // Load space seperated string and convert to list for attendees
                    String campAttendees = null;

                    if (row.getCell(10) != null) {
                        campAttendees = row.getCell(10).getStringCellValue();
                    } else {
                        System.out.println("No attendees");
                    }

                    if (campAttendees != null) {
                        campAttendees = campAttendees.trim();
                    }

                    List<Student> attendees = new ArrayList<Student>();
                    if (campAttendees != null && !campAttendees.isEmpty()) {
                        String[] campAttendeesList = campAttendees.split(" ");
                        for (String attendeeUserID : campAttendeesList) {
                            System.out.println("Attendee UserID: " + attendeeUserID);
                            attendees.add((Student) createUser(attendeeUserID, "STUDENT"));
                        }
                    }

                    // Load space seperated string and convert to list for campcommittee
                    String campCommittee = null;
                    if (row.getCell(11) != null) {
                        campCommittee = row.getCell(11).getStringCellValue();
                    }

                    ArrayList<CampCommitteeMember> comm = new ArrayList<>();
                    if (campCommittee != null && campCommittee != "") {
                        String[] campCommitteeList = campCommittee.split(" ");
                        for (String commMemUserID : campCommitteeList) {
                            comm.add((CampCommitteeMember) createUser(commMemUserID, "STUDENT"));
                        }
                    }

                    // Load space seperated string for blacklsited students and convert to list

                    String blacklisted = null;
                    if (row.getCell(12) != null) {
                        blacklisted = row.getCell(12).getStringCellValue();
                    }

                    ArrayList<CampCommitteeMember> blackList = new ArrayList<>();
                    if (blacklisted != null && blacklisted != "") {
                        String[] black = blacklisted.split(" ");
                        for (String blacklistedUserID : black) {
                            ;
                        }
                    }

                    CampInformation campinfo = new CampInformation(campName, campDates, campRegClosingDate,
                            campFaculty, campLocation, campAttendeeSlots, campCommitteeSlots, campDescription,
                            campStaffInCharge, campVisibility);

                    Camp camp;

                    if (campAttendees != null && campAttendees != "") {

                        // Both Attendees and Committee lists are available
                        if (campCommittee != null && campCommittee != "") {

                            camp = new Camp(campinfo);

                            camp.addAttendee(attendees);
                            camp.setCampCommitteeMemberList(comm);
                            createdCampsList.add(camp);
                        }

                        // Only Attendees list is available
                        else {
                            camp = new Camp(campinfo);
                            camp.addAttendee(attendees);
                            createdCampsList.add(camp);
                        }
                    }

                    // Only Committee list is available
                    else if (campCommittee != null && campCommittee != "") {
                        camp = new Camp(campinfo);
                        camp.setCampCommitteeMemberList(comm);
                        createdCampsList.add(camp);
                    }

                    // Neither available
                    else {
                        camp = new Camp(campinfo);
                        createdCampsList.add(camp);
                    }

                    // Check if the camp has enquiries or suggestions lists and load them
                    camp = loadEnquiries(camp);
                    camp = loadSuggestions(camp);

                    System.out.println("Camp: " + campName + " loaded successfully.");
                }
            }
            excelFile.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading camps from excel file. Exception: " + e.getClass().getSimpleName()
                    + ", Message: " + e.getMessage());
        }

        return createdCampsList;
    }

    public static Camp loadEnquiries(Camp camp) {

        try {
            String filePath = "OOPproj2002/src/pkg_camp/enquiries.xlsx";
            Workbook workbook;

            // Check if the Excel file already exists
            File file = new File(filePath);
            if (!file.exists()) {
                return camp;
            }

            // The FileInputStream provides the necessary stream to read the bytes from the
            // file which is required for operations like reading an Excel workbook.

            FileInputStream excelFile = new FileInputStream(new File(filePath));

            // Upload --> Upload runtime memory to excel (Already have camps.xlsx, if not
            // create new one)
            // Download --> Download excel to runtime memory (Need to check if the excel
            // exists)

            workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheet("Enquiries");

            // Check if the file is empty
            if (sheet.getLastRowNum() <= 0) { // Assuming the header is in the first row
                excelFile.close();
                workbook.close();
                return camp;
            } else {
                System.out.println(sheet.getLastRowNum());
            }

            for (Row row : sheet) {
                // ignore the initial header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                else {

                    // iterate through the rows to check if the first column entry is equal to
                    // camp.getCampName()
                    // if yes, then add the enquiry to the camp's enquiry list
                    // if no, then continue to the next row

                    Enquiry enquiry = null;

                    String campName = row.getCell(0).getStringCellValue();
                    if (campName.equals(camp.getCampName())) {

                        String sender = row.getCell(1).getStringCellValue();
                        String message = row.getCell(2).getStringCellValue();
                        String receiverStr = row.getCell(3).getStringCellValue();

                        String receiverType = row.getCell(4).getStringCellValue();

                        String reply = row.getCell(5).getStringCellValue();

                        // check if reply is empty or null
                        if (reply == null || reply.isEmpty()) {
                            enquiry = new Enquiry(sender, campName, message);
                        }

                        else {
                            enquiry = new Enquiry(sender, campName, message, receiverStr, receiverType, reply);
                        }

                        camp.addEnquiry(enquiry);

                    }
                }
            }
            excelFile.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading enquiries from excel file. Exception: " + e.getClass().getSimpleName()
                    + ", Message: " + e.getMessage());
        }

        return camp;
    }

    public static Camp loadSuggestions(Camp camp) {

        try {
            String filePath = "OOPproj2002/src/pkg_camp/suggestions.xlsx";
            Workbook workbook;

            // Check if the Excel file already exists
            File file = new File(filePath);
            if (!file.exists()) {
                return camp;
            }

            // The FileInputStream provides the necessary stream to read the bytes from the
            // file which is required for operations like reading an Excel workbook.

            FileInputStream excelFile = new FileInputStream(new File(filePath));

            // Upload --> Upload runtime memory to excel (Already have camps.xlsx, if not
            // create new one)
            // Download --> Download excel to runtime memory (Need to check if the excel
            // exists)

            workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheet("Suggestions");

            // Check if the file is empty
            if (sheet.getLastRowNum() <= 0) { // Assuming the header is in the first row
                excelFile.close();
                workbook.close();
                return camp;
            } else {
                System.out.println(sheet.getLastRowNum());
            }

            for (Row row : sheet) {
                // ignore the initial header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                else {
                    Suggestion suggestion = null;
                    boolean approval = false;

                    String campName = row.getCell(0).getStringCellValue();
                    String commMember = row.getCell(1).getStringCellValue();
                    String suggestionMsg = row.getCell(2).getStringCellValue();

                    Cell cell = row.getCell(3);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.BOOLEAN) {
                            approval = row.getCell(3).getBooleanCellValue();
                        }

                        else if (cell.getCellType() == CellType.STRING) {
                            String approvalStr = row.getCell(3).getStringCellValue();
                            if (approvalStr.equalsIgnoreCase("false")) {
                                approval = false;
                            } else if (approvalStr.equalsIgnoreCase("true")) {
                                approval = true;
                            }
                        }
                        suggestion = new Suggestion(commMember, campName, suggestionMsg, approval);

                        if (campName.equals(camp.getCampName())) {
                            camp.addSuggestion(suggestion);
                        }
                    }
                }
            }
            excelFile.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out
                    .println("Error loading suggestions from excel file. Exception: " + e.getClass().getSimpleName()
                            + ", Message: " + e.getMessage());
        }

        return camp;
    }

    // Get attributes of a user
    public static Object createUser(String username, String type) {

        boolean staff = false;
        String password = null;
        String faculty = null;

        try {

            FileInputStream fis = null;
            Workbook workbook = null;
            Sheet sheet = null;

            if (type.equals("STUDENT")) {
                fis = new FileInputStream("OOPproj2002/src/pkg_camp/student_list.xlsx");
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("student");
            } else if (type.equals("STAFF")) {
                fis = new FileInputStream("OOPproj2002/src/pkg_camp/staff_list.xlsx");
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("staff");
                staff = true;
            } else {
                System.out.println("Invalid type!");
                return null;
            }

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Get the username from the first cell of the current row
                Cell cell = row.getCell(1);
                String cellValue = cell.getStringCellValue();

                if (cellValue.contains("@")) {
                    String[] parts = cellValue.split("@");
                    cellValue = parts[0].trim();
                }

                // Check if the username matches the input
                if (cellValue.equals(username)) {

                    Cell facultyCell = row.getCell(2);
                    faculty = facultyCell.getStringCellValue();

                    fis.close();
                    workbook.close();
                }
            }

            fis.close();
            workbook.close();

            FileInputStream fisPW = new FileInputStream("OOPproj2002/src/pkg_camp/user_passwords.xlsx");
            Workbook workbookPW = new XSSFWorkbook(fisPW);
            Sheet sheetPW = workbookPW.getSheet("UserPasswords");

            Iterator<Row> rowIteratorPW = sheetPW.iterator();

            while (rowIteratorPW.hasNext()) {
                Row row = rowIteratorPW.next();

                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Get the username from the first cell of the current row
                Cell cell = row.getCell(0);
                String cellValue = cell.getStringCellValue();

                // Check if the username matches the input
                if (cellValue.equals(username)) {

                    Cell passwordCell = row.getCell(1);
                    password = passwordCell.getStringCellValue();

                    fisPW.close();
                    workbookPW.close();
                }
            }

            fisPW.close();
            workbookPW.close();

            if (staff) {
                Staff newstaff = new Staff(username, password, faculty);
                return newstaff;

            } else {
                Student newstudent = new CampCommitteeMember(username, password, faculty);
                return newstudent;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
