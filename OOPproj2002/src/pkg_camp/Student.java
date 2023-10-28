package sce.sc2002.yys.proj;

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
    private List<CampInformation> registeredCamps;
    private CampCommitteeRole campCommitteeRole;

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

    // Additional methods specific to Student

    public void enquiry() {
        // Implement the logic for making an enquiry.
    }
}
