import java.math.BigInteger;


/**
 * TODO 2: Define the number class
 * The number class is a subtype of expression (since a single number is technically an expression).
 * It also implements the Comparable interface.
 */
public class Number extends Expression implements Comparable {
    private BigInteger val;

    public Number(BigInteger val) {
        super();
        this.val = val;
    }

    /**
     * TODO 2.1
     * Hint: BigInteger provides its own compareTo function that you can use.
     *
     * @param other The other Number object
     * @return The comparison value.
     */
    @Override
    public int compareTo(Object other) {
        return val.compareTo(((Number)other).val);
    }

    @Override
    public BigInteger eval() {
        return val;
    }

    /**
     * TODO 2.2
     *
     * @return the value as a string.
     */
    @Override
    public String toString() {
        return val.toString();
    }
}