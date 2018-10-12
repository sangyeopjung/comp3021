import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTest {
    private Parser parser = new Parser();

    @Test
    void testParser() throws IOException {
        assertEquals(parser.parse("\t  \t  +100013\t\t ").toString(), "100013");
        assertEquals(parser.parse("\t  \t-123  \t").toString(), "-123");
        assertEquals(parser.parse("(+ 	(- (* 	(/ 100 -2) 3) 4)     5)").toString(), "(+ (- (* (/ 100 -2) 3) 4) 5)");
        assertEquals(parser.parse("(+ (- (* 1 2) (/ 10 2)) 24)").eval(), new BigInteger("21"));
    }

    @Test
    void testAdd() throws IOException {
        assertEquals(parser.parse("(+ 100)").eval(), new BigInteger("100"));
        assertEquals(parser.parse("(+ 1 2 3)").eval(), new BigInteger("6"));
        assertEquals(parser.parse("(+ 10 -20)").eval(), new BigInteger("-10"));
    }

    @Test
    void testSub() throws IOException {
        assertEquals(parser.parse("(- 11)").eval(), new BigInteger("-11"));
        assertEquals(parser.parse("(- 100 22)").eval(), new BigInteger("78"));
        assertEquals(parser.parse("(- 10 2 -4 5)").eval(), new BigInteger("7"));
    }

    @Test
    void testMult() throws IOException {
        assertEquals(parser.parse("(* 10)").eval(), new BigInteger("10"));
        assertEquals(parser.parse("(* 3 4)").eval(), new BigInteger("12"));
        assertEquals(parser.parse("(* 3 -4 5)").eval(), new BigInteger("-60"));
    }

    @Test
    void testDiv() throws IOException {
        assertEquals(parser.parse("(/ 10 3)").eval(), new BigInteger("3"));
        assertEquals(parser.parse("(/ 1)").eval(), BigInteger.ONE);
        assertEquals(parser.parse("(/ 2)").eval(), BigInteger.ZERO);
        assertEquals(parser.parse("(/ 100 -2 10)").eval(), new BigInteger("-5"));
    }

    @Test
    public void testDivByZero() throws IOException {
        assertThrows(ArithmeticException.class, () -> {
            parser.parse("(/ 100 -2 0 3)").eval();
        });
    }

    @Test
    void testExp() throws IOException {
        assertEquals(parser.parse("(^ 2 10)").eval(), new BigInteger("1024"));
        assertEquals(parser.parse("(^ -3 4)").eval(), new BigInteger("81"));
    }
}
