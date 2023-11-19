package pkg_camp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// CampCommitteeMember Menu - After log in

public class CampCommitteeMenu {
    public static void campCommitteeMenuPage(CampCommitteeMember campCommitteeMember, Camp camp) throws IOException {
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        Scanner scanner = new Scanner(System.in);
        boolean exitCampCommitteeMenu = false;

        while (!exitCampCommitteeMenu) {
            System.out.println("Camp Committee Menu:");
            System.out.println("");
            System.out.println("(1) Withdraw from a Camp");
            System.out.println("(2) View The Details of Your Camp");
            System.out.println("(3) Submit Suggestions");
            System.out.println("(4) View/Reply To Enquiries");

            System.out.println("(5) View, Edit, or Delete Your Suggestions");
            System.out.println("(6) Generate Camp Report For Camp Attendees");
            System.out.println("(7) Exit Camp Committee Menu");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    break;

                case 2:
                    CampController.viewCamps(campCommitteeMember);
                    break;

                case 3:
                    break;

                case 4:
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to view");
                        System.out.println("Press [2] to reply");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // view enquiries method
                                break;

                            case 2:
                                // reply enquiries method
                                break;
                        }
                    }
                    break;

                case 5:
                    quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to view");
                        System.out.println("Press [2] to edit");
                        System.out.println("Press [3] to delete");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // view suggestions method
                                break;

                            case 2:
                                // edit suggestions method
                                break;

                            case 3:
                                // delete suggestions method
                                break;
                        }
                    }
                    break;

                case 6:
                    // Generate Camp Report For Camp Attendees

                    System.out.println("Camp Name: " + camp.getCampName());
                    System.out.println("Dates: " + camp.getDates());
                    System.out.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                    System.out.println("Location: " + camp.getLocation());
                    System.out.println("Total Slots: " + camp.getTotalSlots());
                    System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                    System.out.println("Description: " + camp.getDescription());
                    System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                    System.out.println("Visibility: " + camp.getVisibility());
                    System.out.println("------------------------------");

                    System.out.println("Camp Attendees: " + camp.getAttendees());
                    break;

                case 7:
                    break;

                case 8:

                    break;

                case 9:
                    // submit suggestions method
                    break;

                case 10:

                case 11:

                case 12:

                    break;

                case 13:
                    exitCampCommitteeMenu = true;
                    return;
            }
        }
    }
}