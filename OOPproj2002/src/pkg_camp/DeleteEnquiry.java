package pkg_camp;

import java.util.Scanner;

public class DeleteEnquiry {

    public static void deleteEnquiry(Student student) {
        Scanner sc = new Scanner(System.in);

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

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Delete Enquiry (Enter Camp Name or 'exit' to cancel): ");
            String enquiryDel = sc.nextLine();

            if (enquiryDel.equalsIgnoreCase("exit")) {
                System.out.println("Deletion canceled.");
                break;
            }

            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (camp.getCampName().equals(enquiryDel)
                            && enquiry.getStudentID().equals(student.getUserID())) {
                        validInput = true;
                        camp.delEnquiry(enquiry);
                        System.out.println("Enquiry deleted successfully.");
                        break;
                    }
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter a valid Camp Name or 'exit' to cancel.");
            }
        }
    }

    public static void deleteEnquiry(CampCommitteeMember campCommitteeMember) {
        Scanner sc = new Scanner(System.in);

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

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Delete Enquiry (Enter Camp Name or 'exit' to cancel): ");
            String enquiryDel = sc.nextLine();

            if (enquiryDel.equalsIgnoreCase("exit")) {
                System.out.println("Deletion canceled.");
                break;
            }

            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (camp.getCampName().equals(enquiryDel)
                            && enquiry.getStudentID().equals(campCommitteeMember.getUserID())) {
                        validInput = true;
                        camp.delEnquiry(enquiry);
                        System.out.println("Enquiry deleted successfully.");
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