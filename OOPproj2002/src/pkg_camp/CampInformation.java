package pkg_camp;

import java.util.List;

import java.time.LocalDate;

// entity - stores information

public class CampInformation {

    private String campName;
    private LocalDate dates;
    private LocalDate registrationClosingDate;
    private String userGroup;
    private String location;
    protected int totalSlots;
    protected int campCommitteeSlots;
    private String description;
    private String staffInCharge;
    private boolean visibility;

    private List<Student> attendees;
    private List<Student> campCommittee;
    private List<Enquiry> enquiryList;
    private List<Suggestion> suggestionList;

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
        // this.enquiryList = new ArrayList<>();
        // this.suggestionList = new ArrayList<>();
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

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
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

    // Getter and setter methods for attendees
    public List<Student> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Student> studentList) {
        this.attendees = studentList;
    }

    public void addAttendee(Student attendee) {
        attendees.add(attendee);
    }

    public void removeAttendee(Student attendee) {
        attendees.remove(attendee);
    }

    // Getter and setter methods for camp committee members
    public List<Student> getCampCommittee() {
        return campCommittee;
    }

    public void setCampCommittee(List<Student> commList) {
        this.campCommittee = commList;
    }

    public void addCampCommitteeMember(Student committeeMember) {
        campCommittee.add(committeeMember);
    }

    public void removeCampCommitteeMember(Student committeeMember) {
        campCommittee.remove(committeeMember);
    }

    public List<Enquiry> getEnquiry() {
        return enquiryList;
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    public List<Suggestion> getSuggestion() {
        return suggestionList;
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    public void removeSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    public int getRemainingSlots() {
        int totalAttendees = getAttendees().size();
        int totalCommitteeMembers = getCampCommittee().size();

        return getTotalSlots() - totalAttendees - totalCommitteeMembers;
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
