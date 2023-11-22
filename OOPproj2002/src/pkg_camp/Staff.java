package pkg_camp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//extends camp for now, might need to remove if got better options
public class Staff implements User {

    // Attributes of Staff
    private String userID;
    private String password;
    private String faculty;
    private User STAFF;
    private ArrayList<EnquiryController> enquiries; // enquires related to THIS staff
    private SuggestionController suggestionController;

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
        // suggestionsController = new SuggestionsController();
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

    @Override
    public String getUserType() {
        return "Staff";
    }

    public SuggestionController getSuggestionsController() {
        return suggestionController;
    }

    /*
     * public void viewAndApproveSuggestions() {
     * List<SuggestionController> suggestion =
     * suggestionController.getSuggestionList();
     * 
     * if (suggestion.isEmpty()) {
     * System.out.println("No suggestions available for review.");
     * } else {
     * System.out.println("List of Suggestions:");
     * for (int i = 0; i < suggestion.size(); i++) {
     * SuggestionController.Suggestion suggestion = suggestion.get(i);
     * System.out.println((i + 1) + ". Camp Name: " + suggestion.getCampName());
     * System.out.println("   Suggested Details: " +
     * suggestion.getSuggestedDetails());
     * System.out.println("   Status: " + (suggestion.isAccepted() ? "Accepted" :
     * "Pending"));
     * }
     * 
     * Scanner scanner = new Scanner(System.in);
     * System.out.
     * println("Enter the number of the suggestion to approve (or 0 to exit):");
     * int choice = scanner.nextInt();
     * 
     * if (choice > 0 && choice <= suggestion.size()) {
     * SuggestionController.Suggestion selectedSuggestion = suggestion.get(choice -
     * 1);
     * selectedSuggestion.acceptSuggestion();
     * System.out.println("Suggestion approved successfully!");
     * } else if (choice != 0) {
     * System.out.println("Invalid choice. Please try again.");
     * }
     * }
     * }
     */

    public void enquiriesHandling() {

        // Iterate through the enquiries and display them
        for (

        EnquiryController enquiry : enquiries) {
            System.out.println("Sender: " + enquiry.getSender().getUserID());
            System.out.println("Message: " + enquiry.getMessage());

            Scanner input = new Scanner(System.in);
            String replyMsg = input.nextLine();
            input.close();

            enquiry.setReply(replyMsg);
        }
    }

    public void campCommitteeRejection(String studentName, Camp camp) {

        // sanity check for whether student is in the camp committee
        boolean studentInCampCommittee = false;
        for (Student committeeMember : camp.getCampCommittee()) {
            if (committeeMember.getUserID().equals(studentName)) {
                studentInCampCommittee = true;
                return;
            }
        }

        if (!studentInCampCommittee) {
            System.out.println("Student is not in the camp committee!");
            return;
        }
        
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        
        for (Camp thisCamp : createdCampsList) {
            if (thisCamp.getCampName().equals(camp.getCampName())) {
                thisCamp.removeCampCommitteeMember(studentName);
                return;
            }
        }
    }
}