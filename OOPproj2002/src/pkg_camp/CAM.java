package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pkg_camp.Student.StudentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class CAM {
    private static List<CampInfoController> createdCamps = new ArrayList<>();
    private static List<Staff> staffList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // System.out.println("Current working directory: " +
        // System.getProperty("user.dir"));
        // Student
        try {
            // reads data from file path: student_list
            FileInputStream excelFile1 = new FileInputStream(new File("OOPproj2002/src/pkg_camp/student_list.xlsx"));

            // XSSFWorkbook constructor takes excelFile1 as parameter and init data from
            // excel file
            Workbook workbook1 = new XSSFWorkbook(excelFile1);

            // retrieves student sheet from workbook, sheet1 then holds a reference to
            // student sheet
            Sheet sheet1 = workbook1.getSheet("student");

            for (Row row : sheet1) {
                String userID = row.getCell(1).getStringCellValue();
                String faculty = row.getCell(2).getStringCellValue();

                if (userID.contains("@")) {
                    String[] parts = userID.split("@");
                    userID = parts[0].trim();
                }
                // System.out.println(userID);
                Student student = new Student(userID, "password", faculty);

                // need to also add in credentials excel: "user_passwords.xlsx"
                Credentials newUser = new Credentials(userID, "password");
                String encodedPW = Credentials.encodePassword("password");
                newUser.excelWriter(userID, encodedPW);

                studentList.add(student);
                excelFile1.close();
                workbook1.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Staff
        try {
            FileInputStream excelFile2 = new FileInputStream(new File("OOPproj2002/src/pkg_camp/staff_list.xlsx"));
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

                // need to also add in credentials excel: "user_passwords.xlsx"
                Credentials newUser = new Credentials(userID, "password");
                String encodedPW = Credentials.encodePassword("password");
                newUser.excelWriter(userID, encodedPW);

                staffList.add(staff);
                excelFile2.close();
                workbook2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                boolean validcredentials = false;
                int errorCount = 0;

                while (!exitstudentlogin) {
                    scanner.useDelimiter(System.lineSeparator());
                    System.out.println("| Student Login |");
                    System.out.print("UserID:");
                    String userID = scanner.next();

                    if (!Credentials.usernameExists(userID)) {
                        System.out.print("Invalid Username");
                        break;
                    }

                    System.out.println("Password (default is: password):");
                    String password = scanner.next();

                    if (Credentials.verifyPassword(userID, password)) {
                        validcredentials = true;
                    }

                    while (validcredentials) {
                        Student thisStudent = null;
                        for (Student student : studentList) {
                            if (student.getUserID().equals(userID)) {
                                thisStudent = student;
                            }
                        }

                        if (password.equals("password")) {
                            // if studentID is correct and
                            // its their first time logging
                            // in, ask them to change
                            // password

                            boolean changed = false;

                            System.out.println(thisStudent.getUserID());

                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.next();

                                // if newPassword is not the same as old password "password",
                                // change the password to newPassword

                                if (newPassword != "password") {
                                    System.out.println("Password Successfully Changed!");
                                    thisStudent.setPassword(newPassword);
                                    Credentials.updatePassword(userID, newPassword);

                                    changed = true; // exit asking them to change password
                                    studentMenuPage(thisStudent, createdCamps);
                                }

                                else { // if they input the same password, ask them to input again
                                    System.out.println("Use a Different Password!");
                                }
                            }
                            // userID and password is correct, not their first time logging in
                        } else if (!password.equals("password")) {
                            System.out.println("Student Login Successful!");
                            // Redirect to student menu method below
                            studentMenuPage(thisStudent, createdCamps);
                            exitstudentlogin = true;
                        } else {
                            // Just for sanity purposes. Wont ever happen
                            System.out.println("Invalid login credentials.");
                            validcredentials = false;
                        }

                    }
                }
            }

            else if (choice == 2) {
                boolean exitstafflogin = false;
                boolean validcredentials = false;

                while (!exitstafflogin) {
                    scanner.useDelimiter(System.lineSeparator());
                    System.out.println("| Staff Login |");
                    System.out.print("UserID: ");
                    String userID = scanner.next();

                    if (!Credentials.usernameExists(userID)) {
                        System.out.print("Invalid Username");
                        break;
                    }

                    System.out.print("Password (default: password): ");
                    String password = scanner.next();

                    // Credentials check
                    if (Credentials.verifyPassword(userID, password)) {
                        validcredentials = true;
                    }

                    while (validcredentials) {
                        Staff thisStaff = null;
                        for (Staff staff : staffList) {
                            if (staff.getUserID().equals(userID)) {
                                thisStaff = staff;
                            }
                        }

                        if (password.equals("password")) {
                            // if staffID is correct and its their first time logging in,
                            // ask them to change password
                            boolean changed = false;
                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.next();

                                // if newPassword is not the same as old password
                                // "password", change the password to newPassword

                                if (newPassword != "password") {
                                    System.out.println("Password Successfully Changed!");
                                    thisStaff.setPassword(newPassword);
                                    Credentials.updatePassword(userID, newPassword);
                                    changed = true; // exit asking them to change password
                                    staffMenuPage(thisStaff, createdCamps);
                                    break;
                                }

                                else { // if they input the same password, ask them to input again
                                    System.out.println("Use a Different Password!");
                                }
                            }
                        }

                        // userID and password is correct, not their first time logging in
                        else if (!password.equals("password")) {
                            System.out.println("Staff Login Successful!");
                            // Redirect to student menu method below
                            exitstafflogin = true;
                            staffMenuPage(thisStaff, createdCamps);
                            break;
                        }

                        else {
                            System.out.println("Invalid login credentials.");
                            validcredentials = false;
                        }

                    }
                }
            }
            else{
                System.out.println("Closing CAM...");
                exitmain = true;
            }
        }
        scanner.close();
    }

    // Student Menu - after log in
    public static void studentMenuPage(Student student, List<CampInfoController> createdCamps) throws IOException {

        Scanner scanner = new Scanner(System.in);

        boolean exitstudentmenu = false;
        EnquiryController enquiry = new EnquiryController();

        while (!exitstudentmenu) {
            System.out.println("Student Menu:");
            System.out.println("(1) Change Password");
            System.out.println("(2) View List of Camps");
            System.out.println("(3) Register for a Camp");
            System.out.println("(4) Submit Enquiry");
            System.out.println("(5) View, Edit or Delete your Enquiry");
            System.out.println("(6) Check Registered Camps");
            System.out.println("(7) Withdraw from a Camp");
            System.out.println("(8) Exit Menu");
            System.out.print("Input Choice: ");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    System.out.println("Enter new Password: ");
                    String newPassword = scanner.next();
                    String oldPW = Credentials.getPassword(student.getUserID());
                    // Add some conditions to check
                    boolean change = false;
                    while (!change) {
                        if (!newPassword.equals(oldPW)) {
                            student.setPassword(newPassword);
                            Credentials.updatePassword(student.getUserID(), newPassword);
                            change = true;
                        } else {
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
                    System.out.println("(1) Attendee");
                    System.out.println("(2) Camp Committee Member");
                    int role_choice = scanner.nextInt();
                    if (role_choice == 1) {
                        student.setStudentType(StudentType.ATTENDEE);
                    } else if (role_choice == 2) {
                        student.setStudentType(StudentType.COMMITTEE);
                    }

                    Registration registration = new Registration();
                    System.out.println("Send Enquiry To:");
                    registration.registerForCamp(student, createdCamps);
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
                            enquiry.addEnquiry(student.getUserID(), "STAFF", enquiryMade);
                            break;

                        case 2:
                            enquiry.addEnquiry(student.getUserID(), "COMMITTEE", enquiryMade);
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
                                List<String> enqList = enquiry.getEnquiriesBySender(student.getUserID());
                                enqList.forEach(System.out::println);
                                quit = true;
                                break;

                            case 2:
                                /*
                                 * System.out.println("Choose the enquiry to edit below");
                                 * List<EnquiryController> enqToEdit = student.getEnquiries();
                                 * System.out.println(enqToEdit);
                                 * int editIndex = scanner.nextInt();
                                 * System.out.println("Enter your new message: ");
                                 * String newMsg = scanner.next();
                                 * 
                                 * for (EnquiryController enq : enqToEdit) {
                                 * int i = 0;
                                 * // Account for entry of i > length of enquiry list
                                 * if (i == editIndex) {
                                 * student.editEnquiry(enq, newMsg);
                                 * }
                                 * 
                                 * i++;
                                 * }
                                 * quit = true;
                                 * break;
                                 */
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
                    break;

                case 7:
                    boolean quit1 = false;
                    System.out.println("Camps you have registered for: ");
                    // System.out.print(student.getEnquiries());

                    System.out.println("Choose the camp you would like to withdraw from: ");
                    int withdrawIndex = scanner.nextInt();

                    System.out.println("Are you sure you want to withdraw from the camp(Y/N)?");
                    while (quit1 != true) {
                        String Decision = scanner.nextLine().toUpperCase();
                        char c = Decision.charAt(0);
                        if (c == 'Y') {
                            // student.withdraw();
                            break;
                        } else if (c == 'N') {
                            break;
                        } else {
                            System.out.println("Error Please enter either Y or N");
                            continue;
                        }
                    }
                    break;
                case 8:
                    Upload.writeToExcel(createdCamps);
                    exitstudentmenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
        scanner.close();
        return;
    }

    // Staff Menu - after log in
    public static void staffMenuPage(Staff staff, List<CampInfoController> createdCamps) throws IOException {

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
            System.out.println("(10) Exit Menu");

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
                                createdCamps = CreateCamp.createNewCamp(staff, createdCamps);
                                break;

                            case 2:
                                boolean campExists = false;
                                System.out.println("Enter Camp Name: ");
                                String campNameMod = scanner.next();
                                
                                while (!campExists) {
                                    // Implement Sanity Check for camp name
                                    for (CampInfoController camp : createdCamps) {
                                        if (camp.getCampName().equals(campNameMod)) {
                                            campExists = true;
                                            break;
                                        }
                                    }
                                
                                    if (campExists == false){
                                        System.out.println("Camp does not exist. Please enter a valid camp name.");
                                        System.out.println("Type 'Exit' to go back to Staff Menu");
                                        System.out.println("Enter Camp Name: ");
                                        campNameMod = scanner.next();
                                        if ("Exit".equals(campNameMod)){
                                            break;
                                        }
                                    }
                                }

                                if (campExists) {
                                    ModifyCamp.modifyCamp(staff, createdCamps, campNameMod);
                                }

                                break;

                                // If camp is in database instead of createdCamps
                                /* List<String> campNames = new ArrayList<>();
                                campNames = Upload.namesInDatabase();

                                System.out.println(campNames);

                                for (String campName : campNames) {
                                    if (campName.equals(campNameMod)) {
                                        int editChoice = Upload.getItemToEdit(campNameMod);
                                        if (editChoice != -1) {
                                            Upload.updateCellValue(campNameMod, editChoice);
                                        }
                                        break;
                                    }
                                } */

                            case 3:
                                System.out.println("Enter Camp Name: ");
                                String campNameDel = scanner.next();
                                createdCamps = Staff.staffDeleteCamp(staff, createdCamps, campNameDel);
                                // Upload.deleteCamp(campNameDel);
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
                    staff.toggleCampVisibility(createdCamps, campNameVisibility);
                    break;

                case 4: // View all camps
                    for (CampInfoController camp : createdCamps) {
                        System.out.println(camp);
                    }
                    break;

                case 5: // View List of Created camps
                    /*
                     * for (String campName : staff.createdCampName) {
                     * System.out.println(campName);
                     * }
                     * break;
                     */

                case 6: // View or Reply enquiries
                    staff.viewAndReplyToEnquiries();
                    break;

                case 7: // Camp suggestions
                    staff.viewAndApproveSuggestions();
                    break;

                case 8: // Generate camp report
                    System.out.println("Enter attendee type (Attendee / Camp Committee): ");
                    String attendeeType = scanner.next();
                    System.out.println("Enter output file format (txt/csv): ");
                    String outputFileFormat = scanner.next();
                    staff.generateReports(createdCamps, attendeeType, outputFileFormat);
                    break;

                case 9: // Generate performance report
                    System.out.println("Enter output file format (txt/csv): ");
                    String performanceOutputFileFormat = scanner.next();
                    staff.generatePerformanceReport(createdCamps, performanceOutputFileFormat);
                    break;

                case 10: // Exit Staff Menu
                    // Upload.deleteAll();
                    Upload.writeToExcel(createdCamps);
                    exitStaffMenu = true;
                    // scanner.close();
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
            // exitStaffMenu = true;
        }
        return;
    }
}
