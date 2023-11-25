package pkg_camp;

public class CampCommitteeMember extends Student {
    private CampInformation campDetails;
    private int points;

    public CampCommitteeMember(String userID, String password, String faculty) {
        super(userID, password, faculty);
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

    public String getUserID() {
        return super.getUserID();
    }

    public void awardPoints(int additionalPoints) {
        points += additionalPoints;
    }
}
