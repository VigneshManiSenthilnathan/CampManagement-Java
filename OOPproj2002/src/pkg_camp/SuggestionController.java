package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class SuggestionController {

    public static void submitSuggestion(CampCommitteeMember campCommitteeMember) {
        SubmitSuggestion.submitSuggestion(campCommitteeMember);
    }

    public static void viewSuggestion(Staff staff) {
        ViewSuggestion.viewSuggestion(staff);
    }

    public static void approveSuggestion(Staff staff) {
        ApproveSuggestion.approveSuggestion(staff);
    }

    public static void viewSuggestion(CampCommitteeMember campCommitteeMember) {
        ViewSuggestion.viewSuggestion(campCommitteeMember);
    }

    public static void editSuggestion(CampCommitteeMember campCommitteeMember) {
        EditSuggestion.editSuggestion(campCommitteeMember);
    }

    public static void deleteSuggestion(CampCommitteeMember campCommitteeMember) {
        DeleteSuggestion.deleteSuggestion(campCommitteeMember);
    }

    public static void campCommitteeAddPoints(CampCommitteeMember campCommitteeMember) {
        // Suggestion.campCommitteeAddPoints(campCommitteeMember);
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
