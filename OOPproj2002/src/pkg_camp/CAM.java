package pkg_camp;

import java.util.Scanner;

public class CAM {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		boolean exit = false;
		
		System.out.println("Main Menu:");
		System.out.println("(1) Login as Student");
		System.out.println("(2) Login as Staff");
		
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
            } else {
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

                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    default:
                        System.out.println("Invalid c")
                }


			}
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
			}
		}
		
		scanner.close();
		
	}
}
