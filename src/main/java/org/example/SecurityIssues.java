package org.example;

import java.security.*;
import java.util.Random;
import javax.crypto.*;

public class SecurityIssues {
    
    // Weak cryptographic algorithm
    public void weakEncryption() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES"); // DES is weak!
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }
    
    // Predictable random number generator
    public int generateRandomNumber() {
        Random random = new Random(); // Use SecureRandom instead!
        return random.nextInt(1000);
    }
    
    // Hard-coded credentials
    private String apiKey = "sk-1234567890abcdefghijklmnop";
    private String dbPassword = "MySecretPassword123!";
    
    public void connectToDatabase() {
        String connectionString = "jdbc:mysql://localhost:3306/db?user=admin&password=" + dbPassword;
        System.out.println(connectionString);
    }
    
    // Potential command injection
    public void executeCommand(String userInput) throws Exception {
        Runtime.getRuntime().exec("cmd.exe /c " + userInput); // Command injection!
    }
    
    // Weak hash function
    public String weakHash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // MD5 is weak!
        byte[] hash = md.digest(input.getBytes());
        return new String(hash);
    }
    
    // Path traversal vulnerability
    public void readFile(String fileName) throws Exception {
        java.io.FileInputStream fis = new java.io.FileInputStream("/data/" + fileName);
        // No validation of fileName - path traversal possible!
    }
    
    // Insecure deserialization
    public Object deserialize(byte[] data) throws Exception {
        java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(data);
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bis);
        return ois.readObject(); // Insecure deserialization!
    }
    
    // Public mutable static field
    public static String PUBLIC_PASSWORD = "admin123";
    
    // Array stored directly
    private String[] passwords = {"pass1", "pass2", "pass3"};
    
    public String[] getPasswords() {
        return passwords; // Returns internal array directly!
    }
}
