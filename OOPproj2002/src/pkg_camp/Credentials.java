package pkg_camp;

import java.io.*;
import java.util.Iterator;

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
        try {
            File file = new File("OOPproj2002/src/pkg_camp/user_passwords.xlsx"); // Change the file name as needed
            if (!file.exists()) {
                System.out.println("Excel file does not exist.");
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("UserPasswords");

            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // Skip the header row

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Cell usernameCell = row.getCell(0);
                Cell passwordCell = row.getCell(1);

                if (usernameCell != null && passwordCell != null) {
                    String cellUsername = usernameCell.getStringCellValue();
                    if (cellUsername.equals(username)) {
                        String encodedPassword = passwordCell.getStringCellValue();
                        fis.close();
                        return encodedPassword;
                    }
                }
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if the username is not found
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
        if (usernameExists(inputUsername) == false) {
            System.out.println("Username does not exist!");
            return false;
        }

        String encodeInputPW = encodePassword(inputPassword);

        String encodedPW = getPassword(inputUsername);
        // System.out.println(inputPassword);
        // System.out.println(encodedPW);
        // System.out.println(encodeInputPW);

        if (encodedPW.equals(encodeInputPW) == false) {
            System.out.println("Incorrect Password!");
            return false;
        }
        return encodedPW.equals(encodeInputPW);
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

    // Check if username exists
    public static boolean usernameExists(String username) {
        try {
            FileInputStream fis = new FileInputStream("OOPproj2002/src/pkg_camp/user_passwords.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("UserPasswords");

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Get the username from the first cell of the current row
                Cell cell = row.getCell(0);
                String cellValue = cell.getStringCellValue();

                // Check if the username matches the input
                if (cellValue.equals(username)) {
                    fis.close();
                    return true;
                }
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Write to excel
    public void excelWriter(String username, String password) {

        // Check if the Excel file exists
        File file = new File("OOPproj2002/src/pkg_camp/user_passwords.xlsx"); // Change the file name as needed
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
            FileOutputStream fileOut = new FileOutputStream("OOPproj2002/src/pkg_camp/user_passwords.xlsx"); // Change
                                                                                                             // the file
                                                                                                             // name as
                                                                                                             // needed
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Data has been written to the Excel file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updatePassword(String usernameToUpdate, String newPassword) {
        // Check if the Excel file exists
        File file = new File("OOPproj2002/src/pkg_camp/user_passwords.xlsx"); // Change the file name as needed
        Workbook workbook;
        Sheet sheet;

        String encodedNewPW = encodePassword(newPassword);

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
        } else {
            System.out.println("Excel file not found. Please create it first.");
            return;
        }

        // Find the username and update the password
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell usernameCell = row.getCell(0);

            if (usernameCell != null && usernameCell.getStringCellValue().equals(usernameToUpdate)) {
                // Found the username, update the password
                Cell passwordCell = row.getCell(1);
                passwordCell.setCellValue(encodedNewPW);
                break; // No need to continue searching
            }
        }

        try {
            // Write the updated workbook to the Excel file
            FileOutputStream fileOut = new FileOutputStream("OOPproj2002/src/pkg_camp/user_passwords.xlsx"); // Change
                                                                                                             // the file
                                                                                                             // name as
                                                                                                             // needed
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Password updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}