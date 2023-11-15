package pkg_camp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Staff Menu - after log in
public class StaffMenu {
    public static void staffMenuPage(Staff staff) throws IOException {

        List<Camp> createdCampsList = CAM.getCreatedCampsList();

        Scanner scanner = new Scanner(System.in);
        boolean exitStaffMenu = false;

        while (!exitStaffMenu) {
            System.out.println("Staff Menu:");
            System.out.println("(1) Change Password");
            System.out.println("(2) Create, Edit or Delete Camps");
            System.out.println("(3) Toggle Camp Visibility");
            System.out.println("(4) View Camps");
            System.out.println("(5) View or Reply Enquiries");
            System.out.println("(6) Camp Suggestions");
            System.out.println("(7) Generate Camp Report");
            System.out.println("(8) Generate Performance Report");
            System.out.println("(9) Exit Menu");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {

                case 1: // Change password
                    System.out.println("Enter new Password: ");
                    String newPassword = scanner.next();
                    String oldPW = Credentials.getPassword(staff.getUserID());
                    // Add some conditions to check
                    boolean change = false;
                    while (!change) {
                        if (!newPassword.equals(oldPW)) {
                            staff.setPassword(newPassword);
                            Credentials.updatePassword(staff.getUserID(), newPassword);
                            change = true;
                        } else {
                            System.out.println("Use a Different Password!");
                        }
                    }
                    break;

                case 2: // Create, edit, delete camp
                    boolean campedits = false;
                    while (!campedits) {
                        System.out.println("(1) Create New Camp");
                        System.out.println("(2) Modify Camp");
                        System.out.println("(3) Delete Camp");
                        System.out.println("(4) Exit to Staff Menu");
                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                CreateCamp.createNewCamp(staff, createdCampsList);
                                break;

                            case 2:
                                ModifyCamp.modifyCamp(staff, createdCampsList);
                                break;

                            case 3:
                                System.out.println("Enter Camp Name: ");
                                String campNameDel = scanner.next();
                                // DeleteCamp.deleteCamp(staff, campNameDel);
                                break;

                            case 4:
                                campedits = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                        }
                    }
                    break;

                case 3: // Camp visibility
                    System.out.println("Enter Camp Name: ");
                    String campNameVisibility = scanner.next();
                    // staff.toggleCampVisibility(createdCamps, campNameVisibility);
                    break;

                case 4: // View all camps
                    ViewCamp.viewCamps(staff, createdCampsList);
                    break;

                case 5: // View or Reply enquiries
                    staff.viewAndReplyToEnquiries();
                    break;

                case 6: // Camp suggestions
                    staff.viewAndApproveSuggestions();
                    break;

                case 7: // Generate camp report
                    System.out.println("Enter attendee type (Attendee / Camp Committee): ");
                    String attendeeType = scanner.next();
                    System.out.println("Enter output file format (txt/csv): ");
                    String outputFileFormat = scanner.next();
                    // staff.generateReports(createdCamps, attendeeType, outputFileFormat);
                    break;

                case 8: // Generate performance report
                    System.out.println("Enter output file format (txt/csv): ");
                    String performanceOutputFileFormat = scanner.next();
                    // staff.generatePerformanceReport(createdCamps, performanceOutputFileFormat);
                    break;

                case 9: // Exit Staff Menu
                    // Upload.deleteAll();
                    Upload.writeToExcel(createdCampsList);
                    exitStaffMenu = true;
                    // scanner.close();
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
        scanner.close();
        return;
    }
}
