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

public class SubmitSuggestion {

    public static void submitSuggestion(CampCommitteeMember campCommitteeMember) {

        System.out.println("You can submit a Suggestion for the following Camps: ");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getCampCommitteeList().contains(campCommitteeMember)) {
                System.out
                        .println(i + ": " + camp.getCampName() + " [Camp Description: " + camp.getDescription() + "]");
            }
        }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        System.out.println("Submit Suggestion to Camp: ");
        String campNameString = sc.next();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (camp.getCampName().equalsIgnoreCase(campNameString)) {
                System.out.println("Your Suggestion: ");
                String campSuggestionString = sc.next();

                Suggestion newSuggestion = new Suggestion(campCommitteeMember.getUserID(), camp.getCampName(),
                        campSuggestionString);
                camp.addSuggestion(newSuggestion);
                System.out.println("Your Suggestion has been submitted!");
                System.out.println("");
            }
        }
    }

}