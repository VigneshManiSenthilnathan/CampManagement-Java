package pkg_camp;

public class EnquiryController {

    public static void viewEnquiry(Student student) {
        ViewEnquiry.viewEnquiry(student);
    }

    public static void viewEnquiry(Staff staff) {
        ViewEnquiry.viewEnquiry(staff);
    }

    public static void viewEnquiry(CampCommitteeMember campCommitteeMember, Camp camp) {
        ViewEnquiry.viewEnquiry(campCommitteeMember, camp);
    }

    public static void viewOwnEnquiry(CampCommitteeMember campCommitteeMember){
        ViewEnquiry.viewOwnEnquiry(campCommitteeMember);
    }

    public static void submitEnquiry(Student student) {
        SubmitEnquiry.submitEnquiry(student);
    }

    public static void submitEnquiry(CampCommitteeMember campCommitteeMember) {
        SubmitEnquiry.submitEnquiry(campCommitteeMember);
    }

    public static void modifyEnquiry(Student student) {
        ModifyEnquiry.modifyEnquiry(student);
    }

    public static void modifyEnquiry(CampCommitteeMember campCommitteeMember) {
        ModifyEnquiry.modifyEnquiry(campCommitteeMember);
    }

    public static void deleteEnquiry(Student student) {
        DeleteEnquiry.deleteEnquiry(student);
    }

    public static void deleteEnquiry(CampCommitteeMember campCommitteeMember) {
        DeleteEnquiry.deleteEnquiry(campCommitteeMember);
    }

    public static void replyEnquiry(CampCommitteeMember campCommitteeMember, Camp camp) {
        ReplyEnquiry.replyEnquiry(campCommitteeMember, camp);
    }

    public static void replyEnquiry(Staff staff) {
        ReplyEnquiry.replyEnquiry(staff);
    }

}

/*
 * // Check if the Excel file already exists
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
 * 
 * // Old implementation below v
 * public class EnquiryController {
 * private Student sender;
 * private User receiver; // User can be either Staff or CampCommitteeMember
 * private String message;
 * private String reply;
 * 
 * public EnquiryController(Student sender, User receiver, String message) {
 * this.sender = sender;
 * this.receiver = receiver;
 * this.message = message;
 * }
 * 
 * // Getters and Setters
 * public Student getSender() {
 * return sender;
 * }
 * 
 * public void setSender(Student sender) {
 * this.sender = sender;
 * }
 * 
 * public User getReceiver() {
 * return receiver;
 * }
 * 
 * public void setReceiver(User receiver) {
 * this.receiver = receiver;
 * }
 * 
 * public String getMessage() {
 * return message;
 * }
 * 
 * public void setMessage(String message) {
 * this.message = message;
 * }
 * 
 * public String getReply() {
 * return reply;
 * }
 * 
 * public void setReply(String reply) {
 * this.reply = reply;
 * }
 * 
 * @Override
 * public String toString() {
 * return "Sender: " + sender.getUserID() + "\nReceiver: " +
 * receiver.getUserID() + "\nMessage: " + message
 * + "\nReply: " + reply;
 * }
 * }
 */
