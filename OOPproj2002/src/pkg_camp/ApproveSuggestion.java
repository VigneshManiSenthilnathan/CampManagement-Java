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

public class ApproveSuggestion {

    public static void approveSuggestion(Staff staff) {

        System.out.println("All suggestions:");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Suggestion suggestion : camp.getSuggestionList()) {
                if (camp.getStaffInCharge().equals(staff.getUserID())) {
                    System.out.println(i + ". [Camp: " + suggestion.getCampName() + "] Suggestion: "
                            + suggestion.getSuggestionString());
                    i++;
                }
            }
        }

        System.out.println("Enter the suggestion number that you wish to approve: ");
        Scanner sc = new Scanner(System.in);
        int suggestionNumber = sc.nextInt();

        for (Camp camp : CampsList.getCreatedCampsList()) {
            if (suggestionNumber >= 1 && suggestionNumber <= camp.getSuggestionList().size()) {
                if (camp.getSuggestionList().get(suggestionNumber - 1).isApproved() = false) {
                    camp.getSuggestionList().get(suggestionNumber - 1).isApproved() = true;
                    System.out.println("Suggestion has been approved successfully.");
                }
            } else {
                System.out.println("This suggestion is already approved!");
            }
        }
    }
}
