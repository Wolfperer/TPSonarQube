package org.example;

import llist.LList;

/**
 * File de priorité utilisant une liste triée.
 * Principe : la liste est maintenue triée à chaque insertion,
 * ce qui rend removeMax() très rapide mais add() plus lent.
 */
public class OrderedLListPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private LList<T> queue;

    public OrderedLListPriorityQueue() {
        this.queue = LList.empty();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Insertion triée : on place l'élément à sa position correcte
     * pour maintenir l'ordre décroissant (plus grand en tête).
     */
    @Override
    public void add(T object) {
        queue = insertSorted(queue, object);
    }

    /**
     * Suppression du maximum : très simple car l'élément max
     * est toujours en tête de liste.
     */
    @Override
    public T removeMax() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T max = queue.head();
        queue = queue.tail();
        return max;
    }

    /**
     * Insère un élément dans une liste triée en maintenant l'ordre.
     * Complexité : O(n) dans le pire cas.
     */
    private LList<T> insertSorted(LList<T> list, T element) {
        if (list.isEmpty() || element.compareTo(list.head()) >= 0) {
            // Cas de base : liste vide ou élément plus grand que la tête
            return list.prepend(element);
        } else {
            // Récursion : insérer dans la queue
            return insertSorted(list.tail(), element).prepend(list.head());
        }
    }
}