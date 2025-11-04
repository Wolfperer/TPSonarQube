package org.example;

/**
 * File de priorité utilisant un tas biaisé (Skew Heap).
 * Cette implémentation est plus efficace que les listes pour
 * de grandes quantités de données.
 */
public class SkewHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private SkewHeap<T> heap;

    public SkewHeapPriorityQueue() {
        this.heap = SkewHeap.empty();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Ajouter un élément : utilise la méthode add() du tas biaisé.
     * Complexité : O(log n) en moyenne.
     */
    @Override
    public void add(T object) {
        heap = heap.add(object);
    }

    /**
     * Supprimer le maximum : la racine du tas contient toujours
     * l'élément maximum. On la retire et on reconstruit le tas.
     * Complexité : O(log n) en moyenne.
     */
    @Override
    public T removeMax() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T max = heap.value();
        heap = heap.removeRoot();
        return max;
    }
}