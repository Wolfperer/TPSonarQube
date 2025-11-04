package org.example;

public abstract class SkewHeap<T extends Comparable<T>> {
    /**
     * Crée un tas biaisé vide.
     */
    public static <T extends Comparable<T>> SkewHeap<T> empty() {
        return new EmptySkewHeap<>();
    }

    public abstract boolean isEmpty();

    public abstract T value();

    public abstract SkewHeap<T> left();
    public abstract SkewHeap<T> right();

    public abstract SkewHeap<T> removeRoot();

    /**
     * Ajoute un élément au tas.
     * Principe : créer un nouveau tas avec juste cet élément,
     * puis le fusionner avec le tas existant.
     */
    public final SkewHeap<T> add(T object) {
        SkewHeap<T> singleElementHeap = new NonEmptySkewHeap<>(object, empty(), empty());
        return this.merge(singleElementHeap);
    }

    public abstract SkewHeap<T> merge(SkewHeap<T> that);
}
