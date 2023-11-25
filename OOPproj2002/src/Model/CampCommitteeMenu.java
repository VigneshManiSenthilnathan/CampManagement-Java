package model;

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
            System.out.println("(1) Change Password"); // done
            System.out.println("(2) View Available Camps"); // done
            System.out.println("(3) Register for a Camp"); // done
            System.out.println("(4) Submit Enquiry to a Camp"); // done
            System.out.println("(5) Manage your Enquiries"); // done
            System.out.println("(6) View/Reply To Enquiries (Camp Committee)"); // done
            System.out.println("(7) Withdraw from a Camp"); // done
            System.out.println("(8) Submit Suggestions to a Camp (Camp Committee)");
            System.out.println("(9) Manage Your Suggestions (Camp Committee)");
            System.out.println("(10) Generate Camp Report for Camp Attendees (Camp Committee)");
            System.out.println("(0) Exit Menu"); // done
            System.out.print("Input Choice: ");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 0:
                    exitCampCommitteeMenu = true;
                    return;

                case 1: // change password
                    ManageCredentials.changePassword(campCommitteeMember);
                    break;

                case 2: // view camps
                    CampController.viewCamps(campCommitteeMember);
                    break;

                case 3: // register for camps
                    CampController.registerForCamp(campCommitteeMember);
                    break;

                case 4: // submit enquiry
                    EnquiryController.submitEnquiry(campCommitteeMember);
                    break;

                case 5: // manage own enquiries
                    boolean modifyDone = false;
                    while (!modifyDone) {
                        System.out.println("[1] View all Enquiries");
                        System.out.println("[2] Modify Enquiry");
                        System.out.println("[3] Delete Enquiry");
                        System.out.println("[0] Exit");

                        Scanner sc = new Scanner(System.in);
                        int choice = sc.nextInt();

                        switch (choice) {
                            case 0:
                                modifyDone = true;
                                break;

                            case 1:
                                EnquiryController.viewOwnEnquiry(campCommitteeMember);
                                break;

                            case 2:
                                EnquiryController.modifyEnquiry(campCommitteeMember);
                                break;

                            case 3:
                                EnquiryController.deleteEnquiry(campCommitteeMember);
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 6: // campcomm view/reply enquiries
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to View Enquiries");
                        System.out.println("Press [2] to Reply Enquiries");
                        System.out.println("Press [0] to Exit");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 0:
                                quit = true;
                                break;

                            case 1:
                                // view enquiries method
                                EnquiryController.viewEnquiry(campCommitteeMember, camp);
                                break;

                            case 2:
                                // reply enquiries method
                                EnquiryController.replyEnquiry(campCommitteeMember, camp);
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 7: // withdraw from camp
                    CampController.withdrawCamp(campCommitteeMember);
                    break;

                case 8: // submit suggestion
                    SuggestionController.submitSuggestion(campCommitteeMember);
                    break;

                case 9: // modify suggestion
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

                case 10:
                    // Generate Camp Report For Camp Attendees
                    ReportController.campCommitteeGR(campCommitteeMember, camp);
                    break;
            }
        }
    }
}