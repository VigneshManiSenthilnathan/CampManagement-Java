package controller;

import java.util.Scanner;

public class EditSuggestion {

    public static void editSuggestion(CampCommitteeMember campCommitteeMember) {

        System.out.println("These are your suggestions: ");

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

        System.out.println("Press EXIT to quit");

        Scanner scan = new Scanner(System.in);
        String exit = scan.next();
        if (exit.equalsIgnoreCase(exit)) {
            return;
        }

        System.out.println("Edit Suggestions (Enter Camp Name or 'exit' to cancel): ");
        Scanner sc = new Scanner(System.in);
        String campNameToModify = sc.nextLine();

        if (campNameToModify.equalsIgnoreCase("exit")) {
            System.out.println("Suggestions canceled.");
            System.out.println("");
            return;
        }

        boolean validInput = false;

        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Suggestion suggestion : camp.getSuggestionList()) {
                // if statement checks if camp name exists and i
                // suggestion was made by the student
                if (camp.getCampName().equalsIgnoreCase(campNameToModify)
                        && suggestion.getStudentID().equals(campCommitteeMember.getUserID())) {

                    System.out.println("Enter New Suggestion: ");
                    String newSuggestion = sc.nextLine();
                    suggestion.setSuggestionString(newSuggestion); // set the new suggestion into the place of the o
                                                                   // one

                    System.out.println("Suggestion edited successfully.");
                    return;
                }
            }
        }
    }
}