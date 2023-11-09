package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsController {
    private List<Suggestion> suggestions;

    public SuggestionsController() {
        suggestions = new ArrayList<>();
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public List<Suggestion> getAllSuggestions() {
        return suggestions;
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
