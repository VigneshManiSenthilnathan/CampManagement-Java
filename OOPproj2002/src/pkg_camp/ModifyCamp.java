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
            System.out.println("(3) Change Faculty");
            System.out.println("(4) Change Location");
            System.out.println("(5) Change Total Slots Available");
            System.out.println("(6) Change Camp Committee Slots Available");
            System.out.println("(7) Change Camp Description");
            System.out.println("(8) Change Camp Visibility");
            System.out.println("(9) Exit to Staff Menu");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch(choice){
                case 0:
                    System.out.println("Enter New Camp Name: ");
                    String newcampname = sc.nextLine();
                    break;

                                    default:

                    System.out.println("Invalid choice. Please choose a valid option.");
                        break;
            } 
        }
        }
  