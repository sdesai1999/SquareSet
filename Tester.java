import java.util.ArrayList;
import java.util.Iterator;

public class Tester {
    public static void main(String[] args) throws InvalidSquareException {
        SquareSet set = new SquareSet();
        set.add(new Square("a1"));
        set.add(new Square("a1"));
        set.add(new Square("b3"));
        set.add(new Square('c', '8'));
        set.add(new Square("h1"));

        printSet(set);

        Iterator<Square> setIter = set.iterator();

        while (setIter.hasNext()) {
            System.out.print(setIter.next() + " ");
        }

        System.out.println();

        SquareSet set2 = new SquareSet();
        set2.add(new Square('a', '1'));
        set2.add(new Square("h1"));
        set2.add(new Square('c', '8'));
        set2.add(new Square("b3"));

        System.out.println(set.equals(set2));
        System.out.println(set.hashCode() == set2.hashCode());

        ArrayList<Square> list = new ArrayList<>();
        list.add(new Square("b3"));
        list.add(new Square("a1"));
        list.add(new Square("c8"));

        System.out.println("set containsAll list: " + set.containsAll(list));
        System.out.println("set2 containsAll list: " + set2.containsAll(list));
        System.out.println(list.containsAll(set2));

        ArrayList<Square> toAdd = new ArrayList<>();
        toAdd.add(new Square("a1"));
        toAdd.add(new Square("a2"));
        toAdd.add(new Square("a3"));
        System.out.println(set.add(new Square("c7")));
        toAdd.add(new Square("a4"));
        toAdd.add(new Square("a5"));
        toAdd.add(new Square("a6"));
        System.out.println(set.addAll(toAdd));
        printSet(set);

        setIter = set.iterator();
        while (setIter.hasNext()) {
            System.out.print(setIter.next() + " ");
        }

        System.out.println();
    }

    public static void printSet(SquareSet s) {
        System.out.println("size " + s.size() + ": " + s);
    }
}














