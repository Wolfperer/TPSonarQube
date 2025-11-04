package llist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class LList<T> implements Iterable<T> {


    public static <T> LList<T> empty() {
        //noinspection unchecked
        return (LList<T>) EmptyLList.singleton;
    }

    public abstract T head();

    public abstract LList<T> tail();

    public abstract int size();

    public abstract boolean isEmpty();

    public boolean nonEmpty() {
        return !isEmpty();
    }

    public LList<T> prepend(T elt) {
        return new NELList<T>(elt, this);
    }

    public LList<T> concat(LList<T> that) {
        return reverse().stackOver(that);
    }

    public LList<T> reverse() {
        return this.stackOver(LList.empty());
    }

    public String toString() {
        var stb = new StringBuilder();
        for (var o : this) {
            stb.append(o).append("  ");
        }
        return stb.toString();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Iterator<T> iterator() {
        return new LListIterator<>(this);
    }

    public LList<T> stackOver(LList<T> that) {
        var r = that;
        for (var o : this) {
            r = r.prepend(o);
        }
        return r;
    }

    public static <T> LList<T> fromStream(Stream<T> stream) {
        LList<T> empty = empty();
        return stream.reduce(empty, LList::prepend, (a, b) -> b.concat(a)).reverse();
    }

    public LList<T> take(int n) {
        LList<T> source = this;
        LList<T> res = LList.empty();
        for (int i = 0; i < n; i++) {
            res = res.prepend(source.head());
            source = source.tail();
        }
        return res.reverse();
    }

    public LList<T> drop(int n) {
        LList<T> res = this;
        for (int i = 0; i < n; i++) {
            res = res.tail();
        }
        return res;
    }


    public LList<T> sorted(Comparator<T> comparator) {
        if (this.isEmpty() || this.tail().isEmpty()) {
            return this;
        }
        int mid = this.size() / 2;
        var left = this.take(mid).sorted(comparator);
        var right = this.drop(mid).sorted(comparator);
        return left.merge(right, comparator);
    }

    private LList<T> merge(LList<T> that, Comparator<T> comparator) {
        var l1 = this;
        var l2 = that;
        LList<T> merged = LList.empty();
        while (!l1.isEmpty() && !l2.isEmpty()) {
            if (comparator.compare(l1.head(), l2.head()) < 0) {
                merged = merged.prepend(l1.head());
                l1 = l1.tail();
            } else {
                merged = merged.prepend(l2.head());
                l2 = l2.tail();
            }
        }
        merged = l1.stackOver(merged);
        merged = l2.stackOver(merged);
        return merged.reverse();
    }


}
