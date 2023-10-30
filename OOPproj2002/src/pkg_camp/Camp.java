package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp extends CampInformation {
    private List<Student> attendees;
    private List<Student> campCommittee;
    private List<String> createdCampName;
    private List<CampInformation> createdCamps;

    public Camp(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description, staffInCharge, visibility);
        this.createdCampName = new ArrayList<>();
        this.createdCamps = new ArrayList<>();
        attendees = new ArrayList<>();
        campCommittee = new ArrayList<>();
    }

    public void createCamp(String campName, LocalDate dates, LocalDate registrationClosingDate, int userGroup,
            String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge,
            boolean visibility) {
        Camp newCamp = new Camp(campName, dates, registrationClosingDate, userGroup, location, totalSlots,
                campCommitteeSlots, description, staffInCharge, visibility);

        createdCamps.add(newCamp);
        createdCampName.add(newCamp.getCampName(staffInCharge));
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public List<Student> getCampCommittee() {
        return campCommittee;
    }

    public List<CampInformation> getCreatedCamps() {
        return createdCamps;
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