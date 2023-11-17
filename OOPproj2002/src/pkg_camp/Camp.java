package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp extends CampInformation {

    private List<Student> attendees;
    private List<Student> campCommittee;
    private static List<Camp> createdCampsList = new ArrayList<>();

    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description,
                staffInCharge, visibility);
        this.attendees = new ArrayList<>();
        this.campCommittee = new ArrayList<>();
    }

    // Overloaded Constructor
    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility,
            List<Student> attendees) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description,
                staffInCharge, visibility);
        this.attendees = attendees;
        this.campCommittee = new ArrayList<>();
    }

    // Overloaded Constructor
    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility,
            List<Student> attendees, List<Student> campCommittee) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description,
                staffInCharge, visibility);
        this.attendees = attendees;
        this.campCommittee = campCommittee;
    }

    public static List<Camp> getCreatedCampsList() {
        return createdCampsList;
    }

    public static void setCreatedCampsList(List<Camp> newcreatedCampsList) {
        createdCampsList = newcreatedCampsList;
    }

    public String getCampName() {
        return super.getCampName();
    }

    public void setCampName(String campName) {
        super.setCampName(campName);
    }

    public LocalDate getDates() {
        return super.getDates();
    }

    public void setDates(LocalDate dates) {
        super.setDates(dates);
    }

    public LocalDate getRegistrationClosingDate() {
        return super.getRegistrationClosingDate();
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        super.setRegistrationClosingDate(registrationClosingDate);
    }

    public String getUserGroup() {
        return super.getUserGroup();
    }

    public void setUserGroup(String userGroup) {
        super.setUserGroup(userGroup);
    }

    public String getLocation() {
        return super.getLocation();
    }

    public void setLocation(String location) {
        super.setLocation(location);
    }

    public int getTotalSlots() {
        return super.getTotalSlots();
    }

    public void setTotalSlots(int totalSlots) {
        super.setTotalSlots(totalSlots);
    }

    public int getCampCommitteeSlots() {
        return super.getCampCommitteeSlots();
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        super.setCampCommitteeSlots(campCommitteeSlots);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public String getStaffInCharge() {
        return super.getStaffInCharge();
    }

    public void setStaffInCharge(String staffInCharge) {
        super.setStaffInCharge(staffInCharge);
    }

    public List<Enquiry> getEnquiries() {
        return super.getEnquiry();
    }

    public void addEnquiries(Enquiry enquiries) {
        super.addEnquiry(enquiries);
    }

    public void removeEnquiry(Enquiry enquiry) {
        super.removeEnquiry(enquiry);
    }

    public List<Suggestion> getSuggestions() {
        return super.getSuggestion();
    }

    public void addSuggestions(Suggestion suggestions) {
        super.addSuggestion(suggestions);
    }

    public void removeSuggestion(Suggestion suggestion) {
        super.removeSuggestion(suggestion);
    }

    // Override methods to get, add, and remove attendees and camp committee members
    @Override
    public List<Student> getAttendees() {
        return super.getAttendees();
    }

    @Override
    public void addAttendee(Student attendee) {
        attendees.add(attendee);
    }

    @Override
    public void removeAttendee(Student attendee) {
        attendees.remove(attendee);
    }

    // For use in CampController class, withdrawCamp method
    // We remove student from attendee list using student.getUserID()
    public void removeAttendee(String studentUserID) {
        for (Student attendee : attendees) {
            if (attendee.getUserID().equals(studentUserID)) {
                attendees.remove(attendee);
                break;
            }
        }
    }

    @Override
    public List<Student> getCampCommittee() {
        return super.getCampCommittee();
    }

    @Override
    public void addCampCommitteeMember(Student committeeMember) {
        // System.out.println(committeeMember.getUserID());
        campCommittee.add(committeeMember);
    }

    @Override
    public void removeCampCommitteeMember(Student committeeMember) {
        campCommittee.remove(committeeMember);
    }

    // For use in Upload class
    public List<String> getAttendeeUserID() {
        // load student userID into a string list
        ArrayList<String> attendeeNames = new ArrayList<>();

        // System.out.println("reached!");

        for (Student attendee : attendees) {
            attendeeNames.add(attendee.getUserID());
        }

        return attendeeNames;
    }

    public List<String> getCommiteeUserID() {
        // load student userID into a string list
        ArrayList<String> commiteeNames = new ArrayList<>();

        // System.out.println("reached!");

        for (Student member : campCommittee) {
            commiteeNames.add(member.getUserID());
        }

        return commiteeNames;
    }

    public void removeCampCommitteeMember(String committeeMemberName) {
        for (Student committeeMember : campCommittee) {
            if (committeeMember.getUserID().equals(committeeMemberName)) {
                campCommittee.remove(committeeMember);
                break;
            }
        }
    }

}
