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
                System.out.println("(2) Create, Edit or Delete Camps");
                // System.out.println("(3) Toggle Camp Visibility");
                System.out.println("(3) View Camps");
                System.out.println("(4) Manage Camp Committee Members");
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

                    case 2: // Create, edit, delete camp
                        boolean campedits = false;
                        while (!campedits) {
                            System.out.println("(1) Create New Camp");
                            System.out.println("(2) Modify Camp/Toggle Visibility");
                            System.out.println("(3) Delete Camp");
                            System.out.println("(4) Exit to Staff Menu");
                            int choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    CampController.createNewCamp(staff);
                                    break;

                                case 2:
                                    CampController.modifyCamp(staff);
                                    break;

                                case 3:
                                    CampController.deleteCamp(staff);
                                    break;

                                case 4:
                                    campedits = true;
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please choose a valid option.");
                            }
                        }
                        break;

                    case 3: // View camps
                        CampController.viewCamps(staff);
                        break;

                    case 4: // Manage Camp Committee
                        ManageCampCommitteeMember.ManageCCMember(staff);
                        break;

                    case 5: // View or Reply enquiries
                        // staff.viewAndReplyToEnquiries();
                        break;

                    case 6: // Camp suggestions
                        SuggestionController.ViewSuggestion(staff);
                        break;

                    case 7:
                        SuggestionController.ApproveSuggestion(staff);
                        break;

                    case 8: // Generate camp report
                        ReportController.staffGR(staff, createdCampsList);
                        break;

                    case 9: // Generate performance report
                        ReportController.getPerformanceReport(staff, createdCampsList);
                        break;

                    case 10: // Exit Staff Menu
                        // Upload.deleteAll();
                        CampsList.setCreatedCampsList(createdCampsList);
                        Upload.writeToExcel(createdCampsList);
                        Upload.suggestionsWriter();
                        exitStaffMenu = true;
                        // scanner.close();
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
