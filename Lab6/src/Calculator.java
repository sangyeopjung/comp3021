import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

public class Calculator {
	public static void genAndSort(int n) {
		Random rand = new Random();
		ArrayList<Number> nums = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			nums.add(new Number(new BigInteger(10, rand)));
		}
		System.out.println("Generated numbers:");
		for (Number num : nums) {
			System.out.print(num + " ");
		}
		Collections.sort(nums);
		System.out.println("\nSorted in increasing order: ");
		for (Number num : nums) {
			System.out.print(num + " ");
		}
		System.out.printf("%n%n");
	}

	public static void main(String[] args) {
		genAndSort(10);
		String line;
		Scanner reader = new Scanner(System.in);
		Parser parser = new Parser();
		try {
			System.out.println("Please enter an expression:");
			while (true) {
				System.out.print("> ");
				line = reader.nextLine();
				if (line.isEmpty()) {
					System.out.println("Goodbye!");
					break;
				}
				Expression expr;
				try {
					expr = parser.parse(line);
				} catch (Exception ex) {
					System.err.println("Invalid expression: " + ex.getMessage());
					continue;
				}
				System.out.println("expr = " + expr);
				System.out.printf("res = %s%n%n", expr.eval());
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.err.println(ex.getStackTrace());
			System.exit(-1);
		} finally {
			reader.close();
		}
	}

}
