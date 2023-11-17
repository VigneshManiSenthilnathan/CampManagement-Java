package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

// Student Menu - after log in
public class StudentMenu {

    public static void studentMenuPage(Student student) throws IOException {
        List<Camp> createdCampsList = CampController.getCreatedCampsList();

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
            System.out.println("(8) Show Camp Committee Menu For Camp Committee Members");
            System.out.println("(9) Exit Menu"); // done
            System.out.print("Input Choice: ");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    System.out.println("Enter new Password: ");
                    String newPassword = scanner.next(); // storing of new pw
                    String oldPW = Credentials.getPassword(student.getUserID()); // old PW
                    // Add some conditions to check
                    boolean change = false;
                    while (!change) {
                        if (!newPassword.equals(oldPW)) { // comparing both pw to ensure they are not the same
                            student.setPassword(newPassword); // changing password only if password is different
                            Credentials.updatePassword(student.getUserID(), newPassword); // updating credientials excel
                                                                                          // with new information
                            change = true;
                        } else {
                            System.out.println("Use a Different Password!");
                        }
                    }
                    break;

                case 2:
                    ViewCamp.studentMenuViewCamps(student, createdCampsList);
                    break;

                case 3:
                    System.out.println("Registering as:");
                    System.out.println("(1) Attendee");
                    System.out.println("(2) Camp Committee Member");

                    int role_choice = scanner.nextInt();

                    if (role_choice == 1) {
                        createdCampsList = Registration.registerForCamp(student, createdCampsList);
                    }

                    else if (role_choice == 2) {
                        createdCampsList = Registration.registerForCampCommitee(student, createdCampsList);
                        System.out.println("Pending Approval from Staff . . .");
                    }

                    break;

                case 4:
                    System.out.print("Send Enquiry To:");
                    System.out.println("(1) Camp Staff");
                    System.out.println("(2) Camp Committee Member");

                    int choice = scanner.nextInt();

                    System.out.println("Enter your enquiry!");
                    String enquiryMade = scanner.next();

                    switch (choice) {
                        case 1:
                            // enquiry.addEnquiry(student.getUserID(), "STAFF", enquiryMade);
                            break;

                        case 2:
                            // enquiry.addEnquiry(student.getUserID(), "COMMITTEE", enquiryMade);
                            break;

                        default:
                            System.out.println("Enter a valid number! [1] or [2] only");
                            System.out.println("Enter 4 again to retry!");
                            break;
                    }

                    break;

                case 5:
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to view");
                        System.out.println("Press [2] to edit");
                        System.out.println("Press [3] to delete");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // List<String> enqList = enquiry.getEnquiriesBySender(student.getUserID());
                                // enqList.forEach(System.out::println);
                                quit = true;
                                break;

                            case 2:
                                /*
                                 * System.out.println("Choose the enquiry to edit below")
                                 * List<EnquiryController> enqToEdit = student.getEnquiries()
                                 * System.out.println(enqToEdit)
                                 * int editIndex = scanner.nextInt()
                                 * System.out.println("Enter your new message: ")
                                 * String newMsg = scanner.next()
                                 * 
                                 * or (EnquiryController enq : enqToEdit)
                                 * int i = 0
                                 * // Account for entry of i > length of enquiry lis
                                 * if (i == editIndex)
                                 * student.editEnquiry(enq, newMsg)
                                 * 
                                 * 
                                 * ++
                                 * 
                                 * quit = true
                                 */
                                break;

                            case 3:
                                // student.deleteEnquiry();
                                quit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                        }
                    }
                    break;

                case 6:
                    // Check the camp student is registered in and display it
                    Registration.dispRegisteredCamps(student, createdCampsList);
                    break;

                case 7:
                    createdCampsList = Withdrawal.withdrawCamp(student, createdCampsList);
                    break;

                case 8:
                    // if retrieved student name matches with campcommittee member file, call
                    // campcommitteemenu
                    // else show rejection message, "only camp committee members can access this"

                case 9:
                    CampController.setCreatedCampsList(createdCampsList);
                    Upload.writeToExcel(createdCampsList);
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