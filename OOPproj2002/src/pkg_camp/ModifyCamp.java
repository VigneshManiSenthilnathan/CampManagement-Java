package pkg_camp;

import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ModifyCamp extends Staff {

    public static List<CampInfoController> modifyCamp(Staff staff, List<CampInfoController> createdCamps,
            String campName) {

        CampInfoController thisCamp = null;
        boolean campExists = false;

        for (CampInfoController camp : createdCamps) {
            if (camp.getCampName().equals(campName)) {
                thisCamp = camp;
                campExists = true;
            }
        }

        if (campExists = false) {
            return createdCamps;
        }

        boolean done = false;
        while (!done) {
            System.out.println("(0) Change Camp Name");
            System.out.println("(1) Change Camp Dates");
            System.out.println("(2) Change Camp Registration Closing Date");
            System.out.println("(3) Change Viewing User Group");
            System.out.println("(4) Change Location");
            System.out.println("(5) Change Total Slots Available");
            System.out.println("(6) Change Camp Committee Slots Available");
            System.out.println("(7) Change Camp Description");
            System.out.println("(8) Change Camp Visibility");
            System.out.println("(9) Change StaffInCharge");
            System.out.println("(10) Exit to Staff Menu");

            Scanner sc = new Scanner(System.in);
            sc.useDelimiter(System.lineSeparator());
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Enter New Camp Name: ");
                    String newcampname = sc.next();
                    Upload.updateCampNameInExcel(thisCamp.getCampName(), newcampname);
                    editCampDetails(thisCamp, 0, newcampname);
                    break;

                case 1:
                    System.out.println("Enter New Camp Dates: ");
                    String newcampdate = sc.next();
                    editCampDetails(thisCamp, 1, newcampdate);
                    break;
                case 2:
                    System.out.println("Enter New Camp Registration Closing Date: ");
                    String newcampregdate = sc.next();
                    editCampDetails(thisCamp, 2, newcampregdate);
                    break;
                case 3:
                    System.out.println("Enter New Camp UserGroup: ");
                    String newcampusergrp = sc.next();
                    editCampDetails(thisCamp, 3, newcampusergrp);
                    break;
                case 4:
                    System.out.println("Enter New Camp Location: ");
                    String newcamplocation = sc.next();
                    editCampDetails(thisCamp, 4, newcamplocation);
                    break;
                case 5:
                    System.out.println("Enter New Total Slots Available: ");
                    String newcamptotalslots = sc.next();
                    editCampDetails(thisCamp, 5, newcamptotalslots);
                    break;
                case 6:
                    System.out.println("Enter New Total Camp Committee Slots Available: ");
                    String newcampcommitteeslots = sc.next();
                    editCampDetails(thisCamp, 6, newcampcommitteeslots);
                    break;
                case 7:
                    System.out.println("Enter New Camp Description: ");
                    String newcampdescription = sc.next();
                    editCampDetails(thisCamp, 7, newcampdescription);
                    break;
                case 8:
                    System.out.println("Enter New Camp Visibility (Visible = 1, Invisible = 0): ");
                    int newcampvisibility = sc.nextInt();
                    CampInformation.toggleVisibility(thisCamp, newcampvisibility);
                    break;
                case 9:
                    System.out.println("Enter New StaffInCharge: ");
                    String newstaffincharge = sc.next();
                    editCampDetails(thisCamp, 8, newstaffincharge);
                    break;
                case 10:
                    System.out.println("Exiting back to staff menu...");
                    done = true;
                    return createdCamps;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return createdCamps;
    }

    public static void editCampDetails(CampInformation camp, int attributeToEdit, String edit) {
        switch (attributeToEdit) {
            case 0:
                camp.setCampName(edit);
                break;

            case 1:
                try {
                    LocalDate campDate = LocalDate.parse(edit, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    camp.setDates(campDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid date format. Use 'dd/MM/yy'.");
                }
                break;

            case 2:
                try {
                    LocalDate regClosingDate = LocalDate.parse(edit, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    camp.setRegistrationClosingDate(regClosingDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid date format. Use 'dd/MM/yy'.");
                }
                break;

            case 3:
                try {
                    camp.setUserGroup(userGroup);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 4:
                camp.setLocation(edit);
                break;

            case 5:
                try {
                    int slots = Integer.parseInt(edit);
                    camp.setTotalSlots(slots);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 6:
                try {
                    int comslots = Integer.parseInt(edit);
                    if (comslots > 10) {
                        System.out.println("Committee Slots exceeded! Max 10.");
                    } else {
                        camp.setCampCommitteeSlots(comslots);
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: Invalid input.");
                }
                break;

            case 7:
                camp.setDescription(edit);
                break;

            case 8:
                camp.setStaffInCharge(edit);
                break;

            case 9:
                System.out.println("Quitting Program...");
                break;

            default:
                System.out.println("Attribute does not exist.");
        }

        // switch-case to find attribute to be edited
        // typecast String to appropriate type based on attribute chosen
        // handle errors and return to calling function

        // camp.attributeToEdit = edit;
    }
}
