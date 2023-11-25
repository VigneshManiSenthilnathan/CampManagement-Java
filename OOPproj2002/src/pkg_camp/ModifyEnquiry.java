package pkg_camp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ModifyEnquiry {

    public static void modifyEnquiry(Student student) {
        Scanner sc = new Scanner(System.in);
        boolean modify = false;

        while (!modify) {
            System.out.println("These are your Enquiries: ");
            int i = 1;
            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (enquiry.getStudentID().equals(student.getUserID())) {
                        System.out.println(
                                i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                        i++;
                    }
                }
            }

            System.out.println("Modify Enquiry (Enter Camp Name or 'exit' to cancel): ");
            String campNameToModify = sc.nextLine();

            if (campNameToModify.equalsIgnoreCase("exit")) {
                System.out.println("Modification canceled.");
                break;
            }

            boolean validInput = false;

            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (camp.getCampName().equals(campNameToModify)
                            && enquiry.getStudentID().equals(student.getUserID())) {
                        validInput = true;

                        System.out.println("Enter New Enquiry: ");
                        String newEnquiry = sc.nextLine();
                        enquiry.editEnquiryString(newEnquiry);
                        System.out.println("Enquiry modified successfully.");
                        break;
                    }
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter a valid Camp Name or 'exit' to cancel.");
            }
        }
    }

    public static void modifyEnquiry(CampCommitteeMember campCommitteeMember) {
        Scanner sc = new Scanner(System.in);
        boolean modify = false;

        while (!modify) {
            System.out.println("These are your Enquiries: ");
            int i = 1;
            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (enquiry.getStudentID().equals(campCommitteeMember.getUserID())) {
                        System.out.println(
                                i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                        i++;
                    }
                }
            }

            System.out.println("Modify Enquiry (Enter Camp Name or 'exit' to cancel): ");
            String campNameToModify = sc.nextLine();

            if (campNameToModify.equalsIgnoreCase("exit")) {
                System.out.println("Modification canceled.");
                break;
            }

            boolean validInput = false;

            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (camp.getCampName().equals(campNameToModify)
                            && enquiry.getStudentID().equals(campCommitteeMember.getUserID())) {
                        validInput = true;

                        System.out.println("Enter New Enquiry: ");
                        String newEnquiry = sc.nextLine();
                        enquiry.editEnquiryString(newEnquiry);
                        System.out.println("Enquiry modified successfully.");
                        break;
                    }
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter a valid Camp Name or 'exit' to cancel.");
            }
        }
    }
}