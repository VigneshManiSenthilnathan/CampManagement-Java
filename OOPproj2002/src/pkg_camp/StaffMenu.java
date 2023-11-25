package pkg_camp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Staff Menu - after log in
public class StaffMenu {
    public static void staffMenuPage(Staff staff) throws IOException {
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();

        Scanner scanner = new Scanner(System.in);
        boolean exitStaffMenu = false;

        while (!exitStaffMenu) { // While the user is not done with Staff Menu
            try {
                System.out.println("Staff Menu:");
                System.out.println("(1) Change Password");
                System.out.println("(2) View, Create or Edit Camps");
                System.out.println("(3) Delete Camps");
                System.out.println("(4) Manage Camps");
                System.out.println("(5) View or Reply Enquiries");
                System.out.println("(6) View Suggestions To Camps");
                System.out.println("(7) Approve Camp Suggestions");
                System.out.println("(8) Generate Camp Report");
                System.out.println("(9) Generate Performance Report");
                System.out.println("(10) Exit Menu");

                int menu = scanner.nextInt();
                scanner.useDelimiter(System.lineSeparator());

                switch (menu) {
                    case 1: // Change password
                        ManageCredentials.changePassword(staff);
                        break;

                    case 2: // View, Create, Edit camp
                        boolean campedits = false;
                        while (!campedits) {
                            System.out.println("(1) View All Camp");
                            System.out.println("(2) Create a New Camp");
                            System.out.println("(3) Modify Camp");
                            System.out.println("(0) Exit to Staff Menu");
                            int choice = scanner.nextInt();

                            switch (choice) {
                                case 0:
                                    campedits = true;
                                    break;

                                case 1:
                                    CampController.viewCamps(staff);
                                    break;

                                case 2:
                                    CampController.createNewCamp(staff);
                                    break;

                                case 3:
                                    CampController.modifyCamp(staff);
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please choose a valid option.");
                            }
                        }
                        break;

                    case 3: // Delete camps
                        CampController.deleteCamp(staff);
                        break;

                    case 4: // Manage Camp Committee
                        CampController.manageCreatedCamps(staff);
                        break;

                    case 5: // View or Reply enquiries
                        boolean enquiryDone = false;

                        while (!enquiryDone) {
                            System.out.println("[1] View all Enquiries");
                            System.out.println("[2] Modify Enquiry");
                            System.out.println("[3] Delete Enquiry");
                            System.out.println("[4] Exit");

                            Scanner sc = new Scanner(System.in);
                            int choice = sc.nextInt();

                            switch (choice) {
                                case 1:
                                    EnquiryController.viewEnquiry(staff);
                                    break;

                                case 2:
                                    EnquiryController.replyEnquiry(staff);
                                    break;
                                case 4:
                                    enquiryDone = true;
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please choose a valid option.");
                                    break;
                            }
                        }
                        break;

                    case 6: // Camp suggestions
                        SuggestionController.viewSuggestion(staff);
                        break;

                    case 7:
                        SuggestionController.approveSuggestion(staff);
                        break;

                    case 8: // Generate camp report
                        ReportController.staffGR(staff, createdCampsList);
                        break;

                    case 9: // Generate performance report
                        ReportController.getPerformanceReport(staff, createdCampsList);
                        break;

                    case 10: // Exit Staff Menu

                        CampsList.setCreatedCampsList(createdCampsList);
                        Upload.writeToExcel(createdCampsList);
                        Upload.suggestionsWriter();
                        Upload.enquiriesWriter();
                        exitStaffMenu = true;

                        break;

                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                continue;
            }
        }
        return;
    }
}
