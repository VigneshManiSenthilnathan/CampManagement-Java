package controller;

import java.util.Scanner;

public class DeleteSuggestion {

    public static void deleteSuggestion(CampCommitteeMember campCommitteeMember) {

        System.out.println("These are your suggestions: ");

        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {

            System.out.println("");

            System.out.println("Camp: " + camp.getCampName());
            System.out.println("");

            if (camp.getSuggestionList().size() > 0) {

                for (Suggestion suggestion : camp.getSuggestionList()) {
                    if (camp.getCampCommitteeList().contains(campCommitteeMember)
                            && (suggestion.getStudentID().equals(campCommitteeMember.getUserID()))) {
                        System.out.println(i + ". Suggestion: "
                                + suggestion.getSuggestionString());
                        i++;
                    }
                }

            }

            System.out.println("End of suggestions for camp: " + camp.getCampName());
            System.out.println("//////////////////////////////////////////");
            System.out.println("");
        }

        System.out.println("Enter the suggestion number that you wish to delete, or press 0 to exit: ");
        Scanner sc = new Scanner(System.in);
        int suggestionNumber = sc.nextInt();

        if (suggestionNumber == 0) {
            return;
        }

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (suggestionNumber >= 1 && suggestionNumber <= camp.getSuggestionList().size()) {
                camp.getSuggestionList().remove(suggestionNumber - 1); // Take in user's input, match it,
                                                                       // remove from the correct index in
                                                                       // the list
                System.out.println("Suggestion deleted successfully.");
            } else {
                System.out.println("Invalid suggestion number. No suggestion deleted.");
            }
        }
    }
}