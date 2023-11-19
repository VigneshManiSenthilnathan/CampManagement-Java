package pkg_camp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CampController extends Camp {

    // Constructor for CampController
    public CampController(String campName, LocalDate dates, LocalDate registrationClosingDate, String userGroup,
            String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge,
            boolean visibility) {
        super(campName, dates, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots,
                description, staffInCharge, visibility);
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

    // Override methods to get, add, and remove attendees and camp committee members
    @Override
    public List<Student> getAttendees() {
        return super.getAttendees();
    }

    @Override
    public void addAttendee(Student attendee) {
        super.addAttendee(attendee);
    }

    @Override
    public void removeAttendee(Student attendee) {
        super.removeAttendee(attendee);
    }

    // For use in CampController class, withdrawCamp method
    // We remove student from attendee list using student.getUserID()
    public void removeAttendee(String studentUserID) {
        for (Student attendee : super.getAttendees()) {
            if (attendee.getUserID().equals(studentUserID)) {
                super.removeAttendee(attendee);
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
        super.addCampCommitteeMember(committeeMember);
    }

    @Override
    public void removeCampCommitteeMember(Student committeeMember) {
        super.removeCampCommitteeMember(committeeMember);
    }

    // For use in Upload class
    public List<String> getAttendeeUserID() {
        // load student userID into a string list
        ArrayList<String> attendeeNames = new ArrayList<>();

        // System.out.println("reached!");

        for (Student attendee : super.getAttendees()) {
            attendeeNames.add(attendee.getUserID());
        }

        return attendeeNames;
    }

    public int getRemainingSlots() {
        return super.getTotalSlots() - super.getAttendees().size() - super.getCampCommittee().size();
    }

    public static void viewCamps(Student student, List<Camp> createdCampsList) {
        int i = 1;

        // check if createdCampsList is empty
        if (createdCampsList.isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        System.out.println("List of Camps available to join:");

        boolean found = false;
        for (Camp camp : createdCampsList) {
            if ((camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())) && (camp.getVisibility())
                    && (camp.getRemainingSlots()) > 0 && !camp.getAttendeeUserID().contains(student.getUserID())) {
                System.out.println(i + ": " + camp.getCampName() + " [Camp Description: " + camp.getDescription()
                        + "] (Slots left: " + (camp.getTotalSlots() - camp.getAttendees().size()) + ")");
                i++;
                found = true;
            }
        }

        System.out.println("");

        if (!found) {
            System.out.println("No camps available to view.");
            System.out.println("");
            return;
        }

        System.out.println("Type 'EXIT' to go back to Student Menu");
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine().toUpperCase();
        if (exit.equals("EXIT")) {
            return;
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static void viewCamps(Staff staff, List<Camp> createdCampsList) {
        System.out.println("List of ALL Camps:");
        for (Camp camp : createdCampsList) {
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Dates: " + camp.getDates());
            System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Staff in Charge: " + camp.getStaffInCharge());
            System.out.println("Visibility: " + camp.getVisibility());
            System.out.println("------------------------------");
        }

        System.out.println("Type 'EXIT' to go back to Staff Menu");
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine().toUpperCase();
        if (exit.equals("EXIT")) {
            return;
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static void viewCamps(CampCommitteeMember campCommitteeMember, List<Camp> createdCampsList) {
        System.out.println("Details of Camp:"); // retrieve the camp details that the student is a campcommitteemember
                                                // of
                                                // print all the details of that 1 camp only
        /*
         * for (Camp camp : createdCampsList) {
         * if (camp name == camp name of camp committee member) {
         * System.out.println("Camp Name: " + camp.getCampName());
         * System.out.println("Dates: " + camp.getDates());
         * System.out.println("Registration Closing Date: " +
         * camp.getRegistrationClosingDate());
         * System.out.println("Location: " + camp.getLocation());
         * System.out.println("Total Slots: " + camp.getTotalSlots());
         * System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
         * System.out.println("Description: " + camp.getDescription());
         * System.out.println("Staff in Charge: " + camp.getStaffInCharge());
         * System.out.println("Visibility: " + camp.getVisibility());
         * System.out.println("------------------------------");
         * }
         * }
         */
    }

    public static void viewCreatedCamps(Staff staff, List<Camp> createdCampsList) {
        System.out.println("List of Camps created by " + staff.getUserID() + ":");
        for (Camp camp : createdCampsList) {
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Dates: " + camp.getDates());
                System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Total Slots: " + camp.getTotalSlots());
                System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                System.out.println("Visibility: " + camp.getVisibility());
                System.out.println("------------------------------");
            }
        }

        System.out.println("Type 'EXIT' to go back to Staff Menu");
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine().toUpperCase();
        if (exit.equals("EXIT")) {
            return;
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static List<Camp> createNewCamp(Staff staff, List<Camp> createdCampsList) {
        LocalDate dates = null;
        LocalDate registrationClosingDate = null;

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Enter Camp Name: ");
        String campName = sc.next();

        System.out.println("Enter Camp Dates: ");
        String datesStr = sc.next();
        try {
            dates = LocalDate.parse(datesStr, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Use 'dd/MM/yy'.");
        }

        System.out.println("Enter Camp Registration Closing Date: ");
        String registrationClosingDateStr = sc.next();
        try {
            registrationClosingDate = LocalDate.parse(registrationClosingDateStr,
                    DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Use 'dd/MM/yy'.");
        }

        System.out.println("Enter Faculty: ");
        String userGroup = sc.next();

        System.out.println("Enter Location: ");
        String location = sc.next();

        System.out.println("Enter Total Slots Available: ");
        int totalSlots = sc.nextInt();

        boolean ccslots = false;
        int campCommitteeSlots = 0;
        while (!ccslots) {
            System.out.println("Enter Camp Committee Slots Available: ");
            campCommitteeSlots = sc.nextInt();
            if (campCommitteeSlots > 10) {
                System.out.println("Max Camp Committee Slots is 10!");
            } else {
                ccslots = true;
            }
        }

        System.out.println("Enter Camp Description: ");
        String description = sc.next();

        boolean exitVisibility = false;
        boolean visibility = false;

        while (!exitVisibility) {
            System.out.println("Enter Camp Visibility:");
            System.out.println("[1]: Visible");
            System.out.println("[2]: Hidden");
            int visibilityInt = sc.nextInt();

            if (visibilityInt == 1) {
                visibility = true;
                exitVisibility = true;
            }

            else if (visibilityInt == 2) {
                exitVisibility = true;
            }

            else {
                System.out.println("Please enter a valid integer only!");
            }
        }

        Camp newcamp = new Camp(campName, dates, registrationClosingDate, userGroup, location, totalSlots,
                campCommitteeSlots, description, staff.getUserID(), visibility);
        createdCampsList.add(newcamp);

        return createdCampsList;
    }

    public static List<Camp> modifyCamp(Staff staff, List<Camp> createdCampsList) {

        System.out.println("Enter Camp Name: ");
        Scanner sc = new Scanner(System.in);
        String campName = sc.next();

        // Implement Sanity Check for camp name
        Camp thisCamp = null;
        boolean campExists = false;
        while (!campExists) {
            for (Camp camp : createdCampsList) {
                if (camp.getCampName().equals(campName)) {
                    thisCamp = camp;
                    campExists = true;
                }
            }

            if (campExists == false) {
                System.out.println("Camp does not exist. Please enter a valid camp name.");
                System.out.println("Type 'Exit' to go back to Staff Menu");
                System.out.println("Enter Camp Name: ");
                campName = sc.next();
                if ("Exit".equals(campName)) {
                    return createdCampsList;
                }
            }
        }

        boolean done = false;
        while (!done) {
            System.out.println("(1) Change Camp Name");
            System.out.println("(2) Change Camp Dates");
            System.out.println("(3) Change Camp Registration Closing Date");
            System.out.println("(4) Change Viewing User Group");
            System.out.println("(5) Change Location");
            System.out.println("(6) Change Total Slots Available");
            System.out.println("(7) Change Camp Committee Slots Available");
            System.out.println("(8) Change Camp Description");
            System.out.println("(9) Change Camp Visibility");
            System.out.println("(10) Change StaffInCharge");
            System.out.println("(11) Exit to Staff Menu");

            sc.useDelimiter(System.lineSeparator());
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter New Camp Name: ");
                    String newCampName = sc.next();
                    Upload.updateCampNameInExcel(thisCamp.getCampName(), newCampName);
                    editCampDetails(thisCamp, 0, newCampName);
                    break;

                case 2:
                    System.out.println("Enter New Camp Dates: ");
                    String newCampDate = sc.next();
                    editCampDetails(thisCamp, 1, newCampDate);
                    break;

                case 3:
                    System.out.println("Enter New Camp Registration Closing Date: ");
                    String newCampRegDate = sc.next();
                    editCampDetails(thisCamp, 2, newCampRegDate);
                    break;

                case 4:
                    System.out.println("Enter New Camp UserGroup: ");
                    String newCampUserGrp = sc.next();
                    editCampDetails(thisCamp, 3, newCampUserGrp);
                    break;

                case 5:
                    System.out.println("Enter New Camp Location: ");
                    String newCampLocation = sc.next();
                    editCampDetails(thisCamp, 4, newCampLocation);
                    break;

                case 6:
                    System.out.println("Enter New Total Slots Available: ");
                    String newCampTotalSlots = sc.next();
                    editCampDetails(thisCamp, 5, newCampTotalSlots);
                    break;

                case 7:
                    System.out.println("Enter New Total Camp Committee Slots Available: ");
                    String newCampCommitteeSlots = sc.next();
                    editCampDetails(thisCamp, 6, newCampCommitteeSlots);
                    break;

                case 8:
                    System.out.println("Enter New Camp Description: ");
                    String newCampDescription = sc.next();
                    editCampDetails(thisCamp, 7, newCampDescription);
                    break;

                case 9:
                    System.out.println("Enter New Camp Visibility (Visible = 1, Invisible = 0): ");
                    int newCampVisibility = sc.nextInt();
                    CampInformation.toggleVisibility(thisCamp, newCampVisibility);
                    break;

                case 10:
                    System.out.println("Enter New StaffInCharge: ");
                    String newStaffIncharge = sc.next();
                    editCampDetails(thisCamp, 8, newStaffIncharge);
                    break;

                case 11:
                    System.out.println("Exiting back to staff menu...");
                    done = true;
                    return createdCampsList;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return createdCampsList;
    }

    public static void editCampDetails(Camp camp, int attributeToEdit, String edit) {
        switch (attributeToEdit) {
            case 0:
                camp.setCampName(edit);
                break;

            case 1:
                try {
                    LocalDate campDate = LocalDate.parse(edit, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    camp.setDates(campDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid date format. Use 'dd/MM/yy'.");
                }
                break;

            case 2:
                try {
                    LocalDate regClosingDate = LocalDate.parse(edit, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    camp.setRegistrationClosingDate(regClosingDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid date format. Use 'dd/MM/yy'.");
                }
                break;

            case 3:
                try {
                    camp.setUserGroup(edit);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 4:
                camp.setLocation(edit);
                break;

            case 5:
                try {
                    int slots = Integer.parseInt(edit);
                    camp.setTotalSlots(slots);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 6:
                try {
                    int comslots = Integer.parseInt(edit);
                    if (comslots > 10) {
                        System.out.println("Committee Slots exceeded! Max 10.");
                    } else {
                        camp.setCampCommitteeSlots(comslots);
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 7:
                camp.setDescription(edit);
                break;

            case 8:
                camp.setStaffInCharge(edit);
                break;

            case 9:
                System.out.println("Quitting Program...");
                break;

            default:
                System.out.println("Attribute does not exist.");
        }
    }

    public static List<Camp> withdrawCamp(Student student, List<Camp> createdCampsList) {
        Scanner withdrawScanner = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            int i = 1;
            System.out.println("List of Camps available to withdraw:");

            for (Camp camp : createdCampsList) {
                if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                        && camp.getAttendeeUserID().contains(student.getUserID())) {
                    System.out.println(i + ". " + camp.getCampName());
                    System.out.println("");
                    i++;
                }
            }

            // Withdraw from an available Camp
            System.out.println("Withdraw from Camp (Type 'EXIT' to stop): ");
            System.out.print("Camp Name: ");
            String campname = withdrawScanner.nextLine();

            if (campname.toUpperCase().equals("EXIT")) {
                done = true;
            } else {
                boolean withdrawalSuccessful = false;

                for (Camp camp : createdCampsList) {
                    if (campname.equals(camp.getCampName())
                            && camp.getAttendeeUserID().contains(student.getUserID())) {

                        camp.removeAttendee(student.getUserID());
                        System.out.println("Withdrawal successful from " + camp.getCampName());
                        withdrawalSuccessful = true;
                        return createdCampsList;
                    }
                }

                if (!withdrawalSuccessful) {
                    System.out.println("Invalid camp name or you are not registered for this camp.");
                }
            }
        }
        return createdCampsList;
        // Dont close the scanner
        // withdrawScanner.close();
    }

    public static List<Camp> deleteCamp(Staff staff, List<Camp> createdCampsList) {

        if (createdCampsList.isEmpty()) {
            System.out.println("There are no available camps to delete!");
            return createdCampsList;
        }

        int i = 1;
        for (Camp camp : createdCampsList) {
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                System.out.println(i + ". " + camp.getCampName());
                i++;
            }
        }

        if (i == 1) {
            System.out.println("You haven't created any camps to delete.");
            return createdCampsList;
        }

        else {
            System.out.println("List of Camps available to delete:");
            i = 1;
            for (Camp camp : createdCampsList) {
                if (camp.getStaffInCharge().equals(staff.getUserID())) {
                    System.out.println(i + ". " + camp.getCampName());
                    i++;
                }
            }

            boolean donedelete = false;
            Scanner scanner = new Scanner(System.in);

            while (!donedelete) {
                System.out.println("Camp to Delete (Enter EXIT to stop): ");
                System.out.print("Camp Name: ");

                String choice = scanner.nextLine();

                if (choice.toUpperCase().equals("EXIT")) {
                    donedelete = true;
                }

                for (Camp camp : createdCampsList) {
                    if (choice.equalsIgnoreCase(camp.getCampName())) {
                        Upload.deleteCamp(choice);
                        createdCampsList.remove(camp);
                        System.out.println("Camp deleted successfully: " + camp.getCampName());
                        donedelete = true;
                        break;
                    }
                }

                /*
                 * else if (choice >= 1 && choice <= i - 1) {
                 * Camp campToDelete = createdCampsList.get(choice - 1);
                 * createdCampsList.remove(campToDelete);
                 * System.out.println("Camp deleted successfully: " +
                 * campToDelete.getCampName());
                 * 
                 * }
                 */

            }
            // Close the scanner outside the loop
            // scanner.close();
            return createdCampsList;
        }
    }
}
