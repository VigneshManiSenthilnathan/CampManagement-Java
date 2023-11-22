package pkg_camp;


import java.util.List;

public class CampController {

    public static void deleteCamp(Staff staff) {
        DeleteCamp.deleteCamp(staff);
    }

    public static void viewCamps(Student student) {
        ViewCamp.studentViewCamps(student);
    }

    public static void viewCamps(Staff staff) {
        ViewCamp.staffViewCamps(staff);
    }

    public static void viewCamps(CampCommitteeMember campCommitteeMember, Camp camp) {
        ViewCamp.campCommitteeViewCamp(campCommitteeMember, camp);
    }

    public static void modifyCamp(Staff staff) {
        ModifyCamp.modifyCamp(staff);
    }

    /*
     * public static void withdrawCampCommittee(CampCommitteeMember
     * campCommitteeMember, Camp camp) {
     * Withdrawal.withdrawCampCommittee(campCommitteeMember, camp);
     * }
     */ // apparently cannot do this???

    public static void registerForCamp(Student student, List<Camp> createdCampsList) {
        Registration.registerForCamp(student, createdCampsList);
    }

    public static void registerForCampCommitee(Student student) {
        Registration.registerForCampCommitee(student);
    }

    public static void withdrawCamp(Student student) {
        Withdrawal.withdrawCamp(student);
    }

    public static void createNewCamp(Staff staff) {
        CreateCamp.createNewCamp(staff);
    }

}
