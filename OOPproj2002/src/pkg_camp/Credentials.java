package pkg_camp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

// To store usernames and passwords
// Extend to store studentType too?
public class Credentials {
    private String username;
    private static String password; // Store encoded PW only

    // Getters
    public String getUsername() {
        return username;
    }

    // Security Concern to return password directly.
    // Return the encoded password thats stored in excel
    private static String getPassword(String username) {
        // Implement getting relavant pw from username
        return password;
    }

    // Setters
    public void setUsername(String newUsername) {
        // Get password from username
        String corresPW = getPassword(username);
        this.username = newUsername;
        // Also update in excel using corresPW to locate -- not just here!
    }

    public void setPassword(String Username, String newPassword) {

        String encodedNewPW = encodePassword(newPassword);
        this.password = encodedNewPW;

    }

    // Run this in the main function to verify existing accounts
    public static boolean verifyPassword(String inputUsername, String inputPassword) {
        String encodedPW = getPassword(inputUsername);
        return encodedPW.equals(inputPassword);
    }

    // To store a username - [password] as a key value pair: Dictionary?
    // Dk if we need that since we can just use excel lol
    public static void storeCredentials(String username, String encodedPW) {

    }

    // To encode a password input
    public static String encodePassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convert the password to bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Compute the hash
            byte[] hashBytes = messageDigest.digest(passwordBytes);

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}