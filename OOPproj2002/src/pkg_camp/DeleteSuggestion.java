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

public class DeleteSuggestion {

    public static void deleteSuggestion(CampCommitteeMember campCommitteeMember) {

        System.out.println("These are your suggestions: ");

        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Suggestion suggestion : camp.getSuggestionList()) {
                System.out.println(i + ". [Camp: " + suggestion.getCampName() + "] Suggestion: "
                        + suggestion.getSuggestionString());
                i++;
            }
        }

        System.out.println("Enter the suggestion number that you wish to delete: ");
        Scanner sc = new Scanner(System.in);
        int suggestionNumber = sc.nextInt();

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