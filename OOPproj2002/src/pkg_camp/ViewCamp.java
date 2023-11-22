package pkg_camp;

import java.util.Scanner;

public class ViewCamp {

    public static void studentViewCamps(Student student) {
        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        System.out.println("List of Camps available to join:");

        boolean found = false;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            if ((camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())) && (camp.getVisibility())
                    && (camp.getRemainingSlots()) > 0 && !camp.getAttendeeUserID().contains(student.getUserID())) {
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

    // Loop for input validation
        while (true) {
            System.out.println("Type 'EXIT' to go back to Student Menu");
            Scanner sc = new Scanner(System.in);
            String exit = sc.nextLine();
            if (exit.equalsIgnoreCase("EXIT")) {
                return;
            } else {
                System.out.println("Invalid Input. Please type 'EXIT' to go back to Student Menu.");
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
            System.out.println("(3) Exit to Staff Menu");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
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
                        System.out.println("Visibility: " + camp.getVisibility());
                        System.out.println("------------------------------");
                    }

                    System.out.println("Type 'EXIT' to go back to Staff Menu");
                    Scanner sc1 = new Scanner(System.in);
                    String exit = sc1.nextLine();
                    if (exit.equalsIgnoreCase("EXIT")) {
                        break;
                    } else {
                        System.out.println("Invalid Input");
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
                        }
                    }

                    System.out.println("Type 'EXIT' to go back to Staff Menu");
                    Scanner sc2 = new Scanner(System.in);
                    exit = sc2.nextLine();
                    if (exit.equalsIgnoreCase("EXIT")) {
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 3:
                    viewcamps = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return;
    }

    public static void campCommitteeViewCamp(CampCommitteeMember campCommitteeMember, Camp camp) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        boolean found = false;

        System.out.println("Details of Your Camps:");
        for (Camp availcamp : CampsList.getCreatedCampsList()) {
            if (availcamp.getCampName().equals(camp.getCampName())) { // solid vignesh only did this 1 line ha ha
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Dates: " + camp.getDates());
                System.out.println("Registration Closing Date: " + availcamp.getRegistrationClosingDate());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Total Slots: " + camp.getTotalSlots());
                System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                System.out.println("Visibility: " + camp.getVisibility());
                System.out.println("------------------------------");
            }
        }
    }
}
