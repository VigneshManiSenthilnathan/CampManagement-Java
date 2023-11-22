package pkg_camp;

import java.util.Scanner;

public class ViewSuggestion {

    public static void viewSuggestion(CampCommitteeMember campCommitteeMember) {
        System.out.println("Your suggestions:");

        // Check for empty list
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No suggestions available");
            return;
        }

        // Get only the suggestions from the camp committee member
        int i = 1;

        for (Camp camp : CampsList.getCreatedCampsList()) {

            if (camp.getSuggestionList().size() > 0) {

                System.out.println("");

                System.out.println("Camp: " + camp.getCampName());
                System.out.println("");

                for (Suggestion suggestion : camp.getSuggestionList()) {
                    if (suggestion.getStudentID().equalsIgnoreCase(campCommitteeMember.getUserID())) {
                        System.out.println(i + " . Suggestion: "
                                + suggestion.getSuggestionString());
                        i++;
                    }
                }

                System.out.println("End of suggestions for camp: " + camp.getCampName());
                System.out.println("//////////////////////////////////////////");
                System.out.println("");

            }
        }

    }

    System.out.println("Enter EXIT to quit");

    Scanner scan = new Scanner(System.in);
    String exit = scan.nextLine();

    if(exit.equalsIgnoreCase("exit"))
    {
        return;
    }

    public static void viewSuggestion(Staff staff) {

        System.out.println("All suggestions:");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getStaffInCharge().equals(staff.getUserID())) {
                for (Suggestion suggestion : camp.getSuggestionList()) {

                    System.out.println(
                            i + ". [Camp: " + camp.getCampName() + "] Suggestion: " + suggestion.getSuggestionString());
                    i++;
                    System.out.println("Press EXIT to return to previous menu: ");
                    Scanner scan = new Scanner(System.in);
                    String exit = scan.nextLine();
                    String variable = "exit";
                    if (variable.equalsIgnoreCase(exit)) {
                        return;
                    }
                }
            }
        }
    }

}