package pkg_camp;

import java.util.List;

public class Staff extends CampInformation implements User {

    private String userID;
    private String password;
    private String faculty;
    private UserType userType;
    private List<Camp> createdCamps;
    private List<Enquiry> enquiriesHandling;
    private CampCommitteeManagement campCommitteeManagement;

    // Constructor and methods for Staff class
    // Implement the methods from the User interface

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public UserType getUserType() {
        return userType;
    }

    // Additional methods specific to Staff

}
