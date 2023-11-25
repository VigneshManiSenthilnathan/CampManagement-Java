package pkg_camp;

import java.util.Scanner;

public class ViewCamp {

    public static void studentViewCamps(Student student) {
        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        System.out.println("[1] View all camps allowed to join");
        System.out.println("[2] View your registered camps");
        System.out.println("[0] Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        boolean doneView = false;
        while (!doneView) {
            switch (choice) {
                case 0:
                    doneView = true;
                    break;

                case 1:
                    System.out.println("List of Camps available to join:");
                    boolean found = false;
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if ((camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase()))
                                && (camp.getVisibility())
                                && (camp.getRemainingSlots()) > 0
                                && !camp.getAttendeeUserID().contains(student.getUserID())
                                && !camp.getBlackList().contains(student.getUserID())) {
                            System.out.println("Camp Name: " + camp.getCampName());
                            System.out.println("Dates: " + camp.getDates());
                            System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                            System.out.println("Location: " + camp.getLocation());
                            System.out.println("Total Slots: " + camp.getTotalSlots());
                            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                            System.out.println("Description: " + camp.getDescription());
                            System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                            System.out.println("------------------------------");
                            found = true;
                        }
                    }
                    System.out.println("");

                    if (!found) {
                        System.out.println("No camps available to view!");
                        System.out.println("");
                        return;
                    }
                    break;

                case 2:
                    int j = 1;
                    found = false;
                    System.out.println("Here are the camps you have registered for:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getUserGroup().equals(student.getFaculty())) {
                            for (Student attendee : camp.getAttendeesList()) {
                                if (student.getUserID().equals(attendee.getUserID())) {
                                    System.out.println(j + "- " + camp.getCampName());
                                    found = true;
                                }
                            }
                        }
                    }

                    if (!found) {
                        System.out.println("You have not registered for any Camps!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }

    public static void staffViewCamps(Staff staff) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        // Your staff-specific code here
        boolean viewcamps = false;
        while (!viewcamps) {
            System.out.println("(1) View All Camps");
            System.out.println("(2) View Created Camps");
            System.out.println("(0) Exit to Previous Menu");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            boolean found = false;
            switch (choice) {
                case 0:
                    viewcamps = true;
                    break;

                case 1:
                    System.out.println("List of ALL Camps:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        System.out.println("Camp Name: " + camp.getCampName());
                        System.out.println("Dates: " + camp.getDates());
                        System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                        System.out.println("Location: " + camp.getLocation());
                        System.out.println("Total Slots: " + camp.getTotalSlots());
                        System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                        System.out.println("Description: " + camp.getDescription());
                        System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                        System.out.println("Visibility (Not visible = 0/Visible = 1): " + camp.getVisibility());
                        System.out.println("------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("List of Camps created by " + staff.getUserID() + ":");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
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
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("You have not created any camps!");
                    }

                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return;
    }

    public static void campCommitteeViewCamp(CampCommitteeMember campCommitteeMember) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        boolean doneView = false;
        while (!doneView) {
            System.out.println("[1] View all camps to join");
            System.out.println("[2] View your registered camps");
            System.out.println("[3] Details of your Committee Camp");
            System.out.println("[0] Exit");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    doneView = true;
                    break;

                case 1:
                    System.out.println("List of Camps available to join:");
                    boolean found = false;
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if ((camp.getUserGroup().toUpperCase().equals(campCommitteeMember.getFaculty().toUpperCase()))
                                && (camp.getVisibility())
                                && (camp.getRemainingSlots()) > 0
                                && !camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())
                                && !camp.getCommiteeUserID().contains(campCommitteeMember.getUserID())) {
                            System.out.println("Camp Name: " + camp.getCampName());
                            System.out.println("Dates: " + camp.getDates());
                            System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                            System.out.println("Location: " + camp.getLocation());
                            System.out.println("Total Slots: " + camp.getTotalSlots());
                            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                            System.out.println("Description: " + camp.getDescription());
                            System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                            System.out.println("------------------------------");
                            found = true;
                        }
                    }
                    System.out.println("");

                    if (!found) {
                        System.out.println("No camps available to view.");
                        System.out.println("");
                        return;
                    }
                    break;

                case 2:
                    found = false;
                    int j = 1;
                    System.out.println("Here are the camps you have registered for:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())) {
                            System.out.println(j + ": " + camp.getCampName());
                            j++;
                            found = true;
                        } else if (camp.getCommiteeUserID().contains(campCommitteeMember.getUserID())) {
                            System.out.println(j + ": " + camp.getCampName() + " (Camp Committee)");
                            j++;
                            continue;
                        }
                    }

                    if (!found) {
                        System.out.println("No camps available to view.");
                        System.out.println("");
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Details of Your Camp:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getCommiteeUserID().contains(campCommitteeMember.getUserID())) {
                            System.out.println("Camp Name: " + camp.getCampName());
                            System.out.println("Dates: " + camp.getDates());
                            System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                            System.out.println("Location: " + camp.getLocation());
                            System.out.println("Total Slots: " + camp.getTotalSlots());
                            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                            System.out.println("Description: " + camp.getDescription());
                            System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                            System.out
                                    .println("Visibility(Not visible = false/Visible = true): " + camp.getVisibility());
                            System.out.println("------------------------------");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}
