package org.example;

import llist.LList;

import java.util.Comparator;

public class LListPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private LList<T> queue;

    public LListPriorityQueue() {
        this.queue = LList.empty();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void add(T object) {
        queue = queue.prepend(object);
    }

    @Override
    public T removeMax() {
        queue = queue.sorted(Comparator.reverseOrder());
        T res = queue.head();
        queue = queue.tail();
        return res;
    }

}
