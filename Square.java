/**
 * Represents a square on an 8x8 chessboard.
 *
 * @author sdesai88
 * @version 11/7/17
*/
public class Square {

    private char file;
    private char rank;
    private String name;

    /**
     * Creates a Square with a file and a rank.
     *
     * @param file : the file (column) of the Square
     * @param rank : the rank (row) of the Square
    */
    public Square(char file, char rank) throws InvalidSquareException {
        this(new String("" + file + rank));
    }

    /**
     * Creates a Square with a name.
     *
     * @param name : the full name of the Square (file and rank)
    */
    public Square(String name) throws InvalidSquareException {
        this.name = name;
        if (this.name.length() < 2) {
            throw new InvalidSquareException(name);
        }
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
        if (!(this.isValidSquare())) {
            throw new InvalidSquareException(name);
        }
    }

    /**
     * @return the name of the Square
    */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Checks if two Squares are the same; they are the same if their files and
     * ranks are the same.
     *
     * @param other : any Object
     * @return true if the Squares are the same, false otherwise
    */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof Square)) {
            return false;
        }

        Square newSq = (Square) other;
        if (this.file == newSq.getFile() && this.rank == newSq.getRank()) {
            return true;
        }

        return false;
    }


    /**
     * The hashCode of a Square is determined by its file and rank
     *
     * @return the hashCode of the Square instance
    */
    @Override
    public int hashCode() {
        int result = 29;
        int fileCode = (int) file;
        int rankCode = (int) rank;
        result = 31 * result + fileCode;
        result = 31 * result + rankCode;
        return result;
    }

    /**
     * Checks if the Square is a proper one that can be found on a chessboard.
     * i.e. file is between a-h and rank is between 1-8.
     *
     * @return true if the Square is valid, false otherwise
    */
    public boolean isValidSquare() {
        if (this.name.length() > 2) {
            return false;
        }

        char fl = this.file;
        char rk = this.rank;

        if ((fl < 'a') || (fl > 'h') || (rk < '1') || (rk > '8')) {
            return false;
        }

        return true;
    }

    /**
     * @return the file of the Square
    */
    public char getFile() {
        return this.file;
    }

    /**
     * @return the rank of the Square
    */
    public char getRank() {
        return this.rank;
    }
}