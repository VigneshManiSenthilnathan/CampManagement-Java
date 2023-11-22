package pkg_camp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ModifyCamp {

    public static void modifyCamp(Staff staff) {

        System.out.println("Enter Camp Name: ");
        Scanner sc = new Scanner(System.in);
        String campName = sc.next();

        // Implement Sanity Check for camp name
        Camp thisCamp = null;
        boolean campExists = false;
        while (!campExists) {
            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getCampName().equals(campName)) {
                    thisCamp = camp;
                    campExists = true;
                }
            }

            if (campExists == false) {
                System.out.println("Camp does not exist. Please enter a valid camp name.");
                System.out.println("Type 'Exit' to go back to Staff Menu");
                System.out.println("Enter Camp Name: ");
                campName = sc.next();
                if ("Exit".equals(campName)) {
                    return;
                }
            }
        }

        boolean done = false;
        while (!done) {
            System.out.println("(1) Change Camp Name");
            System.out.println("(2) Change Camp Dates");
            System.out.println("(3) Change Camp Registration Closing Date");
            System.out.println("(4) Change Viewing User Group");
            System.out.println("(5) Change Location");
            System.out.println("(6) Change Total Slots Available");
            System.out.println("(7) Change Camp Committee Slots Available");
            System.out.println("(8) Change Camp Description");
            System.out.println("(9) Change Camp Visibility");
            System.out.println("(10) Change StaffInCharge");
            System.out.println("(11) Exit to Staff Menu");

            sc.useDelimiter(System.lineSeparator());
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter New Camp Name: ");
                    String newCampName = sc.next();
                    Upload.updateCampNameInExcel(thisCamp.getCampName(), newCampName);
                    editCampDetails(thisCamp, 0, newCampName);
                    break;

                case 2:
                    System.out.println("Enter New Camp Dates: ");
                    String newCampDate = sc.next();
                    editCampDetails(thisCamp, 1, newCampDate);
                    break;

                case 3:
                    System.out.println("Enter New Camp Registration Closing Date: ");
                    String newCampRegDate = sc.next();
                    editCampDetails(thisCamp, 2, newCampRegDate);
                    break;

                case 4:
                    System.out.println("Enter New Camp UserGroup: ");
                    String newCampUserGrp = sc.next();
                    editCampDetails(thisCamp, 3, newCampUserGrp);
                    break;

                case 5:
                    System.out.println("Enter New Camp Location: ");
                    String newCampLocation = sc.next();
                    editCampDetails(thisCamp, 4, newCampLocation);
                    break;

                case 6:
                    System.out.println("Enter New Total Slots Available: ");
                    String newCampTotalSlots = sc.next();
                    editCampDetails(thisCamp, 5, newCampTotalSlots);
                    break;

                case 7:
                    System.out.println("Enter New Total Camp Committee Slots Available: ");
                    String newCampCommitteeSlots = sc.next();
                    editCampDetails(thisCamp, 6, newCampCommitteeSlots);
                    break;

                case 8:
                    System.out.println("Enter New Camp Description: ");
                    String newCampDescription = sc.next();
                    editCampDetails(thisCamp, 7, newCampDescription);
                    break;

                case 9:
                    System.out.println("Enter New Camp Visibility (Visible = 1, Invisible = 0): ");
                    int newCampVisibility = sc.nextInt();
                    String newCampVisibilityStr = "true";
                    if (newCampVisibility == 1) {
                        newCampVisibilityStr = "true";
                    } else if (newCampVisibility == 0) {
                        newCampVisibilityStr = "false";
                    } else {
                        System.out.println("Invalid input. Please enter 1 or 0.");
                    }
                    editCampDetails(thisCamp, 9, newCampVisibilityStr);
                    break;

                case 10:
                    System.out.println("Enter New StaffInCharge: ");
                    String newStaffIncharge = sc.next();
                    editCampDetails(thisCamp, 8, newStaffIncharge);
                    break;

                case 11:
                    System.out.println("Exiting back to staff menu...");
                    done = true;
                    return;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
        return;
    }

    public static void editCampDetails(Camp camp, int attributeToEdit, String edit) {
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
                    camp.setUserGroup(edit);
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
                if (edit == "true") {
                    camp.setVisibility(true);
                } else if (edit == "false") {
                    camp.setVisibility(false);
                } else {
                    System.out.println("Invalid input. Please enter true or false.");
                }
                break;

            case 10:
                System.out.println("Quitting Program...");
                break;

            default:
                System.out.println("Attribute does not exist.");
        }
    }
}
