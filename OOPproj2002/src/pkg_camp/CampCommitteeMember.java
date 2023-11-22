package pkg_camp;


public class CampCommitteeMember extends Student {
    private CampInformation campDetails;
    private int points;

    public CampCommitteeMember(String userID, String password, String faculty) {
        super(userID, password, faculty);
        // this.campDetails = campDetails;
        this.points = 0;
    }

    public CampCommitteeMember(CampInformation campDetails) {
        this.campDetails = campDetails;
        this.points = 0;

    }

    public CampInformation getCampDetails() {
        return campDetails;
    }

    public void setCampDetails(CampInformation campDetails) {
        this.campDetails = campDetails;
    }

    public int getPoints() {
        return points;
    }

    public String getUserID(){
        return super.getUserID();
    }

    public void awardPoints(int additionalPoints) {
        points += additionalPoints;
    }

    /*
     * public void suggestionAccepted() {
     * points += 2; // Award two points for an accepted suggestion
     * } maybe can implement this if want
     */

    public void generateReport(String filter) {
        // Generate a report based on the filter (attendee, camp committee, etc.)
        // Implement the report generation logic here
        // what do they mean by generate report of the camps they have created, since
        // when campcommittee can create camps
    }
}
