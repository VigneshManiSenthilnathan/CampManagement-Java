package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class CAM {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        List<Staff> staffList = new ArrayList<>();

        FileInputStream excelFile1 = new FileInputStream(new File("student_list.xlsx")); // reads data from file path
                                                                                         // student_list
        Workbook workbook1 = new XSSFWorkbook(excelFile1); // XSSFWorkbook constructor takes excelFile1 as parameter and
                                                           // init data from excel file
        Sheet sheet1 = workbook1.getSheet("student"); // retrieves student sheet from workbook, sheet1 then holds a
                                                      // reference to student sheet

        for (Row row : sheet1) {
            String userID = row.getCell(1).getStringCellValue();
            String faculty = row.getCell(2).getStringCellValue();

            if (userID.contains("@")) {
                String[] parts = userID.split("@");
                userID = parts[0].trim();
            }
            Student student = new Student(userID, "password", faculty);
            studentList.add(student);
        }

        FileInputStream excelFile2 = new FileInputStream(new File("student_list.xlsx"));
        Workbook workbook2 = new XSSFWorkbook(excelFile2);
        Sheet sheet2 = workbook2.getSheet("student");

        for (Row row : sheet2) {
            String userID = row.getCell(1).getStringCellValue();
            String faculty = row.getCell(2).getStringCellValue();

            if (userID.contains("@")) {
                String[] parts = userID.split("@");
                userID = parts[0].trim();
            }
            Staff staff = new Staff(userID, "password", faculty);
            staffList.add(staff);
        }

        Staff staff = null;
        Camp camp = null;
        List<Camp> createdCamps = null;

        Scanner scanner = new Scanner(System.in);

        boolean exitmain = false;

        while (!exitmain) {
            System.out.println("Main Menu:");
            System.out.println("(1) Login as Student");
            System.out.println("(2) Login as Staff");
            System.out.println("(3) Exit CAMs");

            int choice = scanner.nextInt();

            if (choice == 1) {
                boolean exitstudentlogin = false;
                while (!exitstudentlogin) {
                    System.out.println("| Student Login |");
                    System.out.print("UserID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Password (default is: password): ");
                    String password = scanner.nextLine();

                    for (Student student : studentList) {
                        if (student.getUserID() == userID && password == "password") {

                            boolean changed = false;

                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.nextLine();

                                if (newPassword != "password") {
                                    System.out.println("Password Successfully Changed!");
                                    student.setPassword(newPassword);
                                    changed = true;
                                } else {
                                    System.out.println("Use a Different Password!");
                                }
                            }
                        } else if (student.getUserID() == userID && password != "password") {
                            System.out.println("Student Login Successful!");
                            exitstudentlogin = true;
                            studentMenuPage(student);
                        } else {
                            System.out.println("Invalid login credentials.");
                        }
                    }
                }
            }

            else if (choice == 2) {
                System.out.println("Staff Login:");
                System.out.print("UserID: ");
                String userID = scanner.nextLine();
                System.out.print("Password (default: password): ");
                String password = scanner.nextLine();
                System.out.print("Faculty: ");
                String faculty = scanner.nextLine();

                if (password.equals("password")) {
                    Staff staff = new Staff(userID, password, faculty);
                }

                else {
                    System.out.println("Invalid login credentials.");
                }
                boolean exitStaffMenu = false;
                while (!exitStaffMenu) {
                    System.out.println("Staff Menu:");
                    System.out.println("(1) Change Password");
                    System.out.println("(2) Create, Edit or Delete Camps");
                    System.out.println("(3) Toggle Camp Visibility");
                    System.out.println("(4) View All Camps");
                    System.out.println("(5) View List Created Camps");
                    System.out.println("(6) View or Reply Enquiries");
                    System.out.println("(7) Camp Suggestions");
                    System.out.println("(8) Generate Camp Report");
                    System.out.println("(9) Generate Performance Report");

                    int menu = scanner.nextInt();

                    switch (menu) {

                        case 1: // Change password
                            String newPassword = scanner.nextLine();
                            staff.setPassword(newPassword);
                            exitStaffMenu = true;
                            break;

                        case 2: // Create, edit, delete camp

                        case 3: // Camp visibility

                        case 4: // View list of all camps

                        case 5: // View List of Created camps

                        case 6: // View or Reply enquiries

                        case 7: // Camp suggestions

                        case 8: // Generate camp report

                        case 9: // Generate performance report

                        default:
                            System.out.println("Invalid choice. Please choose a valid option.");

                    }

                }
            }

            else {
                exit = true;
            }
        }

        scanner.close();

    }

    public static void studentMenuPage(Student student){
            
        Scanner scanner = new Scanner(System.in);
        boolean exitstudentmenu = false;

        while (!exitstudentmenu) {
            System.out.println("Student Menu:");
            System.out.println("(1) Change Password");
            System.out.println("(2) View List of Camps");
            System.out.println("(3) Register for a Camp");
            System.out.println("(4) Submit Enquiry");
            System.out.println("(5) View, Edit or Delete your Enquiry");
            System.out.println("(6) Check Registered Camps");
            System.out.println("(7) Withdraw from a Camp");
            System.out.print("Input Choice Digit: ");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1:s
                    System.out.println("What is your new Password?: ")
                    String newPassword = scanner.nextLine();
                    student.setPassword(newPassword);
                    exitstudentmenu = true;
                    break;

                case 2:
                    student.viewCamps(createdCamps);
                    break;

                case 3:
                    System.out.println("Registering as:");
                    System.out.println("(1) Attendee");
                    System.out.println("(2) Camp Committee Member");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        student.StudentType = 1;
                    }

                    student.registerForCamp();
                    break;

                case 4:
                    student.newEnquiry();
                    break;

                case 5:
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press 1 to view");
                        System.out.println("Press 2 to edit");
                        System.out.println("Press 3 to delete");
                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                student.getEnquiries();
                                quit = true;
                                break;
                            case 2:
                                student.editEnquiry();
                                quit = true;
                                break;
                            case 3:
                                student.deleteEnquiry();
                                quit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please choose a valid option.");
                        }
                    }
                    break;

                case 6:
                    break;

                case 7:
                    boolean quit1 = false;
                    System.out.println("Are you sure you want to withdraw from the camp(Y/N)?");
                    while (quit1 != true) {
                        String Decision = scanner.nextLine().toUpperCase();
                        char c = Decision.charAt(0);
                        if (c == 'Y') {
                            student.withdraw();
                            break;
                        } else if (c == 'N') {
                            break;
                        } else {
                            System.out.println("Error Please enter either Y or N");
                            continue;
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
        exitstudentmenu = false;
}
