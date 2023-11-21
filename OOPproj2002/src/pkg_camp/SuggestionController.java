package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class SuggestionController {

    public static void submitSuggestion(CampCommitteeMember campCommitteeMember) {
        SubmitSuggestion.submitSuggestion(campCommitteeMember);
    }

    public static void ViewSuggestion(Staff staff) {
        ViewSuggestion.staffViewSuggestion(staff);
    }

    public static void ApproveSuggestion(Staff staff) {
        ApproveSuggestion.approveSuggestion(staff);
    }

    public static void ViewSuggestion(CampCommitteeMember campCommitteeMember) {
        ViewSuggestion.campCommitteeViewSuggestion(campCommitteeMember);
    }

    public static void EditSuggestion(CampCommitteeMember campCommitteeMember) {
        EditSuggestion.editSuggestion(campCommitteeMember);
    }

    public static void DeleteSuggestion(CampCommitteeMember campCommitteeMember) {
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
