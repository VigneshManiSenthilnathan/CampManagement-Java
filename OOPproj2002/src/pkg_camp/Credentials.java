package pkg_camp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

// To store usernames and passwords
// Extend to store studentType too?
public class Credentials {
    private String username;
    private String password;
}

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
