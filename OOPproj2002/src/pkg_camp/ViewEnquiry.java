package pkg_camp;

public class ViewEnquiry {

    public static void viewEnquiry(Student student) {
        System.out.println("You have submitted the following Enquiries: ");
        boolean found = false;
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (enquiry.getStudentID().equals(student.getUserID())) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("There are no camps for you to view Enquiries of!");
            System.out.println("");
            return;
        }
    }

    public static void viewEnquiry(CampCommitteeMember campCommitteeMember) {
        System.out.println("The Enquiries are: ");
        boolean found = false;
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (camp.getCampCommitteeList().contains(campCommitteeMember)) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("There are no camps for you to view Enquiries of!");
            System.out.println("");
            return;
        }
    }

    public static void viewEnquiry(Staff staff) {
        System.out.println("The Enquiries are: ");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (camp.getStaffInCharge().equalsIgnoreCase(staff.getUserID())) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                }
            }
        }
    }
}