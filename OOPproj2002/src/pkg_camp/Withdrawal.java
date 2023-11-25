package pkg_camp;

import java.util.Scanner;

public class Withdrawal {

    public static void withdrawCamp(Student student) {
        Scanner sc = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            int i = 1;
            System.out.println("List of Camps available to withdraw:");

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getUserGroup().toUpperCase().equals(student.getFaculty().toUpperCase())
                        && camp.getAttendeeUserID().contains(student.getUserID())) {
                    System.out.println(i + ". " + camp.getCampName());
                    System.out.println("");
                    i++;
                }
            }

            // Withdraw from an available Camp
            System.out.println("Withdraw from Camp (Type 'EXIT' to stop): ");
            System.out.print("Camp Name: ");
            String campname = sc.nextLine();

            if (campname.toUpperCase().equals("EXIT")) {
                done = true;
            } else {
                boolean withdrawalSuccessful = false;

                for (Camp camp : CampsList.getCreatedCampsList()) {
                    if (campname.equals(camp.getCampName())
                        && camp.getAttendeeUserID().contains(student.getUserID())) {

                        System.out.println("You are about to withdraw from "+camp.getCampName()+". Are you sure? (Y/N)");
                        String confirm = sc.nextLine();

                        if (confirm.toUpperCase().equals("Y")) {
                            camp.removeAttendee(student.getUserID());
                            camp.addBlackList(student.getUserID());
                            System.out.println("Withdrawal successful from " + camp.getCampName());
                            System.out.println("You are not allowed to re-join this camp.");
                            withdrawalSuccessful = true;    
                        }
                        else if (confirm.toUpperCase().equals("N")) {
                            System.out.println("Withdrawal cancelled.");
                            withdrawalSuccessful = true;
                        }
                        else{
                            System.out.println("Invalid input. Withdrawal cancelled. Returning to menu.");
                            withdrawalSuccessful = true;
                        }
                        
                    }
                }

                if (!withdrawalSuccessful) {
                    System.out.println("Invalid camp name or you are not registered for this camp.");
                }
            }
        }
    }

    public static void withdrawCamp(CampCommitteeMember campCommitteeMember) {
        Scanner sc = new Scanner(System.in);

        boolean done = false;

        while (!done) {
            int i = 1;
            System.out.println("List of Camps available to withdraw:");

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getUserGroup().toUpperCase().equals(campCommitteeMember.getFaculty().toUpperCase())
                        && camp.getAttendeeUserID().contains(campCommitteeMember.getUserID()) && camp.getCampCommitteeList().contains(campCommitteeMember)) {
                    System.out.println(i + ". " + camp.getCampName() + " [Camp Committee Member]");
                    System.out.println("");
                    i++;
                }
                else if(camp.getUserGroup().toUpperCase().equals(campCommitteeMember.getFaculty().toUpperCase())
                        && camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())){
                    System.out.println(i + ". " + camp.getCampName() + " [Camp Committee Member]");
                    System.out.println("");
                    i++;
                }
            }

            // Withdraw from an available Camp
            System.out.println("Withdraw from Camp (Type 'EXIT' to stop): ");
            System.out.print("Camp Name: ");
            String campname = sc.nextLine();

            if (campname.toUpperCase().equalsIgnoreCase("exit")) {
                done = true;
            } else {
                boolean withdrawalSuccessful = false;

                for (Camp camp : CampsList.getCreatedCampsList()) {
                    if (campname.equalsIgnoreCase(camp.getCampName())
                            && camp.getAttendeeUserID().contains(campCommitteeMember.getUserID())) {

                        System.out.println("You are about to withdraw from "+camp.getCampName()+". Are you sure? (Y/N)");
                        String confirm = sc.nextLine();

                        if (confirm.toUpperCase().equals("Y")){
                            camp.removeAttendee(campCommitteeMember.getUserID());
                            camp.addBlackList(campCommitteeMember.getUserID());
                            System.out.println("Withdrawal successful from " + camp.getCampName());
                            System.out.println("You are not allowed to re-join this camp.");
                            withdrawalSuccessful = true;
                        }
                        else if (confirm.toUpperCase().equals("N")) {
                            System.out.println("Withdrawal cancelled.");
                            withdrawalSuccessful = true;
                        }
                        else{
                            System.out.println("Invalid input. Withdrawal cancelled. Returning to menu.");
                            withdrawalSuccessful = true;
                        }                      
                    }
                    else if (campname.equalsIgnoreCase(camp.getCampName()) && camp.getCommiteeUserID().contains(campCommitteeMember.getUserID())) {
                        System.out.println("Camp Committee Member cannot quit from Camp!");
                        withdrawalSuccessful = true;
                    }
                }

                if (!withdrawalSuccessful) {
                    System.out.println("Invalid camp name or you are not registered for this camp.");
                }
            }
        }
    }
}