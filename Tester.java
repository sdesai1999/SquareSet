import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Tester {
    public static void main(String[] args) {
        SquareSet set1 = new SquareSet();
        Iterator<Square> set1It = set1.iterator();
        set1.add(new Square("a2"));
        set1.add(new Square('b', '7'));
        set1.add(new Square("c1"));
        printSet(set1);
        set1.remove(new Square("b7"));
        printSet(set1);
        while (set1It.hasNext()) {
            System.out.println(set1It.next());
        }

        Collection<Square> coll2 = new ArrayList<>();
        coll2.add(new Square("g7"));
        coll2.add(new Square("h1"));
        coll2.add(new Square("a6"));
        coll2.add(new Square("g2"));
        coll2.add(new Square('g', '7'));
        coll2.add(new Square("f6"));
        coll2.add(new Square("e2"));
        coll2.add(new Square("b8"));
        coll2.add(new Square("a1"));
        coll2.add(new Square("h8"));

        SquareSet set2 = new SquareSet(coll2);
        printSet(set2);
        System.out.println(set2.size());

        ArrayList<Square> arr2 = new ArrayList<>();
        arr2.add(new Square("a7"));
        arr2.add(new Square('a', '8'));
        set2.addAll(arr2);
        printSet(set2);
        set2.addAll(set1);
        printSet(set2);
        System.out.println(set2.isEmpty());
        System.out.println(set2.contains(new Square("g7")));
        coll2.add(new Square("d8"));
        System.out.println(set2.containsAll(coll2));
        coll2.remove(new Square("d8"));

        SquareSet set3 = new SquareSet();
        set3.add(new Square("c1"));
        set3.add(new Square("a2"));
        System.out.println(set3.equals(set1));
        System.out.println(set3.hashCode() == set1.hashCode());

        Object[] objArr = set2.toArray();
        for (Object o : objArr) {
            System.out.print(o + " ");
        }
        System.out.println();

        Object[] objArr2 = new Object[0];
        objArr2 = set2.toArray(objArr2);
        for (Object o : objArr2) {
            System.out.print(o + " ");
        }
        System.out.println();

        Square[] sqArr = {new Square("a1"), new Square("a2"), new Square("a3"),
                          new Square("a4"), new Square("a5"), new Square("a6"),
                          new Square("a7"), new Square("a8"), new Square("b1"),
                          new Square("b2"), new Square("b3"), new Square("b4")};

        sqArr = set1.toArray(sqArr);

        for (Square s : sqArr) {
            System.out.print(s + " ");
        }
        System.out.println();

        Object[] newArr = new Square[0];
        newArr = set2.toArray(newArr);
        for (Object o : newArr) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    public static void printSet(SquareSet s) {
        System.out.println("size " + s.size() + ": " + s);
    }
}