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

        boolean sug = false;
        while (!sug) {
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

            System.out.println("Edit Suggestions (Enter Camp Name or 'exit' to cancel): ");
            Scanner sc = new Scanner(System.in);
            String campNameToModify = sc.nextLine();

            if (campNameToModify.equalsIgnoreCase("exit")) {
                System.out.println("Suggestions canceled.");
                break;
            }

            boolean validInput = false;

            for (Camp camp : CampsList.getCreatedCampsList()) {
                for (Suggestion suggestion : camp.getSuggestionList()) {
                    if (camp.getCampName().equals(campNameToModify) //if statement checks if camp name exists and if suggestion was made by the student
                            && suggestion.getStudentID().equals(campCommitteeMember.getUserID())) { 
                        validInput = true;

                        System.out.println("Enter New Suggestion: ");
                        String newEnquiry = sc.nextLine();
                        suggestion.setSuggestionString(newEnquiry); // set the new suggestion into the place of the old one
                        System.out.println("Suggestion edited successfully.");
                        break;
                    }
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter a valid Camp Name or 'exit' to cancel."); // keep asking user to enter a valid camp name to suggest
                                                                                
        }
        // int j = 1; // follow enquiry modification for code below
        // for (Suggestion suggestion : camp.getSuggestionList()) {
        // if (j == suggestionNumber) {
        // System.out.println("Enter Your New Version: ");

        // sc.useDelimiter(System.lineSeparator());
        // String newSuggestion = sc.nextLine();

        // camp.getSuggestionList().set(j - 1,
        // suggestion.setSuggestionString(newSuggestion));
        // editSuggestionDetails(suggestion, newSuggestion);
        // } else {
        // j++;
        // }
        // }
        // return;
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