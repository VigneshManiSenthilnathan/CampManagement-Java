package pkg_camp;

import java.util.Scanner;

public class ModifyCamp extends Staff {

    public static void modifyCamp(String campName){

        // Implement Sanity Check for camp name

boolean done = false;    
        
        while(!done)        
{}{}.out{
            ("(0) Change Camp Name");
            SySystem.out.println("(1) Change Camp Dates");
            System.out.println("(2) Change Camp Registration Closing Date");
            System.out.println("(3) Change Viewing User Group");
            System.out.println("(4) Change Location");
            System.out.println("(5) Change Total Slots Available");
            System.out.println("(6) Change Camp Committee Slots Available");
            System.out.println("(7) Change Camp Description");
            System.out.println("(8) Change Camp Visibility");
            System.out.println("(9) Change StaffInCharge");
            System.out.println("(10) Exit to Staff Menu");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch(choice){
                case 0:
                    System.out.println("Enter New Camp Name: ");
                    String newcampname = sc.nextLine();
                    CampInformation.editCampDetails(campName, 0, newcampname);
                    break;
                case 1:
                    System.out.println("Enter New Camp Dates: ");
                    String newcampdate = sc.nextLine();
                    CampInformation.editCampDetails(campName, 1, newcampdate);
                    break;
                case 2:
                    System.out.println("Enter New Camp Registration Closing Date: ");
                    String newcampregdate = sc.nextLine();
                    CampInformation.editCampDetails(campName, 2, newcampregdate);
                    break;
                case 3:
                    System.out.println("Enter New Camp UserGroup: ");
                    String newcampusergrp = sc.nextLine();
                    CampInformation.editCampDetails(campName, 3, newcampusergrp);
                    break;
                case 4:
                    System.out.println("Enter New Camp Location: ");
                    String newcamplocation = sc.nextLine();
                    CampInformation.editCampDetails(campName, 4, newcamplocation);
                    break;
                case 5:
                    System.out.println("Enter New Total Slots Available: ");
                    int newcamptotalslots = sc.nextLine();
                    CampInformation.editCampDetails(campName, 5, newcamptotalslots);
                    break;
                case 6:
                    System.out.println("Enter New Total Camp Committee Slots Available: ");
                    int newcampcommitteeslots = sc.nextLine();
                    CampInformation.editCampDetails(campName, 6, newcampcommitteeslots);
                    break;
                case 7:
                    System.out.println("Enter New Camp Description: ");
                    String newcampdescription = sc.nextLine();
                    CampInformation.editCampDetails(campName, 7, newcampdescription);
                    break;
                case 8:
                    System.out.println("Enter New Camp Visibility (Visible = 1, Invisible = 0): ");
                    int newcampvisibility = sc.nextLine();
                    CampInformation.toggleVisibility(campName, newcampvisibility);
                    break;
                case 9:
                    System.out.println("Enter New StaffInCharge: ");
                    String newstaffincharge = sc.nextLine();
                    CampInformation.editCampDetails(campName, 8, newstaffincharge);
                case 10:
                    System.out.println("Exiting back to staff menu...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                        break;
            } 
        }
        }
  
