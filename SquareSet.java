import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class SquareSet implements Set<Square> {

    private Square[] backingArr;
    private int arrIndex;

    public SquareSet() {
        backingArr = new Square[8];
        arrIndex = 0;
    }

    @Override
    public boolean add(Square square) {
        if (square == null) {
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
        for (Square s : backingArr) {
            if (s != null) {
                return false;
            }
        }

        return true;
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
            newArr[i] = (Object) backingArr[i];
        }

        return newArr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
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
        private int end;

        public SquareIterator() {
            this.itIndex = 0;
            this.end = arrIndex;
        }

        @Override
        public boolean hasNext() {
            return itIndex < end;
        }

        @Override
        public Square next() {
            if (hasNext()) {
                Square toReturn = backingArr[itIndex];
                itIndex++;
                return toReturn;
            }

            throw new NoSuchElementException("no more elements");

        }
    }

}


















