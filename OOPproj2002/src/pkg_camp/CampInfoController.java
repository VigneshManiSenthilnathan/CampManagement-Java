package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampInfoController extends CampInformation {
    private List<Student> attendees;
    private List<Student> campCommittee;
    // private List<String> createdCampName;
    private List<CampInfoController> createdCamps;

    public CampInfoController() {

    }

    public CampInfoController(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup,
            String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge,
            boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description, staffInCharge, visibility);
        // this.createdCampName = new ArrayList<>();
        // this.createdCamps = new ArrayList<>();
        attendees = new ArrayList<>();
        campCommittee = new ArrayList<>();
    }

    // Getter Methods
    public String getCampName() {
        return super.getCampName();
    }

    public LocalDate getCampDate() {
        return super.getDates();
    }

    public LocalDate getClosing() {
        return super.getRegistrationClosingDate();
    }

    public String getFaculty() {
        return super.getUserGroup();
    }

    public String getLocation() {
        return super.getLocation();
    }

    public int getTotalSlots() {
        return super.getTotalSlots();
    }

    public int getCommitteeSlots() {
        return super.getCampCommitteeSlots();
    }

    public String getDescription() {
        return super.getDescription();
    }

    public String getStaff() {
        return super.getStaffInCharge();
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public boolean getVisibility() {
        return super.getVisibility();
    }

    public List<String> getAttendeeUserID() {

        List<String> campAttendees = new ArrayList<String>();

        for (Student attendee : attendees) {
            campAttendees.add(attendee.getUserID());
        }

        return campAttendees;
    }

    public String getAttendeeUserID(String attendeeUserID) {
        for (Student attendee : attendees) {
            if (attendee.getUserID().equals(attendeeUserID)) {
                return attendee.getUserID();
            }
        }

        System.out.println("UserID not found in attendees list!");
        return null; // Return null if the attendee with the specified name is not found
    }

    public List<Student> getCampCommittee() {
        return campCommittee;
    }

    public List<String> getCampCommitteeUserID() {

        List<String> campCommitteeUserID = new ArrayList<String>();

        for (Student campcommittee : campCommittee) {
            campCommitteeUserID.add(campcommittee.getUserID());
        }

        return campCommitteeUserID;
    }

    public String getCampCommitteeUserID(String campCommitteeUserID) {
        for (Student campcommittee : campCommittee) {
            if (campcommittee.getUserID().equals(campCommitteeUserID)) {
                return campcommittee.getUserID();
            }
        }

        System.out.println("UserID not found in Camp Committee list!");
        return null; // Return null if the attendee with the specified name is not found
    }

    /*
     * public List<CampInfoController> getCreatedCamps() {
     * return createdCamps;
     * }
     */

    public void addAttendee(Student student) {
        attendees.add(student);
    }

    public void removeAttendee(Student student) {
        attendees.remove(student);
    }

    public void addCampCommitteeMember(Student student) {
        campCommittee.add(student); // Add the student to the camp committee list
    }

    public void createCamp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup,
            String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge,
            boolean visibility) {
        CampInfoController newCamp = new CampInfoController(campName, dates, registrationClosingDate, userGroup,
                location, totalSlots,
                campCommitteeSlots, description, staffInCharge, visibility);

        // createdCamps.add(newCamp);
        // createdCampName.add(newCamp.getCampName());
    }

    @Override
    public void generateReports(String staffInCharge, String campName) {
        // Implement report generation logic for a specific camp filtered by
        // staffInCharge and campName.
    }

    @Override
    public void generateReports(CampInformation camp) {
        // Implement report generation logic for a specific camp committee member.
    }
}