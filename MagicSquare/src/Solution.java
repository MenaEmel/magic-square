import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int[][] arr = new int[3][3];

	for (int row = 0; row < 3; row++)
	    for (int col = 0; col < 3; col++)
		arr[row][col] = input.nextInt();

	input.close();
	System.out.println(calculateMagicSquareCost(arr));
    }

    public static int calculateMagicSquareCost(int[][] arr) {
	int minCost = 100;
	for(int i = 1; i <= 8; i++) {
	    int[][] pattern = getPattern(i);
	    int originalToPatternCost = getDifference(arr, pattern);
	    if(originalToPatternCost < minCost) {
		minCost = originalToPatternCost;
	    }
	}
	return minCost;
    }
    
    public static int[][] getPattern(int patternNumber) {
	int[][] startPattern = new int[][] {
	    		{8, 1, 6},
			{3, 5, 7},
			{4, 9, 2}};
	
	for(int i = 1; i <= patternNumber; i++) {
	   switch(i) {
	   	case 1:
	   	    break;
	   	    
	   	case 2:
	   	case 4:
	   	case 6:
	   	case 8:
	   	    startPattern = reflect(startPattern);
	   	    break;
	   	    
	   	case 3:
	   	case 7:
	   	    startPattern = rotate(startPattern);
	   	    break;
	   	    
	   	case 5:
	   	    startPattern = reflectAcrossDiagonal(startPattern);
	   	    break;
	   }
	}
	
	return startPattern;
    }
    
    public static int[][] reflectAcrossDiagonal(int[][] original) {
	// swap every [x, y] with [y, x]
	int tmp = 0;
	for(int row = 0; row < 3; row++) {
	    for(int col = row + 1; col < 3; col++) {
		tmp = original[row][col];
		original[row][col] = original[col][row];
		original[col][row] = tmp;
	    }
	}
	
	return original;
    }
    
    public static int[][] rotate(int[][] original) {
	// swap first and last rows then reflect
	int tmp = 0;
	for(int col = 0; col < 3; col++) {
	    tmp = original[0][col];
	    original[0][col] = original[2][col];
	    original[2][col] = tmp;
	}
	
	original = reflect(original);
	
	return original;
    }
    
    public static int[][] reflect(int[][] original) {
	int tmp = 0;
	for(int row = 0; row < 3; row++) {
	    // swap elements at at cols 0 and 2
	    tmp = original[row][0];
	    original[row][0] = original[row][2];
	    original[row][2] = tmp;
	}
	return original;
    }
    
    public static int getDifference(int[][] original, int[][] pattern) {
	int cost = 0;
	for(int row = 0; row < 3; row++) {
	    for(int col = 0; col < 3; col++) {
		cost += Math.abs(original[row][col] - pattern[row][col]);
	    }
	}
	return cost;
    }
    
    public static <T> void showList(ArrayList<T> lst) {
	for (T element : lst) {
	    System.out.print(element + " ");
	}
	System.out.println();
    }
    
    public static void showArr(int[][] arr) {
	for(int i = 0; i < 3; i++) {
	    for(int j = 0; j < 3; j++) {
		System.out.print(arr[i][j] + " ");
	    }
	    System.out.println();
	}		
    }
}
