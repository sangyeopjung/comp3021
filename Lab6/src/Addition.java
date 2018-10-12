import java.math.BigInteger;
import java.util.ArrayList;

public class Addition extends Operation {

	public Addition(ArrayList<Expression> operands) {
		super("+", operands);
	}

	public BigInteger eval() {
		BigInteger sum = BigInteger.ZERO;
		for (Expression e : operands) {
			sum = sum.add(e.eval());
		}
		return sum;
	}

}
