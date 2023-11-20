package pkg_camp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubmitEnquiry {

    public static void submitEnquiry(Student student) {

        System.out.println("You can submit an Enquiry for the following Camps: ");
        int i = 1;

        List<Camp> validCamps = new ArrayList<Camp>();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getAttendeeUserID().contains(student.getUserID())) {
                System.out
                        .println(i + ": " + camp.getCampName() + " [Camp Description: " + camp.getDescription() + "]");
                validCamps.add(camp);
            }
        }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Submit an Enquiry to (Enter Camp Name): ");
        String campNameString = sc.next();

        for (Camp camp : validCamps) {
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
}