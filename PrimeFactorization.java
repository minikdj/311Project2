import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeFactorization {
	//private static ArrayList<Integer> primes = new ArrayList<Integer>();
	
	public static void primeFactorization(BigDecimal num, ArrayList<Integer> primesList) {
		int maxVal = SquareRoot.sqRoot(num).intValue()+1;
		for(int i = 2; i < maxVal; i++) {
			if(num.intValue() % i == 0) {
				primesList.add(i);
				//System.out.println(i);
				BigDecimal factoredNum = new BigDecimal(num.intValue()/i);
				primeFactorization(factoredNum, primesList);
				break;
			} else if (i==maxVal-1) {
				primesList.add(num.intValue());
				//System.out.println(num.intValue());
			}	
		}		
		//return primes;
	}
	
	public static String printPrimesStr(BigDecimal number, ArrayList<Integer> primesList) {
		String solution = Integer.toString(number.intValue()) + " := ";
		for(int i = 0; i < primesList.size(); i++) {
			int tempInt = primesList.get(i);
			if(i == primesList.size()-1)
				solution += Integer.toString(tempInt);
			else
				solution += Integer.toString(tempInt) + " * ";
		}
		
		return solution;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Enter an Integer: ");
			BigDecimal number = new BigDecimal(in.next());
			ArrayList<Integer> primes = new ArrayList<Integer>();
			primeFactorization(number, primes);
			
			System.out.printf("%d := ", number.intValue());
			for(int i = 0; i < primes.size(); i++) {
				if(i == primes.size()-1)
					System.out.printf("%d\n", primes.get(i));
				else
					System.out.printf("%d * ", primes.get(i));
			}
			//System.out.println(printPrimesStr(number, primes));
			primes.clear();
		}
		//in.close();
	}
}
