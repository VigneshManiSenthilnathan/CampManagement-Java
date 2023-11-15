package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp extends CampInformation {

    private ArrayList<Student> attendees;
    private ArrayList<Student> campCommittee;

    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description,
                staffInCharge, visibility);
        this.attendees = new ArrayList<>();
        this.campCommittee = new ArrayList<>();
    }

    
    // Override methods to get, add, and remove attendees and camp committee members
    @Override
    public List<Student> getAttendees() {
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
}
