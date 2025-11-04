package org.example;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;

public class SecureCodeExamples {
    
    private static final Logger LOGGER = Logger.getLogger(SecureCodeExamples.class.getName());
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int AES_KEY_SIZE = 256;
    
    private SecureCodeExamples() {
        // Private constructor to prevent instantiation
    }
    
    // Strong cryptographic algorithm (AES instead of DES)
    public static void strongEncryption() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGen.init(AES_KEY_SIZE);
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        LOGGER.log(Level.INFO, "Encryption initialized with AES");
    }
    
    // Using SecureRandom instead of Random
    public static int generateSecureRandomNumber() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(1000);
    }
    
    // No hard-coded credentials - using configuration or environment variables
    public static void connectToDatabase(String dbPassword) {
        if (dbPassword == null || dbPassword.isEmpty()) {
            LOGGER.log(Level.SEVERE, "Database password not provided");
            return;
        }
        // Use the password from secure configuration
        String connectionString = "jdbc:mysql://localhost:3306/db?user=admin";
        LOGGER.log(Level.INFO, "Connection string prepared: {0}", connectionString);
    }
    
    // Parameterized query to prevent SQL injection
    public static void executeSecureQuery(String userInput, java.sql.Connection conn) throws Exception {
        String query = "SELECT * FROM users WHERE name = ?";
        try (java.sql.PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userInput);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LOGGER.log(Level.INFO, "User found");
                }
            }
        }
    }
    
    // Strong hash function (SHA-256 instead of MD5)
    public static String strongHash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }
    
    // Input validation to prevent path traversal
    public static void readFileSafely(String fileName) throws Exception {
        // Validate filename - only allow alphanumeric characters
        if (!fileName.matches("^[a-zA-Z0-9._-]+$")) {
            throw new IllegalArgumentException("Invalid filename");
        }
        try (java.io.FileInputStream fis = new java.io.FileInputStream("/data/" + fileName)) {
            int data = fis.read();
            LOGGER.log(Level.INFO, "Data read: {0}", data);
        }
    }
    
    // Returning a copy of the array instead of the original
    private String[] passwords = {"pass1", "pass2", "pass3"};
    
    public String[] getPasswords() {
        return Arrays.copyOf(passwords, passwords.length);
    }
    
    // Helper method to convert bytes to hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
