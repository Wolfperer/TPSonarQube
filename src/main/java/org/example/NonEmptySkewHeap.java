package org.example;

/**
 * Implémentation d'un tas biaisé non-vide.
 * Contient une valeur et deux sous-tas (gauche et droit).
 */
public class NonEmptySkewHeap<T extends Comparable<T>> extends SkewHeap<T> {
    private final T value;
    private final SkewHeap<T> left;
    private final SkewHeap<T> right;

    public NonEmptySkewHeap(T value, SkewHeap<T> left, SkewHeap<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public SkewHeap<T> left() {
        return left;
    }

    @Override
    public SkewHeap<T> right() {
        return right;
    }

    @Override
    public SkewHeap<T> removeRoot() {
        // Pour supprimer la racine, on fusionne les deux sous-tas
        return left.merge(right);
    }

    /**
     * Fusion de deux tas biaisés - l'algorithme clé des Skew Heaps.
     * 
     * Principe :
     * 1. Si l'autre tas est vide, retourner ce tas
     * 2. Sinon, déterminer quel tas a la plus grande racine
     * 3. Fusionner récursivement le sous-tas droit avec l'autre tas
     * 4. IMPORTANT : échanger gauche et droite (c'est le "biais")
     */
    @Override
    public SkewHeap<T> merge(SkewHeap<T> that) {
        if (that.isEmpty()) {
            return this;
        }

        // Si l'autre tas a une valeur plus grande, inverser l'ordre
        if (this.value().compareTo(that.value()) < 0) {
            return that.merge(this);
        }
        
        // Fusionner récursivement le sous-tas droit avec l'autre tas
        SkewHeap<T> mergedRight = this.right().merge(that);
        
        // CLEF : échanger gauche et droite (stratégie "biaisée")
        return new NonEmptySkewHeap<>(this.value(), mergedRight, this.left());
    }
}