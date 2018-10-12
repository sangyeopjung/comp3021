import java.math.BigInteger;
import java.util.ArrayList;


/**
 * TODO 4: Define Multiplication operation
 * It should be a subclass of Operation
 * Hint: refer to the Addition class to see how to implement this one. It's pretty similar.
 * Hint: Use the constant BigInteger.ONE and the method BigInteger.multiply(BigInteger) to implement the eval method
 */
public class Multiplication extends Operation {

    public Multiplication(ArrayList<Expression> operands) {
        super("*", operands);
    }

    /**
     * TODO 4.1
     *
     * @return The BigInteger result after evaluating the multiplication operation
     */
    public BigInteger eval() {
        BigInteger mult = BigInteger.ONE;
        for (Expression e : operands) {
            mult = mult.multiply(e.eval());
        }
        return mult;
    }
}
