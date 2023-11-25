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
            System.out.println("You have not submitted an Enquiry!");
            System.out.println("");
            return;
        }
    }

    public static void viewOwnEnquiry(CampCommitteeMember campCommitteeMember) {
        System.out.println("You have submitted the following Enquiries: ");
        boolean found = false;
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (enquiry.getStudentID().equals(campCommitteeMember.getUserID())) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("You have not submitted an Enquiry!");
            System.out.println("");
            return;
        }
    }

    public static void viewEnquiry(CampCommitteeMember campCommitteeMember, Camp camp) {
        System.out.println("The Enquiries are: ");
        boolean found = false;
        int i = 1;
        for (Enquiry enquiry : camp.getEnquiryList()) {
            System.out.println(i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
            i++;
            found = true;
        }
        if (!found) {
            System.out.println("There are no Enquiries to view!");
            System.out.println("");
            return;
        }
    }

    public static void viewEnquiry(Staff staff) {
        System.out.println("The Enquiries are: ");
        int i = 1;
        boolean found = false;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (camp.getStaffInCharge().equalsIgnoreCase(staff.getUserID())) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("There are no Camps with Enquiries for you to view!");
            System.out.println("");
            return;
        }
    }
}