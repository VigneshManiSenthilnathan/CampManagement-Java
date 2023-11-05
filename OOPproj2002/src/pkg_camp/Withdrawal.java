package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Withdrawal extends Student {
    // since student can join multiple camp, need to let them choose which to
    // withdraw
    // I forgot to account for that in the CAM

    public void withdrawCamp(Student student, List<CampInfoController> createdCamps) {
        boolean registered = false;
        boolean done = false;
        int i = 1;
        System.out.println("List of Camps available to withdraw:");
        for (CampInfoController camp : createdCamps) {
            if (camp.getUserGroup().equals(this.getFaculty())) {s
                for (Student attendee : camp.getAttendees()) {
                    if (this.getUserID().equals(attendee.getUserID())) {
                        registered = true;
                        break; // No need to check further, the user has registered for this camp
                    }
                }

                if (registered) {
                    System.out.println(i + ". " + camp.getCampName());
                    i++;
                }
            }
        }
        // withdraw for an available Camp
        System.out.println("Withdraw for Camp: ");
        Scanner withdraw = new Scanner(System.in);
        String campname = withdraw.nextLine();
        

        for (CampInfoController camp : createdCamps) {
            if (campname == camp.getCampName()) {
                for (Student applicant : camp.getCampCommittee()) {
                    if (applicant.getUserID() == camp.getAttendeeUserID(applicant.getUserID())) {
                        System.out.println("Withdrawal successful!");
                        done = true;
                    }
                }
                camp.removeAttendee(student);
            }
        }

    }
}
