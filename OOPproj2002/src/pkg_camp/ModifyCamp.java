package pkg_camp;

import java.util.Scanner;

public class ModifyCamp {

    public static void modifyCamp(String campName) {

        // Implement Sanity Check for camp name

        System.out.println("Chose which attribute you would like to modify:");
        System.out.println("(0) Change Camp Name");
        System.out.println("(1) Change Camp Dates");
        System.out.println("(2) Change Camp Registration Closing Date");
        System.out.println("(3) Change Faculty");
        System.out.println("(4) Change Location");
        System.out.println("(5) Change Total Slots Available");
        System.out.println("(6) Change Camp Committee Slots Available");
        System.out.println("(7) Change Camp Description");

        System.out.println("(9) Change Camp Visibility");

        Scanner scan = new Scanner(System.in);

        int choice = scan.nextInt();

        if (choice > -1 || choice < 9) {
            Upload.updateCellValue(campName, );
        }

        scan.close();
    }
}
