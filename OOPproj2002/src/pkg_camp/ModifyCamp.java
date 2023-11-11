package pkg_camp;

import java.util.List;
import java.util.Scanner;

public class ModifyCamp extends Staff {

    public static List<CampInfoController> modifyCamp(Staff staff, List<CampInfoController> createdCamps,
            String campName) {

        CampInfoController thisCamp = null;
        boolean campExists = false;

        // Implement Sanity Check for camp name
        for (CampInfoController camp : createdCamps) {
            if (camp.getCampName().equals(campName)) {
                thisCamp = camp;
                campExists = true;
                break;
            }
        }

        if (!campExists) {
            System.out.println("Camp does not exist. Please enter a valid camp name.");
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
                    CampInformation.editCampDetails(thisCamp, 0, newcampname);
                    break;
                case 1:
                    System.out.println("Enter New Camp Dates: ");
                    String newcampdate = sc.next();
                    CampInformation.editCampDetails(thisCamp, 1, newcampdate);
                    break;
                case 2:
                    System.out.println("Enter New Camp Registration Closing Date: ");
                    String newcampregdate = sc.next();
                    CampInformation.editCampDetails(thisCamp, 2, newcampregdate);
                    break;
                case 3:
                    System.out.println("Enter New Camp UserGroup: ");
                    String newcampusergrp = sc.next();
                    CampInformation.editCampDetails(thisCamp, 3, newcampusergrp);
                    break;
                case 4:
                    System.out.println("Enter New Camp Location: ");
                    String newcamplocation = sc.next();
                    CampInformation.editCampDetails(thisCamp, 4, newcamplocation);
                    break;
                case 5:
                    System.out.println("Enter New Total Slots Available: ");
                    String newcamptotalslots = sc.next();
                    CampInformation.editCampDetails(thisCamp, 5, newcamptotalslots);
                    break;
                case 6:
                    System.out.println("Enter New Total Camp Committee Slots Available: ");
                    String newcampcommitteeslots = sc.next();
                    CampInformation.editCampDetails(thisCamp, 6, newcampcommitteeslots);
                    break;
                case 7:
                    System.out.println("Enter New Camp Description: ");
                    String newcampdescription = sc.next();
                    CampInformation.editCampDetails(thisCamp, 7, newcampdescription);
                    break;
                case 8:
                    System.out.println("Enter New Camp Visibility (Visible = 1, Invisible = 0): ");
                    int newcampvisibility = sc.nextInt();
                    CampInformation.toggleVisibility(thisCamp, newcampvisibility);
                    break;
                case 9:
                    System.out.println("Enter New StaffInCharge: ");
                    String newstaffincharge = sc.next();
                    CampInformation.editCampDetails(thisCamp, 8, newstaffincharge);
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
}
