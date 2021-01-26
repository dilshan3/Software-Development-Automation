package complexityCalculator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface FibonacciMethod {
	public String fibonacci(long number);
	private int wowowowo();
	public int results();
} 

//Test comment
class FibonacciMain implements FibonacciMethod {
	
	public String fibonacci(long number) {
		
		List<String> stringList = new ArrayList<String>();		
	
		if ((number == 0) || (number == 1)){// base cases
			
			return number;
		}	 
		else {
			// recursion step
			return fibonacci(number -1) + fibonacci(number -2);
		}
	}
	
	//this is the main method
	public static void main(String args[]) {
		
		
		/* basdjkasdlasd
                */	
		for(int count = 0; count <= 10; count++){
		 
			System.out.println("Fibonacci if " +  count + " is " + fibonacci(count));
		}
		
		HashMap<String, String> map = new HashMap<String, String>();

		if (a && (y == b)) {
		    if (y == x) {
				while (true) {
					if (x++ < 20) {
						break;
					}
				}
		    } else if (y == t && !d) {
				System.out.println(x = a ? y : x);
		    } else {
				System.out.println(x = a ? y : x = a ? y : x);
			}
		}
	}
}