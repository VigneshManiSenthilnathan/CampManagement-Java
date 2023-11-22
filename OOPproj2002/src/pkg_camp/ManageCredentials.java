package pkg_camp;

import java.util.Scanner;

public class ManageCredentials {

    public static void changePassword(Staff staff) {

        String newPassword3 = null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("If you want to exit, type EXIT. Enter new Password: ");
            String newPassword = sc.next();
            if (newPassword.equals("EXIT")) {
                return;
            }
            System.out.println("Retype new Password: ");
            String newPassword2 = sc.next();
            if (newPassword.equals(newPassword2)) {
                newPassword3 = newPassword;
                break;
            } else {
                System.out.println("Passwords do not match! Try again.");
                continue;
            }
        }

        String oldPW = Credentials.getPassword(staff.getUserID());
        // Add some conditions to check
        boolean change = false;
        while (!change) {
            if (!newPassword3.equals(oldPW)) {
                staff.setPassword(newPassword3);
                Credentials.updatePassword(staff.getUserID(), newPassword3);
                change = true;
            } else {
                System.out.println("Password same as previous. Use a Different Password!");
            }
        }
    }

    public static void changePassword(Student student) {

        String newPassword3 = null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("If you want to exit, type EXIT. Enter new Password: ");
            String newPassword = sc.next();
            if (newPassword.equals("EXIT")) {
                return;
            }
            System.out.println("Retype new Password: ");
            String newPassword2 = sc.next();
            if (newPassword.equals(newPassword2)) {
                newPassword3 = newPassword;
                break;
            } else {
                System.out.println("Passwords do not match! Try again.");
                continue;
            }
        }

        String oldPW = Credentials.getPassword(student.getUserID());
        // Add some conditions to check
        boolean change = false;
        while (!change) {
            if (!newPassword3.equals(oldPW)) {
                student.setPassword(newPassword3);
                Credentials.updatePassword(student.getUserID(), newPassword3);
                change = true;
            } else {
                System.out.println("Password same as previous. Use a Different Password!");
            }
        }
    }
}