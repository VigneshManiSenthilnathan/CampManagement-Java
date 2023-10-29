package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff implements User {

    //Attributes of Staff
    private final String userID;
    private String password;
    private String faculty;
    private User STAFF;
    private ArrayList<Enquiry> enquiries; //enquires related to THIS staff

    //Attributes of Staff Methods
    private List<String> createdCampName;
    private List<CampInformation> createdCamps;
    private List<Enquiry> enquiriesHandling;
    private CampCommitteeManagement campCommitteeManagement;

    // Constructor
    public Staff(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.createdCampName = new ArrayList<>();
        this.createdCamps = new ArrayList<>();
        //this.enquiriesHandling = new ArrayList<>();
    }

    // Implement the methods from the User interface

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getFaculty() {
        return faculty;
    }

    public User getUserType(){
        return STAFF;
    }

    // Additional methods specific to Staff

    public void createCamp(String campName, LocalDate dates, LocalDate registrationClosingDate, int userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {

        Camp newCamp = new Camp(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge, visibility);

        createdCamps.add(newCamp);
        createdCampName.add(newCamp.getCampName(staffInCharge));
    }

    public void generateReports(String staffInCharge, String campName){
        // I think we just use excel java input here?
        // instead of using any super keywords, dk if thats allowed
        // https://towardsdatascience.com/replacing-vba-with-java-in-excel-e9f5e28d4e5c
    }

    public void enquiriesHandling(){

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

    public void campCommitteeApproval(Student student){
        
    }

    public void campCommitteeManagement(){

    }
}