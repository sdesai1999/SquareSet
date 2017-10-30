public class Tester {
    public static void main(String[] args) throws InvalidSquareException {
        SquareSet set = new SquareSet();
        set.add(new Square("a1"));
        set.add(new Square("a1"));
        set.add(new Square("b3"));
        set.add(new Square('c', '8'));
        set.add(new Square("h1"));

        System.out.println("size " + set.size() + ": " + set);
    }
}