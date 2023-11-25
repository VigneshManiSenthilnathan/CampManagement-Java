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

    public static void viewCamps(CampCommitteeMember campCommitteeMember) {
        ViewCamp.campCommitteeViewCamp(campCommitteeMember);
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

    public static void registerForCamp(Student student) {
        Registration.registerForCamp(student);
    }

    public static void manageCreatedCamps(Staff staff) {
        ManageCreatedCamps.manageCreatedCamps(staff);
    }

    public static void registerForCamp(CampCommitteeMember campCommitteeMember) {
        Registration.registerForCamp(campCommitteeMember);
    }

    public static void withdrawCamp(Student student) {
        Withdrawal.withdrawCamp(student);
    }

    public static void withdrawCamp(CampCommitteeMember campCommitteeMember) {
        Withdrawal.withdrawCamp(campCommitteeMember);
    }

    public static void createNewCamp(Staff staff) {
        CreateCamp.createNewCamp(staff);
    }

}
