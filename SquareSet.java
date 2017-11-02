import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Custom implementation of a Set, called a SquareSet. Can only hold Squares
 * that can be found on a real 8x8 chess board.
 *
 * @author sdesai88
 * @version 11/7/17
*/
public class SquareSet implements Set<Square> {

    private Square[] backingArr;
    private int arrIndex;

    /**
     * Creates an instance of a SquareSet, with no elements. Initializes the
     * backing array to an empty array of length 8, and initializes the starting
     * index to 0.
    */
    public SquareSet() {
        backingArr = new Square[8];
        arrIndex = 0;
    }

    /**
     * Creates an instance of a SquareSet, populating it with the Square
     * elements in the Collection passed as a parameter. Backing array is
     * initialized to an empty array of lrngth 8, and the index is initialized
     * to size 8. However, these change after the elements in the Collection are
     * added.
     *
     * @param c : a Collection of Squares to populate the SquareSet with
    */
    public SquareSet(Collection<Square> c) {
        backingArr = new Square[8];
        arrIndex = 0;
        addAll(c);
    }

    @Override
    public boolean add(Square square) {
        if (square == null) {
            throw new NullPointerException();
        }

        try {
            Square newSquare = new Square(square.getFile(), square.getRank());
        } catch (InvalidSquareException e) {
            return false;
        }

        if (contains(square)) {
            return false;
        }

        if (arrIndex == backingArr.length) {
            Square[] tmpArr = new Square[backingArr.length];
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = backingArr[i];
            }

            backingArr = new Square[arrIndex * 2];
            for (int i = 0; i < tmpArr.length; i++) {
                backingArr[i] = tmpArr[i];
            }
        }

        backingArr[arrIndex] = square;
        arrIndex++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Square> c) {
        for (Square s : c) {
            if (s == null) {
                throw new NullPointerException();
            }

            try {
                Square newSquare = new Square(s.getFile(), s.getRank());
            } catch (InvalidSquareException e) {
                return false;
            }
        }

        int tmpIndex = arrIndex;
        for (Square s : c) {
            add(s);
        }

        return tmpIndex != arrIndex;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear()");
    }

    @Override
    public boolean contains(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Square)) {
            return false;
        }

        Square square = (Square) other;
        for (int i = 0; i < arrIndex; i++) {
            if (square.equals(backingArr[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof SquareSet)) {
            return false;
        }

        SquareSet ss = (SquareSet) other;
        if (ss.size() != this.size()) {
            return false;
        }

        if (this.containsAll(ss)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int resultHash = 0;
        for (int i = 0; i < arrIndex; i++) {
            resultHash += backingArr[i].hashCode();
        }

        return resultHash;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<Square> iterator() {
        return new SquareIterator();
    }

    @Override
    public boolean remove(Object other) {
        if (!contains(other)) {
            return false;
        }

        Square square = (Square) other;
        int indexToRemove = -1;
        for (int i = 0; i < arrIndex; i++) {
            if (backingArr[i].equals(square)) {
                indexToRemove = i;
            }
        }

        backingArr[indexToRemove] = null;
        for (int i = indexToRemove; i < arrIndex - 1; i++) {
            backingArr[i] = backingArr[i + 1];
        }

        backingArr[arrIndex - 1] = null;
        arrIndex--;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll(Collection<?> c)");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll(Collection<?> c)");
    }

    @Override
    public int size() {
        return arrIndex;
    }

    @Override
    public Object[] toArray() {
        Object[] newArr = new Object[arrIndex];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = backingArr[i];
        }

        return newArr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (arrIndex > a.length) {
            T[] toReturn = (T[]) new Square[arrIndex];
            for (int i = 0; i < toReturn.length; i++) {
                toReturn[i] = (T) backingArr[i];
            }

            return toReturn;
        }

        for (int i = 0; i < arrIndex; i++) {
            a[i] = (T) backingArr[i];
        }

        if (a.length > arrIndex) {
            a[arrIndex] = null;
        }

        return a;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < arrIndex; i++) {
            result += backingArr[i].toString();
            if (i != (arrIndex - 1)) {
                result += ", ";
            }
        }

        result += "]";
        return result;
    }

    private class SquareIterator implements Iterator<Square> {

        private int itIndex;

        public SquareIterator() {
            itIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return itIndex < arrIndex;
        }

        @Override
        public Square next() {
            if (hasNext()) {
                Square toReturn = backingArr[itIndex];
                itIndex++;
                return toReturn;
            }

            throw new NoSuchElementException("reached end of set");
        }
    }

}