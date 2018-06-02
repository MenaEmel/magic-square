import java.util.ArrayList;
import java.util.Scanner;

public class Solution2 {

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
	int cost = 0;
	while (!isMagicSquare(arr)) {   
	    System.out.println("Not Magic Square");
	    
	    // 1. Find if there are any duplicate numbers
	    ArrayList<Integer> duplicates = getDuplicates(arr);
	    ArrayList<Integer> missing = getMissings(arr);

	    // 2. Process the first duplicate
	    ArrayList<Integer> rowsSum = getRowsSum(arr);
	    ArrayList<Integer> colsSum = getColsSum(arr);
	    ArrayList<Integer> diagonalsSum = getDiagonalsSum(arr);

	    int currentDuplicate = duplicates.get(0);
	    
	    /**
	     * DEBUGGING POINT
	     */
	    System.out.println("Duplicates: ");
	    showList(duplicates);
	    System.out.println("=====================");
	    System.out.println("Missings: ");
	    showList(missing);
	    System.out.println("=====================");
	    System.out.println("Rows sums: ");
	    showList(rowsSum);
	    System.out.println("=====================");
	    System.out.println("Cols sums: ");
	    showList(colsSum);
	    System.out.println("=====================");
	    System.out.println("Diagonals Sums: ");
	    showList(diagonalsSum);
	    System.out.println("=====================");
	    System.out.println("Current Duplicate: " + currentDuplicate);
	    System.out.println("=====================\n\n");
	    
	    boolean found = false;
	    // int minCostForCurrentDuplicate = 100; 	Experimental

	    for (int row = 0; row < 3; row++) {
		for (int col = 0; col < 3; col++) {
		    if (!found && arr[row][col] == currentDuplicate &&
			!isValidPoint(arr, row, col, rowsSum, colsSum, diagonalsSum)) {
			
			System.out.println("Duplicate coordinates = [" + row + ", " + col + "]");
			
			found = true;
		
			int minCost = ((15 - rowsSum.get(row)) < (15 - colsSum.get(col))) ? (15 - rowsSum.get(row)) : (15 - colsSum.get(col));
			int maxCost = ((15 - rowsSum.get(row)) > (15 - colsSum.get(col))) ? (15 - rowsSum.get(row)) : (15 - colsSum.get(col));
			
			System.out.println("Min Cost for " + currentDuplicate + " located at [" + row + ", " + col + "] = " + minCost);
			System.out.println("Max Cost for " + currentDuplicate + " located at [" + row + ", " + col + "] = " + maxCost);
			/**
			 * WARNING: There could be a wrong here
			 */
			if(missing.contains(currentDuplicate + minCost)) {
			    arr[row][col] = currentDuplicate + minCost;
			    cost += Math.abs(minCost);
			} else if(missing.contains(currentDuplicate + maxCost)) {
			    arr[row][col] = currentDuplicate + maxCost;
			    cost += Math.abs(maxCost);
			} else {
			    arr[row][col] = currentDuplicate + ((Math.abs(minCost) < Math.abs(maxCost)) ? minCost : maxCost);
			    cost += ((Math.abs(minCost) < Math.abs(maxCost)) ? Math.abs(minCost) : Math.abs(maxCost));
			}
			
			break;
		    }
		}
		
		if(found)
		    break;
	    }
	    
	    // Show new arr
	    System.out.println("=================================");
	    System.out.println("Arr now: ");
	    showArr(arr);
	    System.out.println("=================================\n\n");
	    // Reset array lists used
	    duplicates.clear();
	    missing.clear();
	    rowsSum.clear();
	    colsSum.clear();
	    diagonalsSum.clear();
	}

	return cost;
    }

    public static boolean isValidPoint (int[][] arr, int row, int col,
	    				ArrayList<Integer> rowsSum,
	    				ArrayList<Integer> colsSum, 
	    				ArrayList<Integer> diagonalsSum) {
	return (rowsSum.get(row) == 15 || 
	   colsSum.get(col) == 15 || 
	   (isEdge(row, col) && getCorrectDiagonalSum(diagonalsSum, row, col) == 15) ||
	   (row == 1 && col == 1 && (diagonalsSum.get(0) == 15 || diagonalsSum.get(1) == 15)));
    }

    public static int getCorrectDiagonalSum(ArrayList<Integer> diagonals, int row, int col) {
	if ((row == 0 && col == 0) || (row == 2 && col == 2)) {
	    return diagonals.get(0);
	} else {
	    return diagonals.get(1);
	}
    }

    public static boolean isEdge(int row, int col) {
	return ((row == 0 && col == 0) || (row == 0 && col == 2) || (row == 2 && col == 0) || (row == 2 && col == 2));
    }

    public static boolean isMagicSquare(int[][] arr) {
	ArrayList<Integer> rows = getRowsSum(arr);
	ArrayList<Integer> cols = getColsSum(arr);
	ArrayList<Integer> diag = getDiagonalsSum(arr);

	return (rows.get(0) == rows.get(1) && rows.get(0) == rows.get(2) && rows.get(0) == 15
		&& cols.get(0) == cols.get(1) && cols.get(0) == cols.get(2) && cols.get(0) == 15
		&& diag.get(0) == diag.get(1) && diag.get(0) == 15 && getDuplicates(arr).size() == 0);
    }

    public static ArrayList<Integer> getRowsSum(int[][] arr) {
	ArrayList<Integer> res = new ArrayList<>();

	for (int row = 0; row < 3; row++) {
	    int currentSum = 0;

	    for (int col = 0; col < 3; col++) {
		currentSum += arr[row][col];
	    }

	    res.add(currentSum);
	}

	return res;
    }

    public static ArrayList<Integer> getColsSum(int[][] arr) {
	ArrayList<Integer> res = new ArrayList<>();

	for (int col = 0; col < 3; col++) {
	    int currentSum = 0;

	    for (int row = 0; row < 3; row++) {
		currentSum += arr[row][col];
	    }

	    res.add(currentSum);
	}

	return res;
    }

    public static ArrayList<Integer> getDiagonalsSum(int[][] arr) {
	ArrayList<Integer> res = new ArrayList<>();
	res.add(arr[0][0] + arr[1][1] + arr[2][2]);
	res.add(arr[0][2] + arr[1][1] + arr[2][0]);
	return res;
    }

    public static ArrayList<Integer> getDuplicates(int[][] arr) {
	ArrayList<Integer> res = new ArrayList<>();
	int counter = 1;

	while (counter <= 9) {
	    int found = 0;

	    for (int row = 0; row < 3; row++) {
		for (int col = 0; col < 3; col++) {
		    if (arr[row][col] == counter) {
			found++;
		    }
		}
	    }

	    if (found > 1)
		res.add(counter);

	    counter++;
	}

	return res;
    }

    public static ArrayList<Integer> getMissings(int[][] arr) {
	ArrayList<Integer> res = new ArrayList<>();
	int counter = 1;

	while (counter <= 9) {
	    int found = 0;

	    for (int row = 0; row < 3; row++) {
		for (int col = 0; col < 3; col++) {
		    if (arr[row][col] == counter)
			found++;
		}
	    }

	    if (found == 0)
		res.add(counter);

	    counter++;
	}

	return res;
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
