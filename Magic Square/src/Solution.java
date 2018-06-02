/*import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
	// Get the input
	Scanner input = new Scanner(System.in);
	int q = input.nextInt();
	
	ArrayList<String> numbers = new ArrayList<>();
	for(int i = 0; i < q; i++) {
	    numbers.add(input.next());
	}
	input.close();
	
	// Start processing
	for(String num : numbers) {
	    long res = isBeautiful(num);
	    if(res == -1)
		System.out.println("NO");
	    else
		System.out.println("YES " + res);
	}
    }
 
    public static long isBeautiful(String numToTest) {
	long res = -1;
	
	if(numToTest.length() <= 1)
	    return res;
	
	for(int startLength = 1; startLength <= numToTest.length() / 2; startLength++) {
	    long firstNumber = Long.parseLong(numToTest.substring(0, startLength));
	
	    for(int i = startLength, counter = 1; i <= numToTest.length() - startLength; counter++) {
		long expected = firstNumber + counter;
		if(numToTest.indexOf(String.valueOf(expected)) != i) {
		    res = -1;
		    break;
		} else {
		    res = firstNumber;
		    i += String.valueOf(expected).length();
		}
	    }
	    
	    if(res != -1)
		break;
	}
	
	return res;
    }
    
    public static <T> void showList(ArrayList<T> list) {
	System.out.println("The following list contents are: ");
	for(T element : list) {
	    System.out.println(element);
	}
    }
}*/