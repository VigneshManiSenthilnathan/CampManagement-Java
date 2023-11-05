package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Registration extends Student {

    public void registerForCamp(Student student, List<CampInfoController> createdCamps) {
        // Showing available camps
        int i = 1;
        boolean done = false;

        while (!done) {
            // List all the camps available to join
            System.out.println("List of Camps available to join:");
            for (CampInfoController camp : createdCamps) {
                if (camp.getUserGroup().equals(this.getFaculty())
                        && (camp.getTotalSlots() - camp.getAttendees().size()) > 0) {
                    boolean userAlreadyAttended = false;

                    for (Student attendee : camp.getAttendees()) {
                        if (this.getUserID().equals(attendee.getUserID())) {
                            userAlreadyAttended = true;
                            break; // No need to check further, the user has attended this camp
                        }
                    }

                    if (!userAlreadyAttended) {
                        System.out.println(i + ". " + camp.getCampName() + " - " + camp.getDescription() + " | ");
                        System.out.println("(Slots left: " + (camp.getTotalSlots() - camp.getAttendees().size()) + ")");
                        i++;
                    }
                }
            }

            // Register for an available Camp
            System.out.println("Register for Camp: ");
            Scanner register = new Scanner(System.in);
            String campname = register.nextLine();

            for (CampInfoController camp : createdCamps) {
                if (campname == camp.getCampName()) {
                    for (Student applicant : camp.getCampCommittee()) {
                        if (applicant.getUserID() == camp.getCampCommitteeUserID(applicant.getUserID())) {
                            System.out.println(
                                    "You are already a part of the committee for this camp. You cannot register for this camp.");
                            done = true;
                        }
                    }
                    camp.addAttendee(student);
                }
            }
        }
        // register.close(); //closing the scanner doesnt work
    }

    public void dispRegisteredCamps(Student student, List<CampInfoController> createdCamps) {
        // list of camps student is registered for
        int j = 1;
        System.out.println("Here are the camps you have registered for:");
        for (CampInfoController camp : createdCamps) {
            if (camp.getUserGroup().equals(this.getFaculty())) {
                for (Student attendee : camp.getAttendees()) {
                    if (this.getUserID().equals(attendee.getUserID())) {
                        System.out.println(j + "- " + camp.getCampName());
                    }
                }
            } else {
                continue;
            }
        }
    }
}
