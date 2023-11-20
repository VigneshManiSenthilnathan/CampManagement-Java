package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Enquiry {

    // attributes of an enquiry
    // student who submitted the enquiry and enquiry details (String)
    private String studentID;
    private String campName;
    private String enquiryString;
    private String repliedBy;
    private String replierType; // "STAFF" or "CAMP_COMMITTEE_MEMBER"
    private String reply;

    public Enquiry(String studentID, String campName, String enquiryString) {
        this.studentID = studentID;
        this.enquiryString = enquiryString;
        this.enquiryString = enquiryString;
        this.repliedBy = null;
        this.replierType = null;
        this.reply = null;
    }

    public Enquiry(String studentID, String campName, String enquiryString, String repliedBy, String replierType,
            String reply) {
        this.studentID = studentID;
        this.enquiryString = enquiryString;
        this.enquiryString = enquiryString;
        this.repliedBy = repliedBy;
        this.replierType = replierType;
        this.reply = reply;
    }

    public String getEnquirySender() {
        return studentID;
    }

    public String getEnquiryString() {
        return enquiryString;
    }

    public void editEnquiryString(String edit) {
        this.enquiryString = edit;
    }

    // Getter Methods
    public String getStudentID() {
        return studentID;
    }

    public String getCampName() {
        return campName;
    }

    public String getEnquiry() {
        return enquiryString;
    }

    public String getRepliedBy() {
        return repliedBy;
    }

    public String getReplierType() {
        return replierType;
    }

    public String getReply() {
        return reply;
    }

    // Setter Methods

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setEnquiryString(String enquiryString) {
        this.enquiryString = enquiryString;
    }

    public void setRepliedBy(String repliedBy) {
        this.repliedBy = repliedBy;
    }

    public void setReplierType(String replierType) {
        // either "STAFF" or "CAMP_COMMITTEE_MEMBER" only!!
        this.replierType = replierType;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}

// Check if the Excel file already exists
/*
 * File file = new File(excelFilePath);
 * if (file.exists()) {
 * try {
 * workbook = WorkbookFactory.create(file);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return;
 * }
 * } else {
 * // Create a new Excel workbook
 * workbook = new XSSFWorkbook();
 * }
 * 
 * sheet = workbook.getSheet("Enquiries");
 * if (sheet == null) {
 * // Create a new sheet for enquiries
 * sheet = workbook.createSheet("Enquiries");
 * }
 * }
 * 
 * 
 * public void addEnquiry(String sender, String receiver, String enquiryMessage)
 * {
 * Row row = sheet.createRow(sheet.getLastRowNum() + 1); // Create a new row
 * 
 * // Create cells for sender, receiver, enquiry message (reply initially empty)
 * row.createCell(0).setCellValue(sender);
 * row.createCell(1).setCellValue(receiver);
 * row.createCell(2).setCellValue(enquiryMessage);
 * row.createCell(3).setCellValue(""); // Initialize reply as an empty string
 * }
 * 
 * public void addReply(String sender, String enquiryMessage, String reply) {
 * 
 * boolean found = false;
 * 
 * for (Row row : sheet) {
 * Cell senderCell = row.getCell(0);
 * Cell enquiryMessageCell = row.getCell(2);
 * 
 * if (senderCell.getStringCellValue().equals(sender)
 * && enquiryMessageCell.getStringCellValue().equals(enquiryMessage)) {
 * found = true;
 * Cell replyCell = row.getCell(3);
 * replyCell.setCellValue(reply);
 * break; // Exit the loop once the reply is added
 * }
 * 
 * if (!found) {
 * System.out.println("No such enquiry!");
 * }
 * }
 * }
 * 
 * public void writeExcelFile() {
 * try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
 * workbook.write(outputStream);
 * System.out.println("Excel file written successfully.");
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * // returns a list of rows
 * public List<String> getEnquiriesBySender(String sender) {
 * 
 * boolean found = false;
 * 
 * List<String> matchingEnquiries = new ArrayList<String>();
 * 
 * for (Row row : sheet) {
 * Cell senderCell = row.getCell(0);
 * if (senderCell.getStringCellValue().equals(sender)) {
 * found = true;
 * matchingEnquiries.add(row.getCell(2).toString());
 * }
 * }
 * 
 * if (!found) {
 * System.out.println("No student by the name of " + sender + "!");
 * return null;
 * }
 * 
 * return matchingEnquiries;
 * }
 * 
 * // returns a list of rows
 * public List<String> getEnquiriesByReceiver(String receiver) {
 * 
 * boolean found = false;
 * 
 * List<String> matchingEnquiries = new ArrayList<String>();
 * 
 * for (Row row : sheet) {
 * Cell receiverCell = row.getCell(1);
 * if (receiverCell.getStringCellValue().equals(receiver)) {
 * found = true;
 * matchingEnquiries.add(row.getCell(2).toString());
 * }
 * }
 * 
 * if (!found) {
 * System.out.println("No enquiries for " + receiver + "!");
 * }
 * 
 * return matchingEnquiries;
 * }
 * 
 * public void close() {
 * try {
 * workbook.close();
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * }
 */