package pkg_camp;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CampInformation {

    private String campName;
    private LocalDate dates;
    private LocalDate registrationClosingDate;
    private static String userGroup;
    private String location;
    protected int totalSlots;
    protected int campCommitteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;

    private ArrayList<Student> attendees;
    private ArrayList<Student> committee;

    public CampInformation() {

    }

    public CampInformation(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup,
            String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge,
            boolean visibility) {
        this.campName = campName;
        this.dates = dates;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
    }

    /*
     * Shall we use an index here?
     * int parameter to represent attribute to edit
     * (i.e.) 0 = campName, 1 = dates ...
     */

    // someone suggest if got better implementation pls

    public static void toggleVisibility(CampInformation camp, int set) {
        if (set == 1) {
            camp.visibility = true;
        } else if (set == 0) {
            camp.visibility = false;
        } else {
            System.out.println("Invalid integer input!");
            System.out.println("To Set Visible Enter: 1");
            System.out.println("To Set Invisible Enter: 0");
        }
    }

    // Setter methods for CampInformation attributes
    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setDates(LocalDate campDate) {
        this.dates = campDate;
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    // Getter methods for CampInformation attributes
    public String getCampName() {
        return campName;
    }

    public LocalDate getDates() {
        return dates;
    }

    public LocalDate getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public boolean getVisibility() {
        return visibility;
    }

    // void return as reports are generated in excel
    public void generateReports() {
        // generate the whole excel sheet at one go
    }

    // overloaded method
    public void generateReports(String staffInCharge) {
        // generate camp details filtered by staff
    }

    // abstract method for staff
    public void generateReports(String staffInCharge, String campName) {
    }

    // abstract method for camp committee members
    public void generateReports(CampInformation camp) {
    }

}
