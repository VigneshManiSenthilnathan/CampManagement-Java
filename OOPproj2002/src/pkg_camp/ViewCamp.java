package pkg_camp;

import java.util.List;
import java.util.Scanner;

public class ViewCamp extends CampController {

    public ViewCamp(User user, List<Camp> createdCampsList) {
        if (user instanceof Student) {
            CampController.viewCamps((Student) user, createdCampsList);
        }

        else if (user instanceof Staff) {
            boolean viewcamps = false;
            while (!viewcamps) {
                System.out.println("(1) View All Camps");
                System.out.println("(2) View Created Camps");
                System.out.println("(3) Exit to Staff Menu");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        CampController.viewCamps((Staff) user, createdCampsList);
                        break;

                    case 2:
                        CampController.viewCreatedCamps((Staff) user, createdCampsList);
                        break;

                    case 3:
                        viewcamps = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        } else {
            System.out.println("Invalid user type");
        }
    }
}
