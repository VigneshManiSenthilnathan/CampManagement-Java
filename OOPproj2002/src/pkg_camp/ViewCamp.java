package pkg_camp;

import java.util.List;
import java.util.Scanner;

public class ViewCamp extends CampController {

    public static void studentMenuViewCamps(Student student, List<Camp> createdCampsList) {
        CampController.viewCamps(student, createdCampsList);
    }

    public static void staffMenuViewCamps(Staff staff, List<Camp> createdCampsList) {

        // check if createdCampsList is empty
        if (createdCampsList.isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        // Your staff-specific code here
        boolean viewcamps = false;
        while (!viewcamps) {
            System.out.println("(1) View All Camps");
            System.out.println("(2) View Created Camps");
            System.out.println("(3) Exit to Staff Menu");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CampController.viewCamps(staff, createdCampsList);
                    break;

                case 2:
                    CampController.viewCreatedCamps(staff, createdCampsList);
                    break;

                case 3:
                    viewcamps = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}
