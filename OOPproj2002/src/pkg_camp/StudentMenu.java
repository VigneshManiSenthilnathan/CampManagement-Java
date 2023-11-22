package pkg_camp;

import java.io.IOException;
import java.util.List;

import java.util.Scanner;

// Student Menu - after log in
public class StudentMenu {

    public static void studentMenuPage(Student student) throws IOException {

        List<Camp> createdCampsList = CampsList.getCreatedCampsList();

        /*
         * if (student.isCampCommitteeMember()) {
         * // downacasting student to campcommitteemember
         * CampCommitteeMenu.campCommitteeMenuPage((CampCommitteeMember) student);
         * }
         */

        Scanner scanner = new Scanner(System.in);
        boolean exitStudentMenu = false;

        while (!exitStudentMenu) {
            System.out.println("Student Menu:");
            System.out.println("(1) Change Password"); // done
            System.out.println("(2) View Available Camps"); // done
            System.out.println("(3) Register for a Camp"); // done
            System.out.println("(4) Submit Enquiry");
            System.out.println("(5) View, Edit or Delete your Enquiry");
            System.out.println("(6) Check Registered Camps"); // done
            System.out.println("(7) Withdraw from a Camp"); // done
            System.out.println("(8) Show Camp Committee Menu For Camp Committee Members"); // done
            System.out.println("(9) Exit Menu"); // done
            System.out.print("Input Choice: ");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    ManageCredentials.changePassword(student);
                    break;

                case 2:
                    CampController.viewCamps(student);
                    break;

                case 3:
                    while (true) {
                        System.out.println("Registering as:");
                        System.out.println("(1) Attendee");
                        System.out.println("(2) Camp Committee Member");

                        int role_choice = scanner.nextInt();

                        if (role_choice == 1) {
                            CampController.registerForCamp(student, createdCampsList);
                            break;
                        }

                        else if (role_choice == 2) {
                            CampController.registerForCampCommitee(student);
                            break;
                        }

                        else {
                            System.out.println("Please press either 1 or 2");
                            continue;
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
                    // Check the camp student is registered in and display it
                    Registration.dispRegisteredCamps(student, createdCampsList);
                    break;

                case 7:
                    Withdrawal.withdrawCamp(student);
                    break;

                case 8:
                    // if retrieved student name matches with campcommittee member file, call
                    // campcommitteemenu
                    // else show rejection message, "only camp committee members can access this"
                    for (Camp camp : createdCampsList) {
                        for (Student comm : camp.getCampCommitteeList()) {
                            if (comm.getUserID().equals(student.getUserID())) {
                                System.out.println("Entering Camp Committee Menu");
                                System.out.println("");

                                // Downcast and enter
                                if (comm instanceof CampCommitteeMember) {
                                    CampCommitteeMember newcomm = (CampCommitteeMember) comm;
                                    CampCommitteeMenu.campCommitteeMenuPage(newcomm, camp);
                                }

                            }
                        }
                    }

                    System.out.println("Invalid access, only camp committee members can access this");
                    break;

                case 9:
                    CampsList.setCreatedCampsList(createdCampsList);
                    Upload.writeToExcel(CampsList.getCreatedCampsList());
                    Upload.suggestionsWriter();
                    Upload.enquiriesWriter();
                    exitStudentMenu = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return;
    }
}