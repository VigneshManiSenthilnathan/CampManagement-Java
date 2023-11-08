package pkg_camp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//extends camp for now, might need to remove if got better options
public class Staff extends CampInfoController implements User {

    // Attributes of Staff
    private String userID;
    private String password;
    private String faculty;
    private User STAFF;
    private ArrayList<EnquiryController> enquiries; // enquires related to THIS staff

    // Attributes of Staff Methods
    private List<String> createdCampName;
    private List<CampInformation> createdCamps;
    private List<EnquiryController> enquiriesHandling;

    // Constructor
    public Staff() {

    }

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

    public void generateReports(List<CampInfoController> camps, String attendeeType, String outputFileFormat) {
        for (CampInfoController camp : camps) {
            // Check if the camp was created by this staff member
            if (camp.getStaffInCharge().equals(this.getUserID())) {
                // Filter participants based on attendee type
                List<Student> participants;
                if ("attendee".equals(attendeeType)) {
                    participants = camp.getAttendees();
                } else if ("campCommittee".equals(attendeeType)) {
                    participants = camp.getCampCommittee();
                } else {
                    // Handle other attendee types or show an error message
                    continue;
                }

                // Generate the report content
                StringBuilder reportContent = new StringBuilder();
                reportContent.append("Camp Name: ").append(camp.getCampName()).append("\n");
                reportContent.append("Camp Date: ").append(camp.getDates()).append("\n");
                reportContent.append("Location: ").append(camp.getLocation()).append("\n");
                reportContent.append("Description: ").append(camp.getDescription()).append("\n");
                reportContent.append("Staff in Charge: ").append(camp.getStaffInCharge()).append("\n");

                // might need to have a getter method for this

                // Add participants to the report
                for (Student participant : participants) {
                    reportContent.append("Participant Name: ").append(participant.getUserID()).append("\n");
                    // might need to have a getter method for this

                    reportContent.append("Participant Role: ").append(participant.getStudentType().toString())
                            .append("\n");
                    // might need to have a getter method for this

                    // Add more participant details as needed

                    // Add a separator between participants
                    reportContent.append("------------------------\n");
                }

                // Output the report to a file in the specified format (txt or csv)
                String outputFileName = camp.getCampName() + "_attendance_report." + outputFileFormat;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    writer.write(reportContent.toString());
                    System.out.println("Attendance report generated: " + outputFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error: Unable to write the attendance report.");
                }
            }
        }
    }

    public void generatePerformanceReport(List<CampInfoController> camps, String outputFileFormat) {
        StringBuilder reportContent = new StringBuilder();

        for (CampInfoController camp : camps) {
            // Check if the camp was created by this staff member and has a camp committee
            if (camp.getStaffInCharge().equals(this) && !camp.getCampCommittee().isEmpty()) {
                reportContent.append("Camp Name: ").append(camp.getCampName()).append("\n");
                reportContent.append("Camp Date: ").append(camp.getDates()).append("\n");
                reportContent.append("Location: ").append(camp.getLocation()).append("\n");

                // Add camp committee members to the report
                List<Student> campCommitteeMembers = camp.getCampCommittee();
                for (Student committeeMember : campCommitteeMembers) {

                    // same getter method
                    reportContent.append("Committee Member Name: ").append(committeeMember.getUserID()).append("\n");
                    // Add more committee member details as needed

                    // Add a separator between committee members
                    reportContent.append("------------------------\n");
                }

                // Add a separator between camps
                reportContent.append("========================================\n");
            }
        }

        // Output the performance report to a file in the specified format (txt or csv)
        String outputFileName = "camp_committee_performance_report." + outputFileFormat;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(reportContent.toString());
            System.out.println("Performance report generated: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to write the performance report.");
        }
    }

    public void enquiriesHandling() {

    }

    public void viewAndReplyToEnquiries() {
        // Iterate through the enquiries and display them
        for (EnquiryController enquiry : enquiries) {
            System.out.println("Sender: " + enquiry.getSender().getUserID());
            System.out.println("Message: " + enquiry.getMessage());

            Scanner input = new Scanner(System.in);
            String replyMsg = input.nextLine();
            input.close();

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