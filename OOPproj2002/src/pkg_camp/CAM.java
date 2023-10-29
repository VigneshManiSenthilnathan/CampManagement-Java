package pkg_camp;

import java.util.Scanner;

public class CAM {
	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		
		boolean exit = false;
		
        //Branch to here after changing password
        while(!exit){
            System.out.println("Main Menu:");
            System.out.println("(1) Login as Student");
            System.out.println("(2) Login as Staff");
            System.out.println("(3) Exit CAMs");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.println("Student Login:");
                System.out.print("UserID: ");
                String userID = scanner.nextLine();
                System.out.print("Password (default: password): ");
                String password = scanner.nextLine();
                System.out.print("Faculty: ");
                String faculty = scanner.nextLine();

                if (password.equals("password")) {
                    Student student = new Student(userID, password, faculty);
                } 
                
                else {
                    System.out.println("Invalid login credentials.");
                }
                
                while (!exit) {
                    System.out.println("Student Menu:");
                    System.out.println("(1) Change Password");
                    System.out.println("(2) View List of Camps");
                    System.out.println("(3) Register for a Camp");
                    System.out.println("(4) Submit Enquiry");
                    System.out.println("(5) View, Edit or Delete your Enquiry");
                    System.out.println("(6) Check Registered Camps");
                    System.out.println("(7) Withdraw from a Camp");
                    
                    int menu = scanner.nextInt();

                    switch(menu){
                        case 1:
                            String newPassword = scanner.nextLine();
                            student.setPassword(newPassword);
                            exit = true;
                            break;

                        case 2:
                            
                            break;

                        case 3:
                            student.registerForCamp();
                            break;

                        case 4:
                            student.newEnquiry();
                            break;

                        case 5:
                            boolean quit = false;
                            while (!quit) {
                                System.out.println("Choose your option below");
                                System.out.println("Press 1 to view");
                                System.out.println("Press 2 to edit");
                                System.out.println("Press 3 to delete");
                                int option = scanner.nextInt();

                                switch(option) {
                                    case 1:
                                        student.getEnquiries();
                                        quit = true;
                                        break;
                                    case 2:
                                        student.editEnquiry();
                                        quit = true;
                                        break;
                                    case 3:
                                        student.deleteEnquiry();
                                        quit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please choose a valid option.");
                                }
                            }
                            break;

                        case 6:
                            break;

                        case 7:
                            boolean quit1 = false;
                            System.out.println("Are you sure you want to withdraw from the camp(Y/N)?");
                            while(quit1!=true){
                                String Decision = scanner.nextLine().toUpperCase();
                                char c = Decision.charAt(0);
                                if(c == 'Y'){
                                    student.withdraw();
                                    break;
                                }
                                else if(c == 'N'){
                                    break;
                                }
                                else{
                                    System.Out.println("Error Please enter either Y or N");
                                    continue;
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please choose a valid option.");
                    }
                }
                exit = false;
            }
            
            else if (choice == 2) {
                System.out.println("Staff Login:");
                System.out.print("UserID: ");
                String userID = scanner.nextLine();
                System.out.print("Password (default: password): ");
                String password = scanner.nextLine();
                System.out.print("Faculty: ");
                String faculty = scanner.nextLine();

                if (password.equals("password")) {
                    Staff staff = new Staff(userID, password, faculty);
                } 
                
                else {
                    System.out.println("Invalid login credentials.");
                }

                while (!exit) {
                    System.out.println("Staff Menu:");
                    System.out.println("(1) Change Password");
                    System.out.println("(2) Create, Edit or Delete Camps");
                    System.out.println("(3) Toggle Camp Visibility");
                    System.out.println("(4) View All Camps");
                    System.out.println("(5) View List Created Camps");
                    System.out.println("(6) View or Reply Enquiries");
                    System.out.println("(7) Camp Suggestions");
                    System.out.println("(8) Generate Camp Report");
                    System.out.println("(9) Generate Performance Report");

                    int menu = scanner.nextInt();

                    switch(menu){

                        case 1: //Change password
                            String newPassword = scanner.nextLine();
                            staff.setPassword(newPassword);
                            exit = true;
                            break;

                        case 2: //Create, edit, delete camp


                        case 3: //Camp visibility

                        case 4: //View list of all camps

                        case 5: //View List of Created camps

                        case 6: //View or Reply enquiries

                        case 7: //Camp suggestions

                        case 8: //Generate camp report

                        case 9: //Generate performance report

                        default:
                            System.out.println("Invalid choice. Please choose a valid option.");
                            
                    }


                }
            }

            else {
                exit = true;
            }
        }

        scanner.close();
	}
}
