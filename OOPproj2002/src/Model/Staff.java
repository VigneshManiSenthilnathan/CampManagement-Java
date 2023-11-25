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

    // Attributes of Staff Methods
    // Constructor
    public Staff() {

    }

    public Staff(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
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
}