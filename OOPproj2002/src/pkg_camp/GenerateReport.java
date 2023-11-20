package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.String;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GenerateReport {
    //
    public static void campCommitteeGR(CampCommitteeMember committeeMember, Camp camp) {

        System.out.println("Would you like to view the committee members for this camp?");
        System.out.println("[0] Yes");
        System.out.println("[1] No");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        try (PrintWriter writer = new PrintWriter(new FileWriter("camp_info.txt"))) {
            writer.println("------------------------------");
            writer.println("Camp Name: " + camp.getCampName());
            writer.println("Dates: " + camp.getDates());
            writer.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
            writer.println("Location: " + camp.getLocation());
            writer.println("Total Slots: " + camp.getTotalSlots());
            writer.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
            writer.println("Description: " + camp.getDescription());
            writer.println("Staff in Charge: " + camp.getStaffInCharge());
            writer.println("Visibility: " + camp.getVisibility());
            writer.println("------------------------------");
            writer.println("Attendees:");
            for (Student student : camp.getAttendees()) {
                writer.println(student.getUserID());
            }
            if (choice == 0) {
                writer.println("------------------------------");
                writer.println("Camp Committee Members:");
                for (Student student : camp.getCampCommittee()) {
                    writer.println(student.getUserID());
                }
            }

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void staffGR(Staff staff, List<Camp> allCamps) {

        System.out.println("Would you like to view the committee members for your camps?");
        System.out.println("[0] Yes");
        System.out.println("[1] No");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        for (Camp camp : allCamps) {
            // Check if the camp was created by this staff member
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                try (PrintWriter writer = new PrintWriter(new FileWriter("camp_info.txt"))) {

                    writer.println("------------------------------");
                    writer.println("Camp Name: " + camp.getCampName());
                    writer.println("Dates: " + camp.getDates());
                    writer.println("Registration Closing Date: " + camp.getRegistrationClosingDate());
                    writer.println("Location: " + camp.getLocation());
                    writer.println("Total Slots: " + camp.getTotalSlots());
                    writer.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
                    writer.println("Description: " + camp.getDescription());
                    writer.println("Staff in Charge: " + camp.getStaffInCharge());
                    writer.println("Visibility: " + camp.getVisibility());
                    writer.println("------------------------------");
                    writer.println("Attendees:");
                    for (Student student : camp.getAttendees()) {
                        writer.println(student.getUserID());
                    }
                    if (choice == 0) {
                        writer.println("------------------------------");
                        writer.println("Camp Committee Members:");
                        for (Student student : camp.getCampCommittee()) {
                            writer.println(student.getUserID());
                        }
                    }

                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            }
        }
    }

    public static void getPerformanceReport(Staff staff, List<Camp> allCamps) {
        StringBuilder reportContent = new StringBuilder();

        for (CampController camp : camps) {
            // Check if the camp was created by this staff member and has a camp committee
            if (camp.getStaffInCharge().equals(this) && !camp.getCampCommittee().isEmpty()) {
                reportContent.append("Camp Name: ").append(camp.getCampName()).append("\n");
                reportContent.append("Camp Date: ").append(camp.getDates()).append("\n");
                reportContent.append("Location: ").append(camp.getLocation()).append("\n");

                // Add camp committee members to the report
                List<Student> campCommitteeMembers = camp.getCampCommittee();
                for (Student committeeMember : campCommitteeMembers) {

                    // same getter method
                    reportContent.append("Committee Member Name: ").append(committeeMember.getUserID()).append("\n");
                    // Add more committee member details as needed

                    // Add a separator between committee members
                    reportContent.append("------------------------\n");
                }

                // Add a separator between camps
                reportContent.append("========================================\n");
            }
        }

        // Output the performance report to a file in the specified format (txt or csv)
        String outputFileName = "camp_committee_performance_report." + outputFileFormat;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(reportContent.toString());
            System.out.println("Performance report generated: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to write the performance report.");
        }
    }

}
