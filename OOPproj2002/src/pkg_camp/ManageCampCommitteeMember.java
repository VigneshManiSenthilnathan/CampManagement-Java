package pkg_camp;

import java.util.Scanner;

public class ManageCampCommitteeMember {

    public static void ManageCCMember(Staff staff) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        Camp chosenCamp = null;
        String exit = "EXIT";

        while (!found) {
            System.out.println("Enter Camp Name. Enter EXIT to quit: ");
            String campName = scanner.next();

            if (campName.equals(exit.toUpperCase())) {
                break;
            }

            for (Camp camp : CampsList.getCreatedCampsList()) {
                if (camp.getCampName().equals(campName)) {
                    found = true;
                    chosenCamp = camp;
                    // Print out a list
                    System.out.println("Camp Committee Members:");
                    for (Student committeeMember : camp.getCampCommitteeList()) {
                        System.out.println(committeeMember.getUserID());
                    }
                }
            }
        }

        boolean removaldone = false;

        while (!removaldone && chosenCamp != null) {
            System.out.println("Do you want to remove any of the above Camp Committee Member?: ");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            try { // try for variable, if input is not an integer, catch it below and continue the
                  // while loop
                int remove = scanner.nextInt();

                if (remove == 1) {

                    int flag = 1; // Flag initialised as 1 to account for various cases
                    String studentmainID = null; // To pass out the studentID to the rest of the methods

                    while (flag == 1) {
                        System.out.println("Enter EXIT to exit. Enter Student ID: ");
                        String studentID = scanner.next();
                        if (studentID.toUpperCase().equals("EXIT")) {
                            flag = 2; // Input studentID matches exit function condition. While loop breaks, and
                                      // the if statement later exits to the main StaffMenu
                            break;
                        }

                        for (String UserID : chosenCamp.getCommiteeUserID()) { // Iterate through campcommittee
                                                                               // studentID
                            if (UserID.equals(studentID.toUpperCase())) { // If one value matches, set flag to 3
                                                                          // and pass value onto the external
                                                                          // studentMain variable
                                flag = 3;
                                studentmainID = studentID.toUpperCase();
                                break; // break to exit the for loop. While loop will exit because flag != 1
                            } else {
                                flag = 4; // studentID was not found, set flag to 4 and continue the for loop.
                                          // for loop and while loop exits if studentID is not found.
                                continue;
                            }
                        }
                    }

                    if (flag == 2) {
                        break;
                    }

                    if (flag == 4) {
                        System.out.println("Student was not found in the camp indicated. Please try again."); // Restart
                                                                                                              // cuz
                                                                                                              // studentID
                                                                                                              // was not
                                                                                                              // found
                        continue;
                    }

                    if (studentmainID != null) {
                        staff.campCommitteeRejection(studentmainID, chosenCamp);
                        System.out.println(
                                studentmainID + " was successfully removed from " + chosenCamp.getCampName()); // successful
                                                                                                               // condition
                        break;
                    }

                    break;
                }

                else if (remove == 2) {
                    System.out.println("No student was removed.");
                    removaldone = true;
                    break;
                }

                else {
                    System.out.println("Invalid choice. Please choose a valid option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please type in an integer");
                continue;
            }
        }
    }
}