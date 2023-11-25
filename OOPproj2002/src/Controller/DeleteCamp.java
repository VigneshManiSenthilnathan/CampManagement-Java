package pkg_camp;

import java.util.List;
import java.util.Scanner;

public class DeleteCamp {

    public static void deleteCamp(Staff staff) {

        List<Camp> campsList = CampsList.getCreatedCampsList();

        if (campsList.isEmpty()) {
            System.out.println("There are no available camps to delete!");
        }

        int i = 1;
        for (Camp camp : campsList) {
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                System.out.println(i + ". " + camp.getCampName());
                i++;
            }
        }

        if (i == 1) {
            System.out.println("You haven't created any camps to delete.");
        }

        else {
            System.out.println("List of Camps available to delete:");
            i = 1;
            for (Camp camp : campsList) {
                if (camp.getStaffInCharge().equals(staff.getUserID())) {
                    System.out.println(i + ". " + camp.getCampName());
                    i++;
                }
            }

            boolean donedelete = false;
            Scanner scanner = new Scanner(System.in);

            while (!donedelete) {
                System.out.println("Camp to Delete (Enter EXIT to stop): ");
                System.out.print("Camp Name: ");

                String choice = scanner.nextLine();

                if (choice.toUpperCase().equals("EXIT")) {
                    donedelete = true;
                }

                for (Camp camp : campsList) {
                    if (choice.equalsIgnoreCase(camp.getCampName())) {
                        Upload.deleteCamp(choice);
                        campsList.remove(camp);
                        System.out.println("Camp deleted successfully: " + camp.getCampName());
                        donedelete = true;
                        break;
                    }
                }
            }
        }

        CampsList.setCreatedCampsList(campsList);
    }
}