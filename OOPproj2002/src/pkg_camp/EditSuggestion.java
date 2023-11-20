package pkg_camp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditSuggestion {

    public static void editSuggestion(CampCommitteeMember campCommitteeMember) {

        System.out.println("These are your suggestions: ");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Suggestion suggestion : camp.getSuggestionList()) {
                if (suggestion.getStudentID().equals(campCommitteeMember.getUserID())) {
                    System.out.println(i + ". [Camp: " + suggestion.getCampName() + "] Suggestion: "
                            + suggestion.getSuggestionString());
                    i++;
                }

            }
        }

        System.out.println("Modify Enquiry (Enter Camp Name or 'exit' to cancel): ");
        String campNameToModify = sc.nextLine();

        if (campNameToModify.equalsIgnoreCase("exit")) {
            System.out.println("Modification canceled.");
            break;
        }

        System.out.println("Enter the suggestion number that you wish to edit: ");
        Scanner sc = new Scanner(System.in);
        int suggestionNumber = sc.nextInt();

        int j = 1;
        for (Suggestion suggestion : camp.getSuggestionList()) {
            if (j == suggestionNumber) {
                System.out.println("Enter Your New Version: ");

                sc.useDelimiter(System.lineSeparator());
                String newSuggestion = sc.nextLine();

                camp.getSuggestionList().set(j - 1, suggestion.setSuggestionString(newSuggestion));
                // editSuggestionDetails(suggestion, newSuggestion);
            } else {
                j++;
            }
        }
        return;
    }

    // public static void editSuggestionDetails(CampCommitteeMember
    // campCommitteeMember,Suggestion suggestion, String edit) {
    // for (Suggestion suggestions : campCommitteeMember.getSuggestionList()){
    // if(suggestions.getSuggestionString.equals(suggestion.getSuggestionString)){

    // }
    // }
    // System.out.println("Suggestion has been changed!");
    // }
}