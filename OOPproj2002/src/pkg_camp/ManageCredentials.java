package pkg_camp;

import java.util.Scanner;

public class ManageCredentials {

    public static void changePassword(Staff staff) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new Password: ");
        String newPassword = sc.next();
        String oldPW = Credentials.getPassword(staff.getUserID());
        // Add some conditions to check
        boolean change = false;
        while (!change) {
            if (!newPassword.equals(oldPW)) {
                staff.setPassword(newPassword);
                Credentials.updatePassword(staff.getUserID(), newPassword);
                change = true;
            } else {
                System.out.println("Use a Different Password!");
            }
        }
    }

}