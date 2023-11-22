package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Registration {

    public static List<Camp> registerForCamp(Student student, List<Camp> createdCamps) {
        // Showing available camps
        boolean done = false;

        while (!done) {
            boolean found = false;

            // List all the camps available to join
            System.out.println("List of Camps available to join:");
            for (Camp camp : createdCamps) {
                int i = 1;
                if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase()) && camp.getVisibility()
                        && (camp.getTotalSlots() - camp.getAttendeesList().size()) > 0
                        && !camp.getAttendeeUserID().contains(student.getUserID())) {
                    found = true;
                    System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription() + " | ");
                    System.out.println("(Slots left: " + (camp.getTotalSlots() - camp.getAttendeesList().size()) + ")");
                    i++;
                }
            }

            System.out.println("");

            if (!found) {
                System.out.println("No camps available to join.");
                System.out.println("");
                return createdCamps;
            }

            // Register for an available Camp
            System.out.println("Register for Camp: ");
            Scanner register = new Scanner(System.in);
            String campname = register.nextLine();

            boolean campNameExists = false;

            for (Camp camp : createdCamps) {
                if (campname.equals(camp.getCampName())) {
                    campNameExists = true;

                    // Uncomment after implementing camp committee
                    for (Student applicant : camp.getCampCommitteeList()) {
                        if (applicant.getUserID().equals(student.getUserID())) {
                            System.out.println(
                                    "You are already a part of the committee for this camp. You cannot register for this camp.");
                            done = true;
                            return createdCamps;
                        }
                    }

                    camp.addAttendee(student);
                    System.out.println("Registration Successful! Welcome to " + camp.getCampName());
                    done = true;
                    break;
                }
            }

            if (!campNameExists) {
                System.out.println("Invalid camp name. Please enter a valid camp name.");
            }
        }
        return createdCamps;
    }

    // register.close(); //closing the scanner doesn't work

    public static void registerForCampCommitee(Student student) {
        // Showing available camps
        int i = 1;
        boolean done = false;

        while (!done) {
            boolean found = false;

            // List all the camps available for commitee
            System.out.println("List of Camps with Committee Roles Available:");
            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                        && (10 - camp.getCampCommitteeList().size()) > 0
                        && !camp.getCommiteeUserID().contains(student.getUserID())) {
                    found = true;
                    System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription() + " | ");
                    System.out.println("(Committee Slots left: " + (10 - camp.getCampCommitteeList().size()) + ")");
                    i++;
                }

            }

            System.out.println("");

            if (!found) {
                System.out.println("No camps available to join.");
                System.out.println("");
                return;
            }

            // Register for an available Camp
            System.out.println("Register as Committee Member: ");
            Scanner register = new Scanner(System.in);
            String campname = register.nextLine();

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (campname.equals(camp.getCampName())) {

                    for (Student applicant : camp.getAttendeesList()) {
                        if (applicant.getUserID().equals(student.getUserID())) {
                            System.out.println("You are already a attendee for this camp!");
                            System.out.println("You cannot register for this camp committee!");
                            System.out.println("");

                            done = true;
                        }
                    }

                    camp.addCampCommitteeMember(student);
                    System.out.println("Registration Successful! Welcome to " + camp.getCampName());
                    done = true;
                    break;
                }
            }
        }
        return;
    }

    public static void dispRegisteredCamps(Student student, List<Camp> createdCamps) {
        // list of camps student is registered for
        int j = 1;
        System.out.println("Here are the camps you have registered for:");
        for (Camp camp : createdCamps) {
            if (camp.getUserGroup().equals(student.getFaculty())) {
                for (Student attendee : camp.getAttendeesList()) {
                    if (student.getUserID().equals(attendee.getUserID())) {
                        System.out.println(j + "- " + camp.getCampName());
                    }
                }
            } else {
                continue;
            }
        }
    }
}
