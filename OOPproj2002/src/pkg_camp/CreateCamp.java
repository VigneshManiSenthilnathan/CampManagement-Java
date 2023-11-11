package pkg_camp;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class CreateCamp extends Staff {

    public CreateCamp() {

    }

    public static List<CampInfoController> createNewCamp(Staff staff, List<CampInfoController> createdCamps) {
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
        int totalSlots = sc.nextInt();

        System.out.println("Enter Camp Committee Slots Available: ");
        int campCommitteeSlots = sc.nextInt();

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
                System.out.println("Please enter a valid integer  only!");
            }
        }

        sc.close();
        CampInfoController newcamp = new CampInfoController(campName, dates, registrationClosingDate, userGroup,
                location, totalSlots, campCommitteeSlots, description, staff.getUserID(), visibility);

        createdCamps.add(newcamp);
        return createdCamps;
    }

}
