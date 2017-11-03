/**
 * Extends the RuntimeException class; will be thrown if an impossible square is
 * attempted to be created.
 *
 * @author sdesai88
 * @version 11/7/17
*/
public class InvalidSquareException extends RuntimeException {

    /**
     * Creates an InvalidSquareException by calling the constructor in the
     * RuntimeException class (parent class).
     *
     * @param square : the name of the invalid Square
    */
    public InvalidSquareException(String square) {
        super(square);
    }
}