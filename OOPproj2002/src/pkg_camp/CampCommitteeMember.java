package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class CampCommitteeMember extends Student {
    private CampInformation campDetails;
    private List<String> suggestions;
    private int points;

    public CampCommitteeMember(String userID, String password, String faculty, CampInformation campDetails) {
        super(userID, password, faculty);
        this.campDetails = campDetails;
        this.suggestions = new ArrayList<>();
        this.points = 0;
    }

    public CampInformation getCampDetails() {
        return campDetails;
    }

    public void setCampDetails(CampInformation campDetails) {
        this.campDetails = campDetails;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void submitSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }

    public void viewSuggestions() {
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }

    public int getPoints() {
        return points;
    }

    public void awardPoints(int additionalPoints) {
        points += additionalPoints;
    }

    public void generateReport(String filter) {
        // Generate a report based on the filter (attendee, camp committee, etc.)
        // Implement the report generation logic here
    }
}

