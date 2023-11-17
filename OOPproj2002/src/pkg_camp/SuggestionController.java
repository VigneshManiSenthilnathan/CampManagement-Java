package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class SuggestionController {

    public void addSuggestion(Suggestion suggestion) {
        // get the list first, then append to the end of the list
        suggestion.add(suggestion);
    }

    public List<Suggestion> getAllSuggestions() {
        // getSuggestionList
    }

    public static class Suggestion {
        private String campName;
        private String suggestedDetails;
        private boolean isAccepted;

        public Suggestion(String campName, String suggestedDetails) {
            this.campName = campName;
            this.suggestedDetails = suggestedDetails;
            this.isAccepted = false;
        }

        public String getCampName() {
            return campName;
        }

        public String getSuggestedDetails() {
            return suggestedDetails;
        }

        public boolean isAccepted() {
            return isAccepted;
        }

        public void acceptSuggestion() {
            isAccepted = true;
        }
    }
}

/*
 * A staff can view and approve suggestions to changes to camp details from camp
 * committee
 * A camp committee member can submit suggestions for changes to camp details to
 * staff
 * A camp committee member can view, edit, and delete the details of his/her
 * suggestions before being processed
 * A camp committee member can get one point for each enquiry replied and each
 * suggestion given. One extra point will be granted for each accepted
 * suggestion
 */
