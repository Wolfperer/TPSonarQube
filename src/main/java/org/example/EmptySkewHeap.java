package org.example;

/**
 * Implémentation d'un tas biaisé vide.
 * Cette classe représente un Skew Heap vide, utilisé comme cas de base.
 */
public class EmptySkewHeap<T extends Comparable<T>> extends SkewHeap<T> {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public T value() {
        throw new RuntimeException("Empty heap has no value");
    }

    @Override
    public SkewHeap<T> left() {
        throw new RuntimeException("Empty heap has no left child");
    }

    @Override
    public SkewHeap<T> right() {
        throw new RuntimeException("Empty heap has no right child");
    }

    @Override
    public SkewHeap<T> removeRoot() {
        throw new RuntimeException("Cannot remove root from empty heap");
    }

    /**
     * Fusion avec un autre tas : si ce tas est vide, 
     * le résultat est simplement l'autre tas.
     */
    @Override
    public SkewHeap<T> merge(SkewHeap<T> that) {
        return that;
    }
}