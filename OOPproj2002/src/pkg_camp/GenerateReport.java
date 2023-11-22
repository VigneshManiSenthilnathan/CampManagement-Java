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
            for (Student student : camp.getAttendeesList()) {
                writer.println(student.getUserID());
            }
            if (choice == 0) {
                writer.println("------------------------------");
                writer.println("Camp Committee Members:");
                for (Student student : camp.getCampCommitteeList()) {
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
                    for (Student student : camp.getAttendeesList()) {
                        writer.println(student.getUserID());
                    }
                    if (choice == 0) {
                        writer.println("------------------------------");
                        writer.println("Camp Committee Members:");
                        for (Student student : camp.getCampCommitteeList()) {
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

        try (PrintWriter writer = new PrintWriter(new FileWriter("performance_report.txt"))) {

            for (Camp camp : allCamps) {
                // Check if the camp was created by this staff member
                if (camp.getStaffInCharge().equalsIgnoreCase(staff.getUserID())) {
                    List<CampCommitteeMember> committee = camp.getCampCommitteeList();
                    List<Enquiry> enquiries = camp.getEnquiryList();
                    List<Suggestion> suggestions = camp.getSuggestionList();

                    for (CampCommitteeMember commMember : committee) {

                        int enqpoints = 0;

                        writer.println("------------------------------");
                        writer.println("Committee Member: " + commMember.getUserID());

                        for (Enquiry enquiry : enquiries) {
                            if (enquiry.getRepliedBy().equalsIgnoreCase(commMember.getUserID())) {
                                enqpoints++;
                            }
                        }

                        int sugpoints = 0;
                        int sugpointsA = 0;

                        for (Suggestion suggestion : suggestions) {
                            if (suggestion.getStudentID().equalsIgnoreCase(commMember.getUserID())) {

                                if (suggestion.getApproved()) {
                                    sugpointsA++;
                                }
                                sugpoints++;
                            }
                        }

                        writer.println("Enquiries Replied: " + enqpoints);
                        writer.println("Suggestions Given: " + sugpoints);
                        writer.println("Suggestions Approved: " + sugpointsA);
                        writer.println("Total Points:" + enqpoints + sugpoints + sugpointsA);
                        writer.println("------------------------------");
                    }

                }
            }

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
