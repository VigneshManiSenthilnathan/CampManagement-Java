package pkg_camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Camp {

    private List<Student> attendees;
    private List<Student> campCommittee;
    private List<Enquiry> enquiryList;
    private List<Suggestion> suggestionList;
    private CampInformation campInformation;

    public Camp(CampInformation campInformation) {
        this.campInformation = campInformation;
        this.attendees = new ArrayList<>();
        this.campCommittee = new ArrayList<>();
        this.enquiryList = new ArrayList<>();
        this.suggestionList = new ArrayList<>();
    }

    /*
     * public Camp(CampInformation campInformation, List<Student> attendees) {
     * this.campInformation = campInformation;
     * this.attendees = attendees;
     * this.campCommittee = new ArrayList<>();
     * this.enquiryList = new ArrayList<>();
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Student> campCommittee) {
     * this.campInformation = campInformation;
     * this.attendees = new ArrayList<>();
     * this.campCommittee = campCommittee;
     * this.enquiryList = new ArrayList<>();
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Enquiry> enquiryList) {
     * this.campInformation = campInformation;
     * this.attendees = new ArrayList<>();
     * this.campCommittee = new ArrayList<>();
     * this.enquiryList = enquiryList;
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Suggestion>
     * suggestionsList) {
     * this.campInformation = campInformation;
     * this.attendees = new ArrayList<>();
     * this.campCommittee = new ArrayList<>();
     * this.enquiryList = new ArrayList<>();
     * this.suggestionList = suggestionsList;
     * }
     * 
     * public Camp(CampInformation campInformation, List<Student> attendees) {
     * this.campInformation = campInformation;
     * this.attendees = attendees;
     * this.campCommittee = new ArrayList<>();
     * this.enquiryList = new ArrayList<>();
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Student> attendees,
     * List<Student> campCommittee) {
     * this.campInformation = campInformation;
     * this.attendees = attendees;
     * this.campCommittee = campCommittee;
     * this.enquiryList = new ArrayList<>();
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Student> attendees,
     * List<Student> campCommittee,
     * List<Enquiry> enquiryList) {
     * this.campInformation = campInformation;
     * this.attendees = attendees;
     * this.campCommittee = campCommittee;
     * this.enquiryList = enquiryList;
     * this.suggestionList = new ArrayList<>();
     * }
     * 
     * public Camp(CampInformation campInformation, List<Student> attendees,
     * List<Student> campCommittee,
     * List<Enquiry> enquiryList, List<Suggestion> suggestionsList) {
     * this.campInformation = campInformation;
     * this.attendees = attendees;
     * this.campCommittee = campCommittee;
     * this.enquiryList = enquiryList;
     * this.suggestionList = suggestionsList;
     * }/*
     * 
     * /*
     * public String getCampName() {
     * return super.getCampName();
     * }
     * 
     * public void setCampName(String campName) {
     * super.setCampName(campName);
     * }
     * 
     * public LocalDate getDates() {
     * return super.getDates();
     * }
     * 
     * public void setDates(LocalDate dates) {
     * super.setDates(dates);
     * }
     * 
     * public LocalDate getRegistrationClosingDate() {
     * return super.getRegistrationClosingDate();
     * }
     * 
     * public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
     * super.setRegistrationClosingDate(registrationClosingDate);
     * }
     * 
     * public String getUserGroup() {
     * return super.getUserGroup();
     * }
     * 
     * public void setUserGroup(String userGroup) {
     * super.setUserGroup(userGroup);
     * }
     * 
     * public String getLocation() {
     * return super.getLocation();
     * }
     * 
     * public void setLocation(String location) {
     * super.setLocation(location);
     * }
     * 
     * public int getTotalSlots() {
     * return super.getTotalSlots();
     * }
     * 
     * public void setTotalSlots(int totalSlots) {
     * super.setTotalSlots(totalSlots);
     * }
     * 
     * public int getCampCommitteeSlots() {
     * return super.getCampCommitteeSlots();
     * }
     * 
     * public void setCampCommitteeSlots(int campCommitteeSlots) {
     * super.setCampCommitteeSlots(campCommitteeSlots);
     * }
     * 
     * public String getDescription() {
     * return super.getDescription();
     * }
     * 
     * public void setDescription(String description) {
     * super.setDescription(description);
     * }
     * 
     * public String getStaffInCharge() {
     * return super.getStaffInCharge();
     * }
     * 
     * public void setStaffInCharge(String staffInCharge) {
     * super.setStaffInCharge(staffInCharge);
     * }
     * 
     * public List<Enquiry> getEnquiries() {
     * return super.getEnquiry();
     * }
     * 
     * public List<Suggestion> getSuggestions() {
     * return super.getSuggestion();
     * }
     * 
     * public void addSuggestions(Suggestion suggestions) {
     * super.addSuggestion(suggestions);
     * }
     * 
     * public void removeSuggestion(Suggestion suggestion) {
     * super.removeSuggestion(suggestion);
     * }
     */

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

    public List<Student> getAttendees() {
        return attendees;
    }

    public void addAttendee(Student attendee) {
        attendees.add(attendee);
    }

    public void addAttendee(List<Student> attendeeList) {
        this.attendees = attendeeList;
    }

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

    public List<Student> getCampCommittee() {
        return campCommittee;
    }

    public void addCampCommitteeMember(Student committeeMember) {
        // System.out.println(committeeMember.getUserID());
        campCommittee.add(committeeMember);
    }

    public void addCampCommitteeMember(List<Student> committeeMemberList) {
        this.campCommittee = committeeMemberList;
    }

    public void removeCampCommitteeMember(Student committeeMember) {
        campCommittee.remove(committeeMember);
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

    public int getRemainingSlots() {
        int totalSlots = campInformation.getTotalSlots();
        int occupiedSlots = getAttendees().size() + getCampCommittee().size();
        return totalSlots - occupiedSlots;
    }
}
