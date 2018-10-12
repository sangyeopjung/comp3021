import java.math.BigInteger;
import java.util.ArrayList;

/**
 * TODO 5: Define Subtraction operation
 * It should be a subclass of Operation
 * Hint: refer to the Addition class to see how to implement this one.
 * Hint: BigInteger.negate() and BigInteger.subtract(BigInteger) are useful for implementing eval()
 * Note: If the number of operands is 1, return the negation. Otherwise, subtract the rest of the operands from
 * the first operand
 */

public class Subtraction extends Operation {

    public Subtraction(ArrayList<Expression> operands) {
        super("-", operands);
    }

    /**
     * TODO 5.1
     *
     * @return The BigInteger result after evaluating the subtraction operation
     */
    public BigInteger eval() {
        if (operands.size() < 1) {
            return BigInteger.ZERO;
        }

        BigInteger neg = operands.get(0).eval();

        if (operands.size() == 1) {
            return neg.negate();
        }

        for (int i = 1; i < operands.size(); i++) {
            neg = neg.subtract(operands.get(i).eval());
        }
        return neg;
    }

}
