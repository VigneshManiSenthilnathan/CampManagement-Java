package controller;

import java.util.List;

public class ReportController {

    public static void campCommitteeGR(CampCommitteeMember committeeMember, Camp camp) {
        GenerateReport.campCommitteeGR(committeeMember, camp);
    }

    public static void staffGR(Staff staff, List<Camp> allCamps) {
        GenerateReport.staffGR(staff, allCamps);
    }

    public static void getPerformanceReport(Staff staff, List<Camp> allCamps) {
        GenerateReport.getPerformanceReport(staff, allCamps);
    }

}
