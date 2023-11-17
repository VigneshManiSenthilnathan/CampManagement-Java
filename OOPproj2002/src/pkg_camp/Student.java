package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements User {
    private String userID;
    private String password;
    private String faculty;
    // private boolean isCampCommitteeMember;

    public Student() {
    }

    public Student(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = "password";
        this.faculty = faculty;
        // this.isCampCommitteeMember = false;
    }

    // Implement the methods from the User interface
    public String getUserID() {
        return userID;
    }

    @Override
    public String getUserType() {
        return "STUDENT";
    }

    public String getPassword() {
        return password;
    }

    /*
     * public boolean isCampCommitteeMember() {
     * return isCampCommitteeMember;
     * }
     */

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFaculty() {
        return faculty;
    }

    /*
     * public void setRole(int role) {
     * if (role == 1) {
     * this.isCampCommitteeMember = true;
     * } else {
     * this.isCampCommitteeMember = false;
     * }
     * }
     * 
     * public boolean getRole() {
     * return isCampCommitteeMember;
     * }
     * 
     * public boolean campCommitteeStatus(Student student, boolean set) {
     * return this.isCampCommitteeMember = set;
     * }
     */
}
