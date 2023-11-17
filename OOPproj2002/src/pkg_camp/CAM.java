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
    private static List<Staff> staffList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    // private static List<CampCommittee> campCommitteeList = new ArrayList<>();
    private static List<Camp> createdCampsList = new ArrayList<>();

    public static List<Camp> getCreatedCampsList() {
        return createdCampsList;
    }

    public static void setCreatedCampsList(List<Camp> newcreatedCampsList) {
        CAM.createdCampsList = newcreatedCampsList;
    }

    public static void main(String[] args) throws IOException {

        // camps.xlsx
        createdCampsList = Download.loadCamps(createdCampsList);

        // check if credientials file exists
        boolean fileExists = Credentials.checkFileExists();

        // student_list.xlsx
        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        // staff_list.xlsx
        try {

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

                    // need to also add in credentials excel: "user_passwords.xlsx"
                    Credentials newUser = new Credentials(userID, "password");
                    String encodedPW = Credentials.encodePassword("password");
                    newUser.excelWriter(userID, encodedPW);

                    staffList.add(staff);
                    excelFile2.close();
                    workbook2.close();
                }
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
                        Student thisStudent = (Student) Download.createUser(userID, "STUDENT");
                        for (Student student : studentList) {
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
                                    StudentMenu.studentMenuPage(thisStudent);
                                    changed = true; // exit asking them to change password
                                    validcredentials = false; // make them reenter password
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
                        Staff thisStaff = (Staff) Download.createUser(userID, "STAFF");
                        for (Staff staff : staffList) {
                            if (staff.getUserID().equals(userID)) {
                                thisStaff = staff;
                            }
                        }

                        if (password.equals("password") && thisStaff != null) {
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
                                    StaffMenu.staffMenuPage(thisStaff);
                                    changed = true; // exit asking them to change password
                                    validcredentials = false; // make them reenter password
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
