package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubmitEnquiry {

    public static void submitEnquiry(Student student) {

        System.out.println("You can submit an Enquiry for the following Camps: ");
        int i = 1;

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getAttendeeUserID().contains(student.getUserID())) {
                System.out
                        .println(i + ": " + camp.getCampName() + " [Camp Description: " + camp.getDescription() + "]");
                i++;
            }
        }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Submit an Enquiry to (Enter Camp Name): ");
        String campNameString = sc.next();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getCampName().equals(campNameString)) {
                System.out.println("Your Enquiry: ");
                String campEnquiryString = sc.next();

                Enquiry newEnquiry = new Enquiry(student.getUserID(), camp.getCampName(), campEnquiryString);
                camp.addEnquiry(newEnquiry);
                System.out.println("Enquiry Submited to " + camp.getCampName());
                System.out.println("");
            }
        }
    }

    public static void submitEnquiry(CampCommitteeMember campCommitteeMember) {

        System.out.println("You can submit an Enquiry for the following Camps: ");
        int i = 1;

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())) {
                System.out
                        .println(i + ": " + camp.getCampName() + " [Camp Description: " + camp.getDescription() + "]");
                i++;
            }
        }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Submit an Enquiry to (Enter Camp Name): ");
        String campNameString = sc.next();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getCampName().equals(campNameString)) {
                System.out.println("Your Enquiry: ");
                String campEnquiryString = sc.next();

                Enquiry newEnquiry = new Enquiry(campCommitteeMember.getUserID(), camp.getCampName(),
                        campEnquiryString);
                camp.addEnquiry(newEnquiry);
                System.out.println("Enquiry Submited to " + camp.getCampName());
                System.out.println("");
            }
        }
    }
}