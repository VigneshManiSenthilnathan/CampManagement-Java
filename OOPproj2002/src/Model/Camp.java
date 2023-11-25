package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp {

    private List<Student> attendeesList;
    private List<CampCommitteeMember> campCommitteeList;
    private List<Enquiry> enquiryList;
    private List<Suggestion> suggestionList;
    private CampInformation campInformation;
    private List<String> blackList;

    public Camp(CampInformation campInformation) {
        this.campInformation = campInformation;
        this.attendeesList = new ArrayList<>();
        this.campCommitteeList = new ArrayList<>();
        this.enquiryList = new ArrayList<>();
        this.suggestionList = new ArrayList<>();
        this.blackList = new ArrayList<>();
    }

    // methods to get, add, and remove blacklist

    public List<String> getBlackList() {
        return blackList;
    }

    public void addBlackList(String student) {
        blackList.add(student.toUpperCase());
    }

    public void delBlackList(String student) {
        blackList.remove(student.toUpperCase());
    }

    // methods to get, add, and remove enquiries

    public List<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
    }

    public void delEnquiry(Enquiry enquiry) {
        enquiryList.remove(enquiry);
    }

    // methods to get, add, and remove suggestions
    public List<Suggestion> getSuggestionList() {
        return suggestionList;
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
    }

    public void delSuggestion(Suggestion suggestion) {
        suggestionList.remove(suggestion);
    }

    // methods to get, add, and remove attendees and camp committe members

    public List<Student> getAttendeesList() {
        return attendeesList;
    }

    public void addAttendee(Student attendee) {
        attendeesList.add(attendee);
    }

    public void addAttendee(List<Student> attendeeList) {
        this.attendeesList = attendeeList;
    }

    public void removeAttendee(Student attendee) {
        attendeesList.remove(attendee);
    }

    // For use in CampController class, withdrawCamp method
    // We remove student from attendee list using student.getUserID()
    public void removeAttendee(String studentUserID) {
        for (Student attendee : attendeesList) {
            if (attendee.getUserID().equals(studentUserID)) {
                attendeesList.remove(attendee);
                break;
            }
        }
    }

    public List<CampCommitteeMember> getCampCommitteeList() {
        return campCommitteeList;
    }

    public void addCampCommitteeMember(Student student) {
        CampCommitteeMember campCommitteeMember = (CampCommitteeMember) student;
        campCommitteeList.add(campCommitteeMember);
    }

    public void setCampCommitteeMemberList(List<CampCommitteeMember> committeeMemberList) {
        this.campCommitteeList = committeeMemberList;
    }

    // Getter Methods

    public String getCampName() {
        return campInformation.getCampName();
    }

    public LocalDate getDates() {
        return campInformation.getDates();
    }

    public LocalDate getRegistrationClosingDate() {
        return campInformation.getRegistrationClosingDate();
    }

    public String getUserGroup() {
        return campInformation.getUserGroup();
    }

    public String getLocation() {
        return campInformation.getLocation();
    }

    public int getTotalSlots() {
        return campInformation.getTotalSlots();
    }

    public int getCampCommitteeSlots() {
        return campInformation.getCampCommitteeSlots();
    }

    public String getDescription() {
        return campInformation.getDescription();
    }

    public String getStaffInCharge() {
        return campInformation.getStaffInCharge();
    }

    public boolean getVisibility() {
        return campInformation.getVisibility();
    }

    // Setter Methods

    public void setCampName(String campName) {
        campInformation.setCampName(campName);
    }

    public void setDates(LocalDate dates) {
        campInformation.setDates(dates);
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        campInformation.setRegistrationClosingDate(registrationClosingDate);
    }

    public void setUserGroup(String userGroup) {
        campInformation.setUserGroup(userGroup);
    }

    public void setLocation(String location) {
        campInformation.setLocation(location);
    }

    public void setTotalSlots(int totalSlots) {
        campInformation.setTotalSlots(totalSlots);
    }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        campInformation.setCampCommitteeSlots(campCommitteeSlots);
    }

    public void setDescription(String description) {
        campInformation.setDescription(description);
    }

    public void setStaffInCharge(String staffInCharge) {
        campInformation.setStaffInCharge(staffInCharge);
    }

    public void setVisibility(boolean visibility) {
        campInformation.setVisibility(visibility);
    }

    // Other Methods

    // For use in Upload class
    public List<String> getAttendeeUserID() {
        // load student userID into a string list
        ArrayList<String> attendeeNames = new ArrayList<>();
        // System.out.println("reached!");
        for (Student attendee : attendeesList) {
            attendeeNames.add(attendee.getUserID());
        }

        return attendeeNames;
    }

    public List<String> getCommiteeUserID() {
        // load student userID into a string list
        ArrayList<String> commiteeNames = new ArrayList<>();
        // System.out.println("reached!");
        for (Student member : campCommitteeList) {
            commiteeNames.add(member.getUserID());
        }
        return commiteeNames;
    }

    public void removeCampCommitteeMember(String committeeMemberName) {
        for (CampCommitteeMember committeeMember : campCommitteeList) {
            if (committeeMember.getUserID().equals(committeeMemberName)) {
                campCommitteeList.remove(committeeMember);
                break;
            }
        }
    }

    public int getRemainingSlots() {
        int totalSlots = campInformation.getTotalSlots();
        int occupiedSlots = getAttendeesList().size() + getCampCommitteeList().size();
        return totalSlots - occupiedSlots;
    }
}
