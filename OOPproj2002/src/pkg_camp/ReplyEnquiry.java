package pkg_camp;

public class ReplyEnquiry {

    public static void replyEnquiry(CampCommitteeMember campCommitteeMember) {

        // check if createdCampsList is empty
        if (CampsList.getCreatedCampsList().isEmpty()) {
            System.out.println("No camps available");
            return;
        }

        System.out.println("You can view the Enquiries of the following Camps: ");
        boolean found = false;
        for (Camp camp : CampsList.getCreatedCampsList()){
            if (camp.getCampCommitteeList().contains(campCommitteeMember)) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Dates: " + camp.getDates());
                System.out.println("Location: " + camp.getLocation());
                System.out.println("Total Slots: " + camp.getTotalSlots());
                System.out.println("Description: " + camp.getDescription());
                System.out.println("Staff in Charge: " + camp.getStaffInCharge());
                System.out.println("------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no camps for you to view Enquiries of!");
            System.out.println("");
            return;
        }

        boolean replyDone = false;
        while(!replyDone){
            
        }
    }
}
