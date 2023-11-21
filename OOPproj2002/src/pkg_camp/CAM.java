package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import java.util.Scanner;

public class CAM {

    public static void printAttendeesForAllCamps(List<Camp> createdCampsList) {
        for (Camp camp : createdCampsList) {
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Attendees:");

            List<Student> attendees = camp.getAttendees();
            for (Student attendee : attendees) {
                System.out.println(" - " + attendee.getUserID());
            }

            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) throws IOException {

        List<Student> studentList;
        List<Staff> staffList;

        // Move to after credentials load (around line 127)
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        createdCampsList = Download.loadCamps(createdCampsList);
        CampsList.setCreatedCampsList(createdCampsList);

        printAttendeesForAllCamps(CampsList.getCreatedCampsList());

        // check if credientials file exists
        boolean fileExists = Credentials.checkFileExists();

        // student_list.xlsx
        try {

            studentList = StudentsList.getStudentList();

            if (!fileExists) {

                // reads data from file path: student_list
                File file = new File("OOPproj2002/src/pkg_camp/student_list.xlsx");

                FileInputStream excelFile1 = new FileInputStream(file);

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
            }

            StudentsList.setStudentsList(studentList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // staff_list.xlsx
        try {
            staffList = StaffsList.getStaffList();

            if (!fileExists) {
                // reads data from file path: staff_list

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
                    StaffsList.appendToStaffList(staff);

                    // need to also add in credentials excel: "user_passwords.xlsx"
                    Credentials newUser = new Credentials(userID, "password");
                    String encodedPW = Credentials.encodePassword("password");
                    newUser.excelWriter(userID, encodedPW);

                    staffList.add(staff);
                    excelFile2.close();
                    workbook2.close();
                }
            }

            StaffsList.setStaffsList(staffList);

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
                int errorCount = 3;

                while (!exitstudentlogin) {
                    scanner.useDelimiter(System.lineSeparator());
                    System.out.println("| Student Login |");
                    System.out.print("UserID: ");
                    String userID = scanner.next();
                    userID = userID.toUpperCase();

                    while (errorCount > 0) {
                        if (!Credentials.usernameExists(userID.toUpperCase())) {
                            System.out.println(
                                    "Invalid Username, try again. You have " + errorCount + " tries left.");
                            System.out.println("UserID: ");
                            userID = scanner.next().toUpperCase();// Loop check if invalid username   
                            if(errorCount == 0){
                                break;
                            }
                            errorCount--;
                        } else {
                            break;
                        }
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    }

                    System.out.print("Password (default: password): ");
                    errorCount = 3;
                    String password = scanner.next();

                    // Credentials check
                    while (errorCount > 0) {
                        if (Credentials.verifyPassword(userID, password)) {
                            validcredentials = true;
                            break;
                        } else {
                            System.out.println(
                                    "Try again, re-type password. You have " + errorCount + " tries left.");
                            System.out.println("Password: ");
                            password = scanner.next();
                            if(errorCount == 0){
                                break;
                            }
                            errorCount--;
                        } // Loop check if invalid password
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    }
                    else{
                        validcredentials = true;
                    }
                    
                    
                    while (validcredentials) {
                        Student thisStudent = (Student) Download.createUser(userID, "STUDENT");
                        for (Student student : StudentsList.getStudentList()) {
                            if (student.getUserID().equals(userID)) {
                                thisStudent = student;
                            }
                        }

                        if (password.equals("password") && thisStudent != null) {
                            // if studentID is correct and its their first time logging in,
                            // ask them to change password
                            boolean changed = false;
                            while (!changed) {
                                System.out.println("Change your password: ");
                                String newPassword = scanner.next();

                                // if newPassword is not the same as old password
                                // "password", change the password to newPassword

                                if (newPassword != "password") {
                                    System.out.println("Password Successfully Changed!");
                                    thisStudent.setPassword(newPassword);
                                    Credentials.updatePassword(userID, newPassword);
                                    changed = true; // exit asking them to change password
                                    validcredentials = false; // make them reenter password
                                    StudentMenu.studentMenuPage(thisStudent);
                                    exitstudentlogin = true;
                                    break;
                                }

                                else { // if they input the same password, ask them to input again
                                    System.out.println("Use a Different Password!");
                                }
                            }
                        }

                        // userID and password is correct, not their first time logging in
                        else if (!password.equals("password")) {
                            System.out.println("Student Login Successful!");

                            // Redirect to student menu method below
                            StudentMenu.studentMenuPage(thisStudent);
                            exitstudentlogin = true;

                            break;
                        }

                        else {
                            System.out.println("Invalid login credentials.");
                            validcredentials = false;
                        }
                    }
                }
            }

            else if (choice == 2) {
                boolean exitstafflogin = false;
                boolean validcredentials = false;
                int errorCount = 3;

                while (!exitstafflogin) {
                    scanner.useDelimiter(System.lineSeparator());
                    System.out.println("| Staff Login |");
                    System.out.print("UserID: ");
                    String userID = scanner.next();

                    while (errorCount > 0) {
                        if (!Credentials.usernameExists(userID.toUpperCase())) {
                            System.out.println("Invalid Username, try again. You have " + errorCount + " tries left.");
                            System.out.println("UserID: ");
                            userID = scanner.next().toUpperCase(); // Loop check if invalid username
                            if(errorCount == 0){
                                break;
                            }
                            errorCount--;
                        } else {
                            break;
                        }
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    }

                    System.out.print("Password (default: password): ");
                    String password = scanner.next();
                    errorCount = 3;

                    // Credentials check
                    while (errorCount > 0) {
                        if (Credentials.verifyPassword(userID, password)) {
                            validcredentials = true;
                            break;
                        } 
                        else {
                            System.out.println("Try again, re-type password. You have " + errorCount + " tries left.");
                            System.out.println("Password: ");
                            password = scanner.next();
                            if(errorCount == 0){
                                break;
                            }
                            errorCount--;
                        } // Loop check if invalid password
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    }
                    else{
                        validcredentials = true;
                    }

                    /*
                     * if (!Credentials.usernameExists(userID)) {
                     * System.out.print("Invalid Username");
                     * break;
                     * }
                     * 
                     * System.out.print("Password (default: password): ");
                     * String password = scanner.next();
                     * 
                     * // Credentials check
                     * if (Credentials.verifyPassword(userID, password)) {
                     * validcredentials = true;
                     * }
                     */

                    while (validcredentials) {
                        Staff thisStaff = (Staff) Download.createUser(userID, "STAFF");
                        for (Staff staff : StaffsList.getStaffList()) {
                            if (staff.getUserID().equals(userID)) {
                                thisStaff = staff;
                            }
                        }

                        if (password.equals("password") && thisStaff != null) {
                            // if staffID is correct and its their first time logging in,
                            // ask them to change password
                            boolean changed = false;
                            while (!changed) {
                                System.out.println("Change your password. Password cannot be the same as before: ");
                                String newPassword = scanner.next();
                                System.out.println("Please type your new password again: ");
                                String newPassword2 = scanner.next();

                                // if newPassword is not the same as old password
                                // "password", change the password to newPassword

                                if (newPassword != "password") {
                                    System.out.println("Password Successfully Changed!");
                                    thisStaff.setPassword(newPassword);
                                    Credentials.updatePassword(userID, newPassword);
                                    StaffMenu.staffMenuPage(thisStaff);
                                    changed = true; // exit asking them to change password
                                    validcredentials = false; // make them reenter password
                                    break;
                                }

                                else if (newPassword == "password") { // if they input the same password, ask them to
                                                                      // input again
                                    System.out.println("Use a Different Password!");
                                }

                                else if (newPassword != newPassword2) {
                                    System.out.println("You have re-entered the wrong password. Please try again");
                                }

                                else {
                                    System.out.println("Please try again.");
                                }
                            }
                        }

                        // userID and password is correct, not their first time logging in
                        else if (!password.equals("password")) {
                            System.out.println("Staff Login Successful!");

                            // Redirect to staff menu method below
                            StaffMenu.staffMenuPage(thisStaff);
                            exitstafflogin = true;
                            break;
                        }

                        else {
                            System.out.println("Invalid login credentials.");
                            validcredentials = false;
                        }
                    }
                }
            }

            else {
                System.out.println("Closing CAM...");
                exitmain = true;
            }
        }
        scanner.close();
    }
}
