package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff implements User {

    // Attributes of Staff
    private final String userID;
    private String password;
    private String faculty;
    private User STAFF;
    private ArrayList<Enquiry> enquiries; // enquires related to THIS staff

    // Attributes of Staff Methods
    private List<String> createdCampName;
    private List<CampInformation> createdCamps;
    private List<Enquiry> enquiriesHandling;

    // Constructor
    public Staff(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        // this.enquiriesHandling = new ArrayList<>();
    }
    // Implement the methods from the User interface

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFaculty() {
        return faculty;
    }

    public User getUserType() {
        return STAFF;
    }

    public void generateReports(String staffInCharge, String attendeeType, String outputFileFormat) {
        /*
         * List<Camp> campsCreatedByStaff = getCampsCreatedByStaff(staffInCharge);
         * 
         * for (Camp camp : campsCreatedByStaff) {
         * List<Participant> participants = camp.getParticipantsByType(attendeeType);
         * 
         * // Generate the report content
         * StringBuilder reportContent = new StringBuilder();
         * reportContent.append("Camp Name: ").append(camp.getName()).append("\n");
         * reportContent.append("Camp Date: ").append(camp.getDate()).append("\n");
         * reportContent.append("Location: ").append(camp.getLocation()).append("\n");
         * reportContent.append("Organizer: ").append(camp.getOrganizer()).append("\n");
         * 
         * for (Participant participant : participants) {
         * reportContent.append("Participant Name: ").append(participant.getName()).
         * append("\n");
         * reportContent.append("Participant Role: ").append(participant.getRole()).
         * append("\n");
         * // Add more participant details as needed
         * 
         * // Add a separator between participants
         * reportContent.append("------------------------\n");
         * }
         * 
         * // Output the report to a file in the specified format (txt or csv)
         * String outputFileName = camp.getName() + "_attendance_report." +
         * outputFileFormat;
         * try (BufferedWriter writer = new BufferedWriter(new
         * FileWriter(outputFileName))) {
         * writer.write(reportContent.toString());
         * System.out.println("Attendance report generated: " + outputFileName);
         * } catch (IOException e) {
         * e.printStackTrace();
         * System.err.println("Error: Unable to write the attendance report.");
         * }
         */
    }

    public void enquiriesHandling() {

    }

    public void viewAndReplyToEnquiries() {
        // Iterate through the enquiries and display them
        for (Enquiry enquiry : enquiries) {
            System.out.println("Sender: " + enquiry.getSender().getUserID());
            System.out.println("Message: " + enquiry.getMessage());

            Scanner input = new Scanner(System.in);
            String replyMsg = input.nextLine();

            enquiry.setReply(replyMsg);
        }
    }

    public void campCommitteeApproval(Student student) {

    }

    /*
     * public void campCommitteeManagement(){
     * 
     * }
     */
}