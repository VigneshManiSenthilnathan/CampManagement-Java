package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp extends CampInformation {

    private ArrayList<Student> attendees;
    private ArrayList<Student> campCommittee;

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
            ArrayList<Student> attendees) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description,
                staffInCharge, visibility);
        this.attendees = attendees;
        this.campCommittee = new ArrayList<>();
    }

    // Overloaded Constructor
    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility,
            ArrayList<Student> attendees, ArrayList<Student> campCommittee) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description,
                staffInCharge, visibility);
        this.attendees = attendees;
        this.campCommittee = campCommittee;
    }

    // Override methods to get, add, and remove attendees and camp committee members
    @Override
    public ArrayList<Student> getAttendees() {
        return attendees;
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
        return campCommittee;
    }

    @Override
    public void addCampCommitteeMember(Student committeeMember) {
        campCommittee.add(committeeMember);
    }

    @Override
    public void removeCampCommitteeMember(Student committeeMember) {
        campCommittee.remove(committeeMember);
    }

    // For use in Upload class
    public ArrayList<String> getAttendeeUserID() {
        // load student userID into a string list
        ArrayList<String> attendeeNames = new ArrayList<>();

        // System.out.println("reached!");

        for (Student attendee : attendees) {
            attendeeNames.add(attendee.getUserID());
        }

        return attendeeNames;
    }
}
