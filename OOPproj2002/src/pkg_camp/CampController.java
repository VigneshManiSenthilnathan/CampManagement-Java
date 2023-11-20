package pkg_camp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CampController {

    public static void deleteCamp(Staff staff) {
        DeleteCamp.deleteCamp(staff);
    }

    public static void viewCamps(Student student) {
        ViewCamp.studentViewCamps(student);
    }

    public static void viewCamps(Staff staff) {
        ViewCamp.staffViewCamps(staff);
    }

    public static void viewCamps(CampCommitteeMember campCommitteeMember, Camp camp) {
        ViewCamp.campCommitteeViewCamp(campCommitteeMember, camp);
    }

    public static void modifyCamp(Staff staff) {
        ModifyCamp.modifyCamp(staff);
    }

    /*
     * public static void withdrawCampCommittee(CampCommitteeMember
     * campCommitteeMember, Camp camp) {
     * Withdrawal.withdrawCampCommittee(campCommitteeMember, camp);
     * }
     */ // apparently cannot do this???

    public static void registerForCamp(Student student, List<Camp> createdCampsList) {
        Registration.registerForCamp(student, createdCampsList);
    }

    public static void registerForCampCommitee(Student student) {
        Registration.registerForCampCommitee(student);
    }

    public static void withdrawCamp(Student student) {
        Withdrawal.withdrawCamp(student);
    }

    public static void createNewCamp(Staff staff) {
        CreateCamp.createNewCamp(staff);
    }

    // Constructor for CampController
    /*
     * public CampController(String campName, LocalDate dates, LocalDate
     * registrationClosingDate, String userGroup,
     * String location, int totalSlots, int campCommitteeSlots, String description,
     * String staffInCharge,
     * boolean visibility) {
     * super(campName, dates, registrationClosingDate, userGroup, location,
     * totalSlots, campCommitteeSlots,
     * description, staffInCharge, visibility);
     * }
     * 
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
     * // Override methods to get, add, and remove attendees and camp committee
     * members
     * 
     * @Override
     * public List<Student> getAttendees() {
     * return super.getAttendees();
     * }
     * 
     * @Override
     * public void addAttendee(Student attendee) {
     * super.addAttendee(attendee);
     * }
     * 
     * @Override
     * public void removeAttendee(Student attendee) {
     * super.removeAttendee(attendee);
     * }
     * 
     * // For use in CampController class, withdrawCamp method
     * // We remove student from attendee list using student.getUserID()
     * public void removeAttendee(String studentUserID) {
     * for (Student attendee : super.getAttendees()) {
     * if (attendee.getUserID().equals(studentUserID)) {
     * super.removeAttendee(attendee);
     * break;
     * }
     * }
     * }
     * 
     * @Override
     * public List<Student> getCampCommittee() {
     * return super.getCampCommittee();
     * }
     * 
     * @Override
     * public void addCampCommitteeMember(Student committeeMember) {
     * super.addCampCommitteeMember(committeeMember);
     * }
     * 
     * @Override
     * public void removeCampCommitteeMember(Student committeeMember) {
     * super.removeCampCommitteeMember(committeeMember);
     * }
     * 
     * // For use in Upload class
     * public List<String> getAttendeeUserID() {
     * // load student userID into a string list
     * ArrayList<String> attendeeNames = new ArrayList<>();
     * 
     * // System.out.println("reached!");
     * 
     * for (Student attendee : super.getAttendees()) {
     * attendeeNames.add(attendee.getUserID());
     * }
     * 
     * return attendeeNames;
     * }
     * 
     * public int getRemainingSlots() {
     * return super.getTotalSlots() - super.getAttendees().size() -
     * super.getCampCommittee().size();
     * }
     */

    /*
     * public static List<Camp> deleteCamp(Staff staff, List<Camp> createdCampsList)
     * {
     * 
     * if (createdCampsList.isEmpty()) {
     * System.out.println("There are no available camps to delete!");
     * return createdCampsList;
     * }
     * 
     * int i = 1;
     * for (Camp camp : createdCampsList) {
     * if (camp.getStaffInCharge().equals(staff.getUserID())) {
     * System.out.println(i + ". " + camp.getCampName());
     * i++;
     * }
     * }
     * 
     * if (i == 1) {
     * System.out.println("You haven't created any camps to delete.");
     * return createdCampsList;
     * }
     * 
     * else {
     * System.out.println("List of Camps available to delete:");
     * i = 1;
     * for (Camp camp : createdCampsList) {
     * if (camp.getStaffInCharge().equals(staff.getUserID())) {
     * System.out.println(i + ". " + camp.getCampName());
     * i++;
     * }
     * }
     * 
     * boolean donedelete = false;
     * Scanner scanner = new Scanner(System.in);
     * 
     * while (!donedelete) {
     * System.out.println("Camp to Delete (Enter EXIT to stop): ");
     * System.out.print("Camp Name: ");
     * 
     * String choice = scanner.nextLine();
     * 
     * if (choice.toUpperCase().equals("EXIT")) {
     * donedelete = true;
     * }
     * 
     * for (Camp camp : createdCampsList) {
     * if (choice.equalsIgnoreCase(camp.getCampName())) {
     * Upload.deleteCamp(choice);
     * createdCampsList.remove(camp);
     * System.out.println("Camp deleted successfully: " + camp.getCampName());
     * donedelete = true;
     * break;
     * }
     * }
     * 
     * /*
     * else if (choice >= 1 && choice <= i - 1) {
     * Camp campToDelete = createdCampsList.get(choice - 1);
     * createdCampsList.remove(campToDelete);
     * System.out.println("Camp deleted successfully: " +
     * campToDelete.getCampName());
     * 
     * }
     * 
     * 
     * }
     * // Close the scanner outside the loop
     * // scanner.close();
     * return createdCampsList;
     * }
     * }
     */
}
