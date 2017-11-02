/**
 * Extends the Exception class; will be thrown if an impossible square is
 * attempted to be created.
 *
 * This exception is a checked exception, because I added a throws clause in the
 * Square constructors and the compiler requires me to handle the exception in
 * the tester class. I chose a checked exception because I wanted the compiler
 * to catch any errors by the user rather than it happening at run-time.
 *
 * @author sdesai88
 * @version 11/7/17
*/
public class InvalidSquareException extends Exception {

    /**
     * Creates an InvalidSquareException by calling the constructor in the
     * Exception class (parent class).
     *
     * @param square : the name of the invalid Square
    */
    public InvalidSquareException(String square) {
        super(square);
    }
}