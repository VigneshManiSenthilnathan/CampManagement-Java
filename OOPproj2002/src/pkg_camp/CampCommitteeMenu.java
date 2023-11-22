package pkg_camp;

import java.io.IOException;
import java.util.Scanner;

// CampCommitteeMember Menu - After log in

public class CampCommitteeMenu {
    public static void campCommitteeMenuPage(CampCommitteeMember campCommitteeMember, Camp camp) throws IOException {
        // List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        Scanner scanner = new Scanner(System.in);
        boolean exitCampCommitteeMenu = false;

        while (!exitCampCommitteeMenu) {
            System.out.println("Camp Committee Menu:");
            System.out.println(""); // new line
            System.out.println("(1) View The Details of Your Camp"); // campcommittee can choose to view the details of
                                                                     // their own camps
            System.out.println("(2) Submit Suggestions");
            System.out.println("(3) View/Reply To Enquiries");
            System.out.println("(4) View, Edit, or Delete Your Suggestions");
            System.out.println("(5) Generate Camp Report For Camp Attendees");
            System.out.println("(6) Return to Student Menu"); // done

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    CampController.viewCamps(campCommitteeMember, camp);
                    break;

                case 2:
                    SuggestionController.submitSuggestion(campCommitteeMember);
                    break;

                case 3:
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to View Enquiries");
                        System.out.println("Press [2] to Reply Enquiries");
                        System.out.println("Press [3] to Exit");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // view enquiries method
                                EnquiryController.viewEnquiry(campCommitteeMember);
                                break;

                            case 2:
                                // reply enquiries method
                                EnquiryController.replyEnquiry(campCommitteeMember);
                                break;

                            case 3:
                                quit = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 4:
                    quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to View");
                        System.out.println("Press [2] to Edit");
                        System.out.println("Press [3] to Delete");
                        System.out.println("Press [4] to Exit");
                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                SuggestionController.viewSuggestion(campCommitteeMember);
                                break;

                            case 2:
                                SuggestionController.editSuggestion(campCommitteeMember);
                                break;

                            case 3:
                                SuggestionController.deleteSuggestion(campCommitteeMember);
                                break;

                            case 4:
                                quit = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 5:
                    // Generate Camp Report For Camp Attendees
                    ReportController.campCommitteeGR(campCommitteeMember, camp);
                    break;

                case 6:
                    exitCampCommitteeMenu = true;
                    return;
            }
        }
    }
}