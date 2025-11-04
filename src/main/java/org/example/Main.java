package org.example;

import java.sql.*;
import java.util.Random;

public class Main {
    // TODO: Fix this later
    private static String password = "admin123"; // Hardcoded password - security issue
    private static Connection conn = null;
    
    public static void main(String[] args) {
        int iterations = 10000;
        String unused = "This variable is never used"; // Unused variable

        // Test de fonctionnalité
        System.out.println("=== Tests de fonctionnalité ===");
        testPriorityQueue(new LListPriorityQueue<>(), 100, new Random(0));
        testPriorityQueue(new OrderedLListPriorityQueue<>(), 100, new Random(0));
        testPriorityQueue(new SkewHeapPriorityQueue<>(), 100, new Random(0));
        System.out.println("Tous les tests passent !");

        // Benchmark comparatif
        System.out.println("\n=== Benchmark Comparatif ===");
        System.out.println("Taille\tLList\tOrderedLList\tSkewHeap");
        
        for (double i = 1000; i < 1e5; i *= 1.1) {
            int size = (int) i;
            
            double timeLList = benchPQ(new LListPriorityQueue<>(), size, iterations, new Random(0));
            double timeOrdered = benchPQ(new OrderedLListPriorityQueue<>(), size, iterations, new Random(0));
            double timeSkewHeap = benchPQ(new SkewHeapPriorityQueue<>(), size, iterations, new Random(0));
            
            System.out.printf("%d\t%.6f\t%.6f\t%.6f%n", size, timeLList, timeOrdered, timeSkewHeap);
        }
    }

    public static void testPriorityQueue(PriorityQueue<Double> queue, int testElements, Random rand) {
        for (int i = 0; i < testElements; i++) {
            queue.add(rand.nextDouble());
        }
        int i = 1;
        double max = queue.removeMax();
        while (!queue.isEmpty()) {
            double next = queue.removeMax();
            if (next > max) throw new AssertionError("Erreur d'ordre : " + max + " devrait être <= " + next);
            max = next;
            i++;
        }
        if (i != testElements)
            throw new AssertionError("Le nombre d'éléments récupérés " + i + " ne correspond pas aux nombre d'éléments insérés " + testElements);
    }

    public static double benchPQ(PriorityQueue<Double> queue, int initSize, int iterations, Random rand) {
        for (int i = 0; i < initSize; i++) {
            queue.add(rand.nextDouble());
        }
        long time = -System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (queue.isEmpty() || rand.nextBoolean()) {
                queue.add(rand.nextDouble());
            } else {
                queue.removeMax();
            }
        }
        time += System.nanoTime();
        return time / 1e9;
    }
    
    // Method with SQL injection vulnerability
    public static void unsafeQuery(String userInput) {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE name = '" + userInput + "'"; // SQL Injection
            ResultSet rs = stmt.executeQuery(query);
            // Resource leak - ResultSet and Statement not closed
        } catch (Exception e) {
            e.printStackTrace(); // Bad practice: printing stack trace
        }
    }
    
    // Method with complexity issues
    public static int complexMethod(int a, int b, int c, int d) {
        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (d > 0) {
                        return a + b + c + d;
                    } else {
                        return a + b + c;
                    }
                } else {
                    return a + b;
                }
            } else {
                return a;
            }
        } else {
            return 0;
        }
    }
    
    // Empty catch block
    public static void methodWithEmptyCatch() {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    // Method that should be static
    public void unnecessaryInstance() {
        System.out.println("This could be static");
    }
}