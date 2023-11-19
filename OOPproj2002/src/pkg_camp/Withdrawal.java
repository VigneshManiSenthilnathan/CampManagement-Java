package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Withdrawal {

    public static void withdrawCamp(Student student) {
        Scanner sc = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            int i = 1;
            System.out.println("List of Camps available to withdraw:");

            for (Camp camp : CampsList.getCreatedCampsList()) {
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
            String campname = sc.nextLine();

            if (campname.toUpperCase().equals("EXIT")) {
                done = true;
            } else {
                boolean withdrawalSuccessful = false;

                for (Camp camp : CampsList.getCreatedCampsList()) {
                    if (campname.equals(camp.getCampName())
                            && camp.getAttendeeUserID().contains(student.getUserID())) {

                        camp.removeAttendee(student.getUserID());
                        System.out.println("Withdrawal successful from " + camp.getCampName());
                        withdrawalSuccessful = true;
                    }
                }

                if (!withdrawalSuccessful) {
                    System.out.println("Invalid camp name or you are not registered for this camp.");
                }
            }
        }
    }
}