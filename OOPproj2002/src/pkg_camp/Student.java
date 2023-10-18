package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements User {
    private String userID;
    private String password;
    private String faculty;
    private UserType STUDENT;
    private StudentType studentType;
    private List<Enquiry> enquiries;
    private List<CampInformation> registeredCamps;
    private CampCommitteeRole campCommitteeRole;

    // Constructor
    public Student(String userID, String password, String faculty){
        
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        
        Scanner role = new Scanner(System.in);
        System.out.println("Enter Number for Role");
        System.out.println("[1] : Committee, [2] : Attendee");

        boolean condition = true;
        while (condition == true){
            try {
                int chosenRole = role.nextInt(); 
                condition = false;

                if (chosenRole == 1){ studentType = COMMITTEE;}

            } catch (Exception e) {
                System.out.println("Enter Valid Integer");
                System.out.println("[1] : Committee, [2] : Attendee");
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

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getFaculty() {
        return faculty;
    }

    public UserType getUserType() {
        return userType;
    }

    // Additional methods specific to Student

    public void enquiry(){

    }
}

