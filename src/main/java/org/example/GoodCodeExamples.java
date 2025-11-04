package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

// Refactored class with improved code quality
public class GoodCodeExamples {
    
    private static final Logger LOGGER = Logger.getLogger(GoodCodeExamples.class.getName());
    private static final int MAGIC_MULTIPLIER = 42;
    private static final int MAGIC_ADDITION = 100;
    private static final int MAGIC_SUBTRACTION = 7;
    private static final int MAX_LOOP_ITERATIONS = 1000;
    
    private GoodCodeExamples() {
        // Private constructor to prevent instantiation
    }
    
    // Using constants instead of magic numbers
    public static int calculateSomething(int x) {
        return x * MAGIC_MULTIPLIER + MAGIC_ADDITION - MAGIC_SUBTRACTION;
    }
    
    // Extracted common code to a single method - No duplication
    public static void printUserInfo(String name) {
        LOGGER.log(Level.INFO, "User: {0}", name);
        LOGGER.log(Level.INFO, "Status: Active");
        LOGGER.log(Level.INFO, "Role: User");
        LOGGER.log(Level.INFO, "Created: 2024");
    }
    
    // Using a data object instead of many parameters
    public static void methodWithObject(UserData userData) {
        String result = userData.toString();
        LOGGER.log(Level.INFO, result);
    }
    
    // Proper equals() and hashCode() implementation
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    // Refactored shorter method
    public static void shortMethod() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int value : values) {
            LOGGER.log(Level.INFO, "Value: {0}", value);
        }
    }
    
    // Proper null check
    public static void safeMethod(String input) {
        if (input != null) {
            String result = input.toUpperCase();
            LOGGER.log(Level.INFO, result);
        } else {
            LOGGER.log(Level.WARNING, "Input is null");
        }
    }
    
    // Proper resource handling with try-with-resources
    public static void fileHandlingWithResources() {
        try (java.io.FileInputStream fis = new java.io.FileInputStream("file.txt")) {
            int data = fis.read();
            LOGGER.log(Level.INFO, "Data: {0}", data);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "File error", e);
        }
    }
    
    // Catching specific exception
    public static void catchSpecific() {
        try {
            int result = 10 / 2;
            LOGGER.log(Level.INFO, "Result: {0}", result);
        } catch (ArithmeticException e) {
            LOGGER.log(Level.SEVERE, "Arithmetic error", e);
        }
    }
    
    // Switch with default case
    public static void switchWithDefault(int value) {
        switch (value) {
            case 1:
                LOGGER.log(Level.INFO, "One");
                break;
            case 2:
                LOGGER.log(Level.INFO, "Two");
                break;
            default:
                LOGGER.log(Level.INFO, "Other");
                break;
        }
    }
    
    // Efficient string concatenation using StringBuilder
    public static String efficientLoop() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < MAX_LOOP_ITERATIONS; i++) {
            result.append(i).append(",");
        }
        return result.toString();
    }
    
    // Proper boxing
    public static void properBoxing() {
        Integer x = Integer.valueOf(10);
        Integer y = Integer.valueOf(20);
        LOGGER.log(Level.INFO, "Sum: {0}", x + y);
    }
    
    // Proper thread interruption handling
    public static void properSleepHandling() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARNING, "Thread interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Inner class for user data
    public static class UserData {
        private final String fieldA;
        private final String fieldB;
        private final String fieldC;
        
        public UserData(String fieldA, String fieldB, String fieldC) {
            this.fieldA = fieldA;
            this.fieldB = fieldB;
            this.fieldC = fieldC;
        }
        
        @Override
        public String toString() {
            return fieldA + fieldB + fieldC;
        }
    }
}
