package pkg_camp;

import org.apache.batik.svggen.font.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CAM {

    public static void main(String[] args) throws IOException {

        List<Student> studentList;
        List<Staff> staffList;

        // Move to after credentials load (around line 127)
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        createdCampsList = Download.loadCamps(createdCampsList);
        CampsList.setCreatedCampsList(createdCampsList);

        // check if credientials file exists
        boolean fileExists = Credentials.checkFileExists();

        // student_list.xlsx
        try {
            studentList = StudentsList.getStudentList();

            if (!fileExists) {

                // reads data from file path: student_list2
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

        int width = 100;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("CAMS", 10, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
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
                    System.out.print("(Type EXIT to restart the program)\n UserID: ");
                    String userID = scanner.next();
                    userID = userID.toUpperCase();
                    if (userID.equalsIgnoreCase("EXIT")) {
                        System.out.println("Restarting CAMs...");
                        exitstudentlogin = true;
                        break;
                    }

                    while (errorCount > 0) {
                        if (!Credentials.usernameExistsStudent(userID.toUpperCase())) {
                            System.out.println("Invalid Username, try again. You have " + errorCount + " tries left.");
                            System.out.println("UserID: ");
                            userID = scanner.next().toUpperCase();// Loop check if invalid username
                            if (errorCount == 0) {
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
                            if (errorCount == 0) {
                                break;
                            }
                            errorCount--;
                        } // Loop check if invalid password
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    } else {
                        validcredentials = true;
                    }

                    Student thisStudent = (Student) Download.createUser(userID, "STUDENT");

                    while (validcredentials) {

                        for (Student student : StudentsList.getStudentList()) {
                            if (student.getUserID().equals(userID)) {
                                thisStudent = student;
                            }

                        }

                        if (password.equals("password") && thisStudent != null) {
                            System.out.println("First time logging in, please change your password.");
                            ManageCredentials.changePassword(thisStudent);
                            System.out.println("Password changed successfully, please re-login.");
                            validcredentials = false;
                        }

                        // userID and password is correct, not their first time logging in
                        else if (!password.equals("password")) {
                            System.out.println("Student Login Successful!");

                            // Redirect to the correct student menu method below
                            for (Camp camp : CampsList.getCreatedCampsList()) {
                                for (CampCommitteeMember campCommitteeMember : camp.getCampCommitteeList()) {
                                    if (thisStudent.getUserID().equalsIgnoreCase(campCommitteeMember.getUserID())) {
                                        CampCommitteeMember committeeMember = (CampCommitteeMember) thisStudent;
                                        CampCommitteeMenu.campCommitteeMenuPage(committeeMember, camp);
                                        break;
                                    }
                                }
                            }

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
                    System.out.print("(Type EXIT to restart the program) \n UserID: ");
                    String userID = scanner.next();

                    if (userID.equalsIgnoreCase("EXIT")) {
                        System.out.println("Restarting CAMs...");
                        exitstafflogin = true;
                        break;
                    }

                    while (errorCount > 0) {
                        if (!Credentials.usernameExistsStaff(userID.toUpperCase())) {
                            System.out.println("Invalid Username, try again. You have " + errorCount + " tries left.");
                            System.out.println("UserID: ");
                            userID = scanner.next().toUpperCase(); // Loop check if invalid username
                            if (errorCount == 0) {
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
                        } else {
                            System.out.println("Try again, re-type password. You have " + errorCount + " tries left.");
                            System.out.println("Password: ");
                            password = scanner.next();
                            if (errorCount == 0) {
                                break;
                            }
                            errorCount--;
                        } // Loop check if invalid password
                    }

                    if (errorCount == -1) {
                        System.out.println("Too many failed attempts, closing CAMS system. Please try again later.");
                        return;
                    } else {
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
                            System.out.println("As this is your first time logging in, please change your password.");
                            ManageCredentials.changePassword(thisStaff);
                            System.out.println("Password changed successfully, please re-login.");
                            validcredentials = false;
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
