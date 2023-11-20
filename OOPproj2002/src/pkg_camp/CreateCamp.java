package pkg_camp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateCamp {

    public static void createNewCamp(Staff staff) {
        LocalDate dates = null;
        LocalDate registrationClosingDate = null;

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Enter Camp Name: ");
        String campName = sc.next();

        System.out.println("Enter Camp Dates: ");
        String datesStr = sc.next();
        try {
            dates = LocalDate.parse(datesStr, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Use 'dd/MM/yy'.");
        }

        System.out.println("Enter Camp Registration Closing Date: ");
        String registrationClosingDateStr = sc.next();
        try {
            registrationClosingDate = LocalDate.parse(registrationClosingDateStr,
                    DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Use 'dd/MM/yy'.");
        }

        System.out.println("Enter Faculty: ");
        String userGroup = sc.next();

        System.out.println("Enter Location: ");
        String location = sc.next();

        System.out.println("Enter Total Slots Available: ");
        int totalSlots = sc.nextInt();

        boolean ccslots = false;
        int campCommitteeSlots = 0;
        while (!ccslots) {
            System.out.println("Enter Camp Committee Slots Available: ");
            campCommitteeSlots = sc.nextInt();
            if (campCommitteeSlots > 10) {
                System.out.println("Max Camp Committee Slots is 10!");
            } else {
                ccslots = true;
            }
        }

        System.out.println("Enter Camp Description: ");
        String description = sc.next();

        boolean exitVisibility = false;
        boolean visibility = false;

        while (!exitVisibility) {
            System.out.println("Enter Camp Visibility:");
            System.out.println("[1]: Visible");
            System.out.println("[2]: Hidden");
            int visibilityInt = sc.nextInt();

            if (visibilityInt == 1) {
                visibility = true;
                exitVisibility = true;
            }

            else if (visibilityInt == 2) {
                exitVisibility = true;
            }

            else {
                System.out.println("Please enter a valid integer only!");
            }
        }

        CampInformation campinfo = new CampInformation(campName, dates, registrationClosingDate,
                userGroup, location, totalSlots, campCommitteeSlots, description,
                staff.getUserID(), visibility);

        Camp newcamp = new Camp(campinfo);
        CampsList.appendToCampsList(newcamp);
    }
}
