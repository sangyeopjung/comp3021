import java.math.BigInteger;
import java.util.ArrayList;


/**
 * TODO 7: Define Exponent operation
 * It should be a subclass of Operation
 * Hint: BigInteger.pow(int)
 */

public class Exponent extends Operation {

    public Exponent(ArrayList<Expression> operands) {
        super("^", operands);
    }

    /**
     * TODO 7.1
     *
     * @return The result of the exponentiation
     */
    public BigInteger eval() {
        return  operands.get(0).eval().pow(Integer.parseInt(operands.get(1).eval().toString()));
    }
}