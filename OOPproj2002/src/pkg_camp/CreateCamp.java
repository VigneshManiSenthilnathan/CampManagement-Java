package pkg_camp;

import java.util.Scanner;
import java.time.LocalDate;

public class CreateCamp extends Staff {

    public CreateCamp() {

    }

    public static void createNewCamp(Staff staff) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Enter Camp Name: ");
        String campName = sc.next();

        System.out.println("Enter Camp Dates: ");
        String datesStr = sc.next();
        LocalDate dates = LocalDate.parse(datesStr);

        System.out.println("Enter Camp Registration Closing Date: ");
        String registrationClosingDateStr = sc.next();
        LocalDate registrationClosingDate = LocalDate.parse(registrationClosingDateStr);

        System.out.println("Enter Faculty: ");
        String userGroup = sc.next();

        System.out.println("Enter Location: ");
        String location = sc.next();

        System.out.println("Enter Total Slots Available: ");
        String totalSlots = sc.next();

        System.out.println("Enter Camp Committee Slots Available: ");
        String campCommitteeSlots = sc.next();

        System.out.println("Enter Camp Description: ");
        String description = sc.next();

        boolean exitVisibility = false;
        while (!exitVisibility) {
            System.out.println("Enter Camp Visibility:");
            System.out.println("[1]: Visible");
            System.out.println("[2]: Hidden");
            int visibilityInt = sc.nextInt();

            if (visibilityInt == 1) {
                boolean visilibility = true;
                exitVisibility = true;
            }

            else if (visibilityInt == 2) {
                boolean visibility = false;
                exitVisibility = true;
            }

            else {
                System.out.println("Please enter a valid integer!");
            }
        }

        CampInfoController newcamp = new CampInfoController(campName, dates, registrationClosingDate, userGroup,
                location, totalSlots, campCommitteeSlots, description, staff.getUserID(), visibility);
    }

}
