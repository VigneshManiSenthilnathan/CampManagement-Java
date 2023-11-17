package pkg_camp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// CampCommitteeMember Menu - After log in

public class CampCommitteeMenu {
    public static void campCommitteeMenuPage(CampCommitteeMember campCommitteeMember) throws IOException {
        List<Camp> createdCampsList = CAM.getCreatedCampsList();
        Scanner scanner = new Scanner(System.in);
        boolean exitCampCommitteeMenu = false;

        while (!exitCampCommitteeMenu) {
            System.out.println("Camp Committee Menu:");
            System.out.println("(1) View The Details of Your Camp");
            System.out.println("(2) Submit Suggestions");
            System.out.println("(3) View/Reply To Enquiries");
            System.out.println("(4) View, Edit, or Delete Your Suggestions");
            System.out.println("(5) Generate Camp Report"); // idk why they say can generate list of students for camp
                                                            // they
                                                            // created, since when they can create camp
            System.out.println("(6) Exit Camp Committee Menu");

            int menu = scanner.nextInt();
            scanner.useDelimiter(System.lineSeparator());

            switch (menu) {
                case 1:
                    ViewCamp.campCommitteeMenuViewCamp(campCommitteeMember, createdCampsList);
                    break;

                case 2:
                    break;

                case 3:
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to view");
                        System.out.println("Press [2] to reply");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // view enquiries method
                                break;

                            case 2:
                                // reply enquiries method
                                break;
                        }
                    }

                case 4:
                    quit = false;
                    while (!quit) {
                        System.out.println("Choose your option below");
                        System.out.println("Press [1] to view");
                        System.out.println("Press [2] to edit");
                        System.out.println("Press [3] to delete");

                        int option = scanner.nextInt();

                        switch (option) {
                            case 1:
                                // view suggestions method
                                break;

                            case 2:
                                // edit suggestions method
                                break;

                            case 3:
                                // delete suggestions method
                                break;
                        }
                    }

                case 5:
                    // generate some report haiya
                    break;

                case 6:
                    exitCampCommitteeMenu = true;
                    break;
            }
        }
    }
}