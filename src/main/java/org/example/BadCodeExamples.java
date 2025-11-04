package org.example;

import java.util.*;
import java.io.*;

// Class with multiple code smells
public class BadCodeExamples {
    
    // Magic numbers everywhere
    public int calculateSomething(int x) {
        return x * 42 + 100 - 7;
    }
    
    // Duplicated code
    public void printUserInfo1(String name) {
        System.out.println("User: " + name);
        System.out.println("Status: Active");
        System.out.println("Role: User");
        System.out.println("Created: 2024");
    }
    
    public void printUserInfo2(String name) {
        System.out.println("User: " + name);
        System.out.println("Status: Active");
        System.out.println("Role: User");
        System.out.println("Created: 2024");
    }
    
    // Method with too many parameters
    public void methodWithManyParams(String a, String b, String c, String d, 
                                     String e, String f, String g, String h) {
        System.out.println(a + b + c + d + e + f + g + h);
    }
    
    // Improper equals() implementation
    public boolean equals(Object obj) {
        if (obj instanceof BadCodeExamples) {
            return true; // Always returns true!
        }
        return false;
    }
    
    // Missing hashCode() when equals() is overridden
    
    // Method that's too long
    public void veryLongMethod() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int e = 5;
        int f = 6;
        int g = 7;
        int h = 8;
        int i = 9;
        int j = 10;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(i);
        System.out.println(j);
        // ... more code
        for (int k = 0; k < 100; k++) {
            System.out.println(k);
        }
    }
    
    // Potential null pointer dereference
    public void dangerousMethod(String input) {
        String result = input.toUpperCase(); // No null check!
        System.out.println(result);
    }
    
    // Resource leak
    public void fileHandlingIssue() throws IOException {
        FileInputStream fis = new FileInputStream("file.txt");
        // File not closed!
        int data = fis.read();
    }
    
    // Using deprecated methods
    @SuppressWarnings("deprecation")
    public void useDeprecated() {
        Date date = new Date();
        int year = date.getYear(); // Deprecated method
    }
    
    // Catching generic Exception
    public void catchTooGeneric() {
        try {
            // Some code
            int result = 10 / 0;
        } catch (Exception e) {
            // Catching too generic exception
            System.out.println("Error");
        }
    }
    
    // Switch without default
    public void switchWithoutDefault(int value) {
        switch (value) {
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
            // No default case
        }
    }
    
    // String concatenation in loop
    public String inefficientLoop() {
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result += i + ","; // Inefficient string concatenation
        }
        return result;
    }
    
    // Unnecessary boxing
    public void unnecessaryBoxing() {
        Integer x = new Integer(10); // Should use valueOf
        Integer y = Integer.valueOf(20);
    }
    
    // Thread.sleep in loop without proper handling
    public void sleepInLoop() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Interrupted exception swallowed
            }
        }
    }
}
