package pkg_camp;

import java.util.Scanner;

public class Registration {

    public static void registerForCamp(Student student) {
        // Showing available camps
        System.out.println("Registering as:");
        System.out.println("[1] Attendee");
        System.out.println("[2] Camp Committee Member");
        System.out.println("[0] Exit");

        boolean found = false;

        Scanner sc = new Scanner(System.in);
        int role_choice = sc.nextInt();

        switch (role_choice) {
            case 0:
                break;

            case 1:
                boolean done = false;
                while (!done) {
                    int i = 1;
                    // List all the camps available to join
                    System.out.println("List of Camps available to join:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                                && camp.getVisibility()
                                && (camp.getTotalSlots() - camp.getAttendeesList().size()) > 0
                                && !camp.getAttendeeUserID().contains(student.getUserID())
                                && !camp.getBlackList().contains(student.getUserID())) {
                            found = true;
                            System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription() + " | ");
                            System.out.println(
                                    "(Slots left: " + (camp.getTotalSlots() - camp.getAttendeesList().size()) + ")");
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
                    System.out.println("Register for Camp: ");
                    Scanner register = new Scanner(System.in);
                    String campname = register.nextLine();
                    if (campname.equalsIgnoreCase("EXIT")) {
                        return;
                    }

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (campname.equals(camp.getCampName())
                                && camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                                && camp.getVisibility()
                                && (camp.getTotalSlots() - camp.getAttendeesList().size()) > 0
                                && !camp.getAttendeeUserID().contains(student.getUserID())
                                && !camp.getBlackList().contains(student.getUserID())) {
                            camp.addAttendee(student);
                            System.out.println("Registration Successful! Welcome to " + camp.getCampName());
                            done = true;
                        }
                    }
                }
                break;

            case 2:
                // Showing available camps
                done = false;
                while (!done) {
                    int i = 1;
                    found = false;
                    // List all the camps available for commitee
                    System.out.println("List of Camps with Committee Roles Available:");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                                && (10 - camp.getCampCommitteeList().size()) > 0
                                && !camp.getCommiteeUserID().contains(student.getUserID())
                                && !camp.getBlackList().contains(student.getUserID())) {
                            found = true;
                            System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription() + " | ");
                            System.out.println(
                                    "(Committee Slots left: " + (10 - camp.getCampCommitteeList().size()) + ")");
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
                    if (campname.equalsIgnoreCase("EXIT")) {
                        return;
                    }

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (campname.equals(camp.getCampName())) {
                            if (camp.getAttendeesList().contains(student)
                                    && !camp.getBlackList().contains(student.getUserID())) {
                                camp.removeAttendee(student.getUserID());
                                camp.addCampCommitteeMember(student);
                                System.out.println("Registration Successful! You are now a Camp Committee Member!");
                                System.out.println("");
                                done = true;
                                break;
                            }
                        }
                    }
                }
                break;

            default:
                System.out.println("Please press either 1 or 2");
                break;
        }
    }

    public static void registerForCamp(CampCommitteeMember campCommitteeMember) {
        // Showing available camps
        boolean done = false;

        while (!done) {
            boolean found = false;
            // List all the camps available to join
            System.out.println("List of Camps available to join:");
            for (Camp camp : CampsList.getCreatedCampsList()) {
                int i = 1;
                if (camp.getUserGroup().toUpperCase().equals(campCommitteeMember.getFaculty().toUpperCase())
                        && camp.getVisibility()
                        && (camp.getTotalSlots() - camp.getAttendeesList().size()) > 0
                        && !camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())
                        && !camp.getCommiteeUserID().contains(campCommitteeMember.getUserID())
                        && !camp.getBlackList().contains(campCommitteeMember.getUserID())) {
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
                return;
            }

            // Register for an available Camp
            System.out.println("Which camp do you want to register for? (Type EXIT to quit menu): ");
            Scanner register = new Scanner(System.in);
            String campname = register.nextLine();
            if (campname.equalsIgnoreCase("EXIT")) {
                return;
            }

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (campname.equals(camp.getCampName())) {
                    // Uncomment after implementing camp committee
                    for (CampCommitteeMember applicant : camp.getCampCommitteeList()) {
                        if (applicant.getUserID().equalsIgnoreCase(campCommitteeMember.getUserID())) {
                            System.out.println(
                                    "You are part of the committee for this camp. You cannot register for this camp.");
                            done = true;
                            break;
                        }
                    }

                    Student student = campCommitteeMember;
                    camp.addAttendee(student);
                    System.out.println("Registration Successful! Welcome to " + camp.getCampName());
                    done = true;
                    break;
                }
            }
        }
    }
}
