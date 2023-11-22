package pkg_camp;

import java.util.Scanner;

public class ReplyEnquiry {

    public static void replyEnquiry(CampCommitteeMember campCommitteeMember) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        boolean found = false;
        System.out.println("The Enquiries are: ");
        int i = 1;
        int totalEnquiries = 0;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getEnquiryList().size() > 0) {
                System.out.println("");

                System.out.println("Camp: " + camp.getCampName());
                System.out.println("");

                for (Enquiry enquiry : camp.getEnquiryList()) {
                    if (camp.getCampCommitteeList().contains(campCommitteeMember)) {
                        System.out.println(
                                i + ". Enquiry: " + enquiry.getEnquiryString());
                        i++;
                        totalEnquiries++;
                        found = true;
                    }
                }
                System.out.println("");
                System.out.println("-------------------------------------------------");
            }
        }

        if (!found) {
            System.out.println("There are no camps for you to view enquiries of!");
            System.out.println("");
            return;
        }

        boolean replyDone = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());
        
        while (!replyDone) {
            System.out.println("Which Enquiry are you replying to?");

            choice = sc.nextInt();

            if (choice > totalEnquiries || choice < 1) {
                System.out.println("Invalid choice!");
                System.out.println("Please choose from the above list of enquiries.");
                System.out.println("");
                continue;
            }

            i = 1;

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getCampCommitteeList().contains(campCommitteeMember)) {
                    for (Enquiry enquiry : camp.getEnquiryList()) {
                        if (choice == i) {
                            System.out.println("Enter your Reply: ");
                            String reply = sc.next();
                            enquiry.setReply(reply);
                            enquiry.setRepliedBy(campCommitteeMember.getUserID());
                            enquiry.setReplierType("CAMP_COMMITTEE_MEMBER");
                            replyDone = true;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
    }

    public static void replyEnquiry(Staff staff) {
        // Check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        boolean found = false;
        int totalEnquiries = 0;
        System.out.println("The Enquiries are: ");

        int i = 1;

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getEnquiryList().size() > 0 && camp.getStaffInCharge().equalsIgnoreCase(staff.getUserID())) {
                System.out.println("Camp: " + camp.getCampName());
                System.out.println("");

                for (Enquiry enquiry : camp.getEnquiryList()) {
                    totalEnquiries++;
                    System.out.println(i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                }

                System.out.println("-------------------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no camps for you to view Enquiries of!");
            System.out.println("");
            return;
        }

        boolean replyDone = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while (!replyDone) {
            System.out.println("Which Enquiry are you replying to?");

            choice = sc.nextInt();

            if (choice > totalEnquiries || choice < 1) {
                System.out.println("Invalid choice!");
                System.out.println("Please choose from the above list of enquiries.");
                System.out.println("");
                continue;
            }

            i = 1;

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getStaffInCharge().equalsIgnoreCase(staff.getUserID())) {
                    for (Enquiry enquiry : camp.getEnquiryList()) {
                        if (choice == i) {
                            System.out.println("Enter your Reply: ");
                            String reply = sc.nextLine();
                            enquiry.setReply(reply);
                            enquiry.setRepliedBy(staff.getUserID());
                            enquiry.setReplierType("STAFF");
                            replyDone = true;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
    }
}