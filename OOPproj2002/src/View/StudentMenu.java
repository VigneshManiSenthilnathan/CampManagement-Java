package pkg_camp;

import java.io.IOException;
import java.util.List;

import java.util.Scanner;

// Student Menu - after log in
public class StudentMenu {

    public static void studentMenuPage(Student student) throws IOException {

        List<Camp> createdCampsList = CampsList.getCreatedCampsList();

        Scanner scanner = new Scanner(System.in);
        boolean exitStudentMenu = false;

        while (!exitStudentMenu) {
            System.out.println("");
            System.out.println("Student Menu:");
            System.out.println("-------------------------------------");
            System.out.println("(1) Change Password"); // done
            System.out.println("(2) View Camps"); // done
            System.out.println("(3) Register for a Camp"); // done
            System.out.println("(4) Submit Enquiry to a Camp"); // done
            System.out.println("(5) Manage your Enquiries"); // done
            System.out.println("(6) Withdraw from a Camp"); // done
            System.out.println("(0) Exit Menu"); // done
            System.out.print("Input Choice: ");
            System.out.println("");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 0:
                    CampsList.setCreatedCampsList(createdCampsList);
                    Upload.writeToExcel(CampsList.getCreatedCampsList());
                    Upload.suggestionsWriter();
                    Upload.enquiriesWriter();
                    exitStudentMenu = true;
                    break;

                case 1:
                    ManageCredentials.changePassword(student);
                    return;

                case 2:
                    CampController.viewCamps(student);
                    break;

                case 3:
                    CampController.registerForCamp(student);
                    // check if Student has become a Camp Committee Member
                    // if Yes, go into CampCommitteeMenu
                    for (Camp camp : CampsList.getCreatedCampsList()) {
                        for (CampCommitteeMember campCommitteeMember : camp.getCampCommitteeList()) {
                            if (campCommitteeMember.getUserID().equalsIgnoreCase(student.getUserID())) {
                                CampCommitteeMember newCampCommitteeMember = (CampCommitteeMember) student;
                                CampCommitteeMenu.campCommitteeMenuPage(newCampCommitteeMember, camp);
                                return;
                            }
                        }
                    }
                    break;

                case 4:
                    EnquiryController.submitEnquiry(student);
                    break;

                case 5:
                    boolean modifyDone = false;

                    while (!modifyDone) {
                        System.out.println("[1] View all Enquiries");
                        System.out.println("[2] Modify Enquiry");
                        System.out.println("[3] Delete Enquiry");
                        System.out.println("[4] Exit");

                        Scanner sc = new Scanner(System.in);
                        int choice = sc.nextInt();

                        switch (choice) {
                            case 1:
                                EnquiryController.viewEnquiry(student);
                                break;

                            case 2:
                                EnquiryController.modifyEnquiry(student);
                                break;

                            case 3:
                                EnquiryController.deleteEnquiry(student);
                                break;

                            case 4:
                                modifyDone = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 6:
                    Withdrawal.withdrawCamp(student);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return;
    }
}