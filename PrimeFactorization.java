import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Scanner;

public class PrimeFactorization {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an Integer: ");
		BigDecimal number = new BigDecimal(in.next());
		System.out.println(number);
		
		BigDecimal answer = SquareRoot.sqRoot(number);
		System.out.println(answer);
	}
}
