package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp extends CampInformation {
    private List<Student> attendees;
    private List<Student> campCommittee;

    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, int userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffInCharge, visibility);
        attendees = new ArrayList<>();
        campCommittee = new ArrayList<>();
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public List<Student> getCampCommittee() {
        return campCommittee;
    }

    @Override
    public void generateReports(String staffInCharge, String campName) {
        // Implement report generation logic for a specific camp filtered by staffInCharge and campName.
    }

    @Override
    public void generateReports(CampInformation camp) {
        // Implement report generation logic for a specific camp committee member.
    }
}