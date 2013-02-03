package psychicpoker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.02.13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public class CombinationIterator<T> implements Iterator<Collection<T>> {

    private int[] indices;
    private T[] elements;
    private boolean hasNext = true;

    public CombinationIterator(T[] elements, int k) throws IllegalArgumentException {
        checkArgument(k<=elements.length, "Impossible to select %d elements from hand of size %d", k, elements.length);
        this.indices = new int[k];
        for(int i=0; i<k; i++)
            indices[i] = k-1-i;
        this.elements = elements;
    }

    public boolean hasNext() {
        return hasNext;
    }

    private int inc(int[] indices, int maxIndex, int depth) throws IllegalStateException {
        if(depth == indices.length) {
            throw new IllegalStateException("The End");
        }
        if(indices[depth] < maxIndex) {
            indices[depth] = indices[depth]+1;
        } else {
            indices[depth] = inc(indices, maxIndex-1, depth+1)+1;
        }
        return indices[depth];
    }

    private boolean inc() {
        try {
            inc(indices, elements.length - 1, 0);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public Collection<T> next() {
        Collection<T> result = new ArrayList<T>(indices.length);
        for(int i=indices.length-1; i>=0; i--) {
            result.add(elements[indices[i]]);
        }
        hasNext = inc();
        return result;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}
