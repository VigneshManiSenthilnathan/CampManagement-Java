package pkg_camp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// To store usernames and passwords
// Extend to store studentType too?
public class Credentials {
    private String username;
    private static String password; // Store encoded PW only

    public Credentials(String username, String password) {
        this.username = username;
        setPassword(password);
    }

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
    /*
     * public void setUsername(String newUsername) {
     * // Get password from username
     * String corresPW = getPassword(username);
     * this.username = newUsername;
     * // Also update in excel using corresPW to locate -- not just here!
     * }
     */

    public void setPassword(String newPassword) {

        String encodedNewPW = encodePassword(newPassword);
        Credentials.password = encodedNewPW;

    }

    // Run this in the main function to verify existing accounts
    public static boolean verifyPassword(String inputUsername, String inputPassword) {
        String encodedPW = getPassword(inputUsername);
        return encodedPW.equals(inputPassword);
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
            System.out.println("Encoding Successful!");
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Encoding Unsuccessful!");
            return null;
        }
    }

    public void excelWriter(String username, String password) {

        // Check if the Excel file exists
        File file = new File("user_passwords.xlsx"); // Change the file name as needed
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            // If the file exists, open it and check if the sheet "UserPasswords" exists
            try {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("UserPasswords");
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        else {
            // If the file doesn't exist, create a new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("UserPasswords");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Username");
            headerRow.createCell(1).setCellValue("EncodedPassword");
        }

        // Create a data row for the new username and password
        int lastRowNum = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(lastRowNum + 1);
        dataRow.createCell(0).setCellValue(username);
        dataRow.createCell(1).setCellValue(password);

        try {
            // Write the updated workbook to the Excel file
            FileOutputStream fileOut = new FileOutputStream("user_passwords.xlsx"); // Change the file name as needed
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Data has been written to the Excel file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}