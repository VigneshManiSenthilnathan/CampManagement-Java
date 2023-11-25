package controller;

import java.util.Scanner;

public class ManageCreatedCamps {

    public static void manageCreatedCamps(Staff staff) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        boolean foundcamp = false;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                foundcamp = true;
            }
        }

        if (!foundcamp) {
            System.out.println("You have not created any Camps!");
            return;
        }

        boolean manageDone = false;
        while (!manageDone) {
            System.out.println("Manage Created Camps: ");
            System.out.println("[1] View Camp Members");
            System.out.println("[2] Remove Camp Members");
            System.out.println("[0] Exit");
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter(System.lineSeparator());
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    manageDone = true;
                    break;

                case 1:
                    int i = 1;

                    System.out.println("Your Created Camps: ");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getStaffInCharge().equals(staff.getUserID())) {
                            System.out.println(i + ". Camp Name: " + camp.getCampName());
                        }
                    }

                    System.out.println("View Members for Camp (Type EXIT to quit):  ");
                    String campName = scanner.next();

                    if (campName.equalsIgnoreCase("Exit")) {
                        break;
                    }

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getCampName().equalsIgnoreCase(campName)) {
                            if (camp.getAttendeesList().isEmpty() && camp.getCampCommitteeList().isEmpty()) {
                                System.out.println("The camp has no members!");
                                break;
                            }
                        }
                    }

                    for (Camp campPrint : CampsList.getCreatedCampsList()) {
                        if (campPrint.getCampName().equalsIgnoreCase(campName)) {
                            if (campPrint.getAttendeesList().isEmpty()) {
                                System.out.println("No Attendees!");
                            } else {
                                System.out.println("Attendees: ");
                                for (Student student : campPrint.getAttendeesList()) {
                                    System.out.println(student.getUserID());
                                }
                            }

                            if (campPrint.getCampCommitteeList().isEmpty()) {
                                System.out.println("No Camp Committee Members!");
                            }

                            else {
                                System.out.println("Camp Committee Members: ");
                                for (CampCommitteeMember campCommitteeMember : campPrint.getCampCommitteeList()) {
                                    System.out.println(campCommitteeMember.getUserID());
                                }
                            }
                        }
                    }
                    break;

                case 2:
                    i = 1;
                    System.out.println("Your Created Camps: ");
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getStaffInCharge().equals(staff.getUserID())) {
                            System.out.println(i + ". Camp Name: " + camp.getCampName());
                        }
                    }

                    System.out.println("Remove Members for Camp (Type EXIT to quit):  ");
                    campName = scanner.next();

                    if (campName.equalsIgnoreCase("Exit")) {
                        break;
                    }

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getCampName().equalsIgnoreCase(campName)) {
                            if (camp.getAttendeesList().isEmpty() && camp.getCampCommitteeList().isEmpty()) {
                                System.out.println("The camp has no members!");
                                break;
                            }
                        }
                    }

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getCampName().equalsIgnoreCase(campName)) {
                            if (camp.getAttendeesList().isEmpty()) {
                                System.out.println("No Attendees!");
                            } else {
                                System.out.println("Attendees: ");
                                for (Student student : camp.getAttendeesList()) {
                                    System.out.println(student.getUserID());
                                }
                            }

                            if (camp.getCampCommitteeList().isEmpty()) {
                                System.out.println("No Camp Committee Members!");
                            }

                            else {
                                System.out.println("Camp Committee Members: ");
                                for (CampCommitteeMember campCommitteeMember : camp.getCampCommitteeList()) {
                                    System.out.println(campCommitteeMember.getUserID());
                                }
                            }
                        }
                    }

                    System.out.println("Enter UserID to remove (Type EXIT to quit): ");
                    String studentID = scanner.nextLine();

                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        if (camp.getCampName().equalsIgnoreCase(campName)) {
                            if (camp.getAttendeeUserID().contains(studentID)) {
                                camp.removeAttendee(studentID);
                                System.out.println("Attendee removed!");
                            } else if (camp.getCommiteeUserID().contains(studentID)) {
                                camp.removeCampCommitteeMember(studentID);
                                System.out.println("Camp Committee Member removed!");
                            }
                        }
                    }
                    if (campName.equalsIgnoreCase("Exit")) {
                        break;
                    }

            }
        }
    }
}
