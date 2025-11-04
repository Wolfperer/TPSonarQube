package org.example;

public interface PriorityQueue<T extends Comparable<T>> {
    void add(T object);

    T removeMax();

    boolean isEmpty();
}
