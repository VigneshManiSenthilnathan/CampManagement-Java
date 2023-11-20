import java.util.Scanner;

public class DeleteEnquiry {

    public static void deleteEnquiry(Student student) {

        System.out.println("These are your Enquiries: ");
        int i = 1;
        for (Camp camp : CampsList.getCreatedCampsList()) {
            for (Enquiry enquiry : camp.getEnquiryList()) {
                if (enquiry.getStudentID().equals(student.getUserID())) {
                    System.out.println(
                            i + ". [Camp: " + enquiry.getCampName() + "] Enquiry: " + enquiry.getEnquiryString());
                    i++;
                }
            }
        }

        System.out.println("Delete Enquiry (Enter Camp Name): ");
        Scanner sc = new Scanner(System.in);
        int enquiryNumber = sc.nextInt();

        if (enquiryNumber >= 1 && enquiryNumber <= student.getEnquiryList().size()) {
            student.getEnquiryList().remove(enquiryNumber - 1); // Take in user's input, match it, remove from the
                                                                // correct index in the list
            System.out.println("Suggestion deleted successfully.");
        } else {
            System.out.println("Invalid suggestion number. No suggestion deleted.");
        }
    }
}