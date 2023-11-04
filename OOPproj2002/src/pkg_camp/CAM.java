package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pkg_camp.Student.StudentType;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class CAM {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        List<Staff> staffList = new ArrayList<>();
        List<Camp> createdCamps = new ArrayList<>();

        FileInputStream excelFile1 = new FileInputStream(new File("student_list.xlsx")); // reads data from file path:
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

            // need to also add in credentials excel: "user_passwords.xlsx"
            // Credentials newUser = new Credentials(userID, "password");
            // newUser.excelWriter(userID, "password");

            studentList.add(student);
        }

        FileInputStream excelFile2 = new FileInputStream(new File("staff_list.xlsx"));
        Workbook workbook2 = new XSSFWorkbook(excelFile2);
        Sheet sheet2 = workbook2.getSheet("staff");

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
                            // if studentID is correct and
                            // its their first time logging
                            // in, ask them to change
                            // password

                            boolean changed = false;

                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.nextLine();

                                if (newPassword != "password") { // if newPassword is not the same as old password
                                                                 // "password", change the password to newPassword
                                    System.out.println("Password Successfully Changed!");
                                    student.setPassword(newPassword);
                                    changed = true; // exit asking them to change password
                                }

                                else { // if they input the same password, ask them to input again
                                    System.out.println("Use a Different Password!");
                                }
                            }

                        } else if (userID == student.getUserID() && password == student.getPassword()
                                && password != "password") { // userID and password is correct, not their first time
                                                             // logging in
                            System.out.println("Student Login Successful!");
                            // Redirect to student menu method below
                            studentMenuPage(student, createdCamps);
                            exitstudentlogin = true;
                        } else {
                            System.out.println("Invalid login credentials.");
                        }
                    }
                }
            }

            else if (choice == 2) {
                boolean exitstafflogin = false;
                while (!exitstafflogin) {
                    System.out.println("Staff Login:");
                    System.out.print("UserID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Password (default: password): ");
                    String password = scanner.nextLine();
                    System.out.print("Faculty: ");
                    String faculty = scanner.nextLine();

                    for (Staff staff : staffList) {
                        if (staff.getUserID() == userID && password == "password") {
                            // if staffID is correct and its their first time logging in, ask them to change
                            // password
                            boolean changed = false;
                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.nextLine();

                                if (newPassword != "password") { // if newPassword is not the same as old password
                                                                 // "password", change the password to newPassword
                                    System.out.println("Password Successfully Changed!");
                                    staff.setPassword(newPassword);
                                    changed = true; // exit asking them to change password
                                }

                                else { // if they input the same password, ask them to input again
                                    System.out.println("Use a Different Password!");
                                }
                            }
                        }

                        else if (userID == staff.getUserID() && password == staff.getPassword()
                                && password != "password") { // userID and password is correct, not their first time
                                                             // logging in
                            System.out.println("Staff Login Successful!");
                            // Redirect to student menu method below
                            staffMenuPage(staff, createdCamps);
                            exitstafflogin = true;
                        }

                        else {
                            System.out.println("Invalid login credentials.");
                        }
                    }
                }
            }
        }
        scanner.close();
    }

    // Student Menu - after log in
    public static void studentMenuPage(Student student, List<Camp> createdCamps){
            
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
            System.out.print("Input Choice: ");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("Enter new Password: ");
                    String newPassword = scanner.nextLine();
                    // Add some conditions to check
                    boolean change = false;
                    while(!change){
                        if (newPassword != student.getPassword()){
                            student.setPassword(newPassword);
                            change = true;
                        }
                        else {
                            System.out.println("Use a Different Password!");
                        }
                    }
                break;

                case 2:
                    student.viewCamps(createdCamps);
                    break;

                case 3:
                    System.out.println("Registering as:");
                    System.out.println("(1) Attendee");
                    System.out.println("(2) Camp Committee Member");
                    int role_choice = scanner.nextInt();
                    if (role_choice == 1) {
                        student.setStudentType(StudentType.ATTENDEE); 
                    }
                    else if (role_choice == 2){
                        student.setStudentType(StudentType.COMMITTEE); 
                    }

                    student.registerForCamp(createdCamps, student);
                    break;

                case 4:
                    System.out.println("Send Enquiry To:");
                    System.out.println("(1) Camp Staff");
                    System.out.println("(2) Camp Committee Member");
                    int choice = scanner.nextInt();
                    student.newEnquiry(User.receiver);
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
                                List<Enquiry> enqList = student.getEnquiries();
                                System.out.println(enqList);
                                quit = true;
                                break;
                            case 2:
                                System.out.println("Choose the enquiry to edit below")
                                List<Enquiry> enqToEdit = student.getEnquiries();
                                System.out.println(enqToEdit);
                                int editIndex = scanner.nextInt();
                                System.out.println("Enter your new message: ")
                                String newMsg = scanner.nextLine();
                                

                                for (Enquiry enquiry : enqToEdit) {
                                    int i = 0;
                                    // Account for entry of i > length of enquiry list
                                    if (i == editIndex){
                                        student.editEnquiry(enquiry, newMsg);
                                    }

                                    i++;
                                }
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
                    System.out.println("Camps you have registered for: ");
                    System.out.print(student.getEnquiries());

                    System.out.println("Choose the camp you would like to withdraw from: ");
                    int withdrawIndex = scanner.nextInt();

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

    // Staff Menu - after log in
    public static void staffMenuPage(Staff staff, List<Camp> createdCamps) {

        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Enter new Password: ");
                    String newPassword = scanner.nextLine();
                    // Add some conditions to check
                    boolean change = false;
                    while (!change) {
                        if (newPassword != staff.getPassword()) {
                            staff.setPassword(newPassword);
                            change = true;
                        } else {
                            System.out.println("Use a Different Password!");
                        }
                    }
                    break;

                case 2: // Create, edit, delete camp
                    break;

                case 3: // Camp visibility
                    break;

                case 4: // View list of all camps
                    break;

                case 5: // View List of Created camps
                    break;

                case 6: // View or Reply enquiries
                    break;

                case 7: // Camp suggestions
                    break;

                case 8: // Generate camp report
                    break;

                case 9: // Generate performance report
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
            exitStaffMenu = true;
        }
    }
}
