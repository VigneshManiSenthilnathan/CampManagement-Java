package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements User {
    private String userID;
    private String password;
    private String faculty;
    private User STUDENT;
    private StudentType studentType;
    private List<Enquiry> enquiries;
    private List<Camp> registeredCamps;
    private CampCommitteeMember campCommitteeRole;

    public Student(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.enquiries = new ArrayList<>();
        this.registeredCamps = new ArrayList<>();

        // Here, you can set the student type based on user input
        Scanner roleScanner = new Scanner(System.in);
        System.out.println("Enter Number for Role");
        System.out.println("[1] : Committee, [2] : Attendee");

        boolean condition = true;
        while (condition) {
            try {
                int chosenRole = roleScanner.nextInt();
                if (chosenRole == 1) {
                    studentType = StudentType.COMMITTEE;

                } else if (chosenRole == 2) {
                    studentType = StudentType.ATTENDEE;
                } else {
                    System.out.println("Invalid role selection. Please enter 1 or 2.");
                    continue; // Repeat the loop
                }
                condition = false;
                roleScanner.close();
            } catch (Exception e) {
                System.out.println("Enter Valid Integer");
                System.out.println("[1] : Committee, [2] : Attendee");
                roleScanner.nextLine(); // Consume the invalid input
            }
        }
    }

    enum StudentType {
        ATTENDEE, COMMITTEE
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

    public User getUserClass() {
        return STUDENT;
    }

    // Enquiry Methods

    // Input decides whether staff/campComm receives it
    public void newEnquiry(User receiver) {

        Scanner msg = new Scanner(System.in);
        String message = msg.nextLine();
        Enquiry enquiry = new Enquiry(this, receiver, message);
    }

    // Method to get the list of enquiries
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    // Method to edit an existing enquiry
    public void editEnquiry(Enquiry enquiry, String newMessage) {

        int index = enquiries.indexOf(enquiry);
        if (index != -1) {
            System.out.println("Original Message: " + enquiries.get(index).getMessage());
            enquiries.get(index).setMessage(newMessage);
        }
    }

    // Method to delete an enquiry
    public void deleteEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    // New camp methods
    public void viewCamps(List<Camp> createdCamps) {
        int i = 1;
        for (Camp camp : createdCamps) {
            if (camp.getUserGroup() == this.getFaculty()) {
                System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription());
            }
            i++;
        }
    }

    public void registerForCamp(){
        // Showing available camps
        while(i < createdCamps.size()) {
            if ()
            i++;
        }

        // How can we show available camps? Need to allow access to excel?
    }

}
