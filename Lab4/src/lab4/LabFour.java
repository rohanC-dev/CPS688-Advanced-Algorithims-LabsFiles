
//////////////////////////////////////////////////
/*
Write your solution for Lab 4.
*/
//////////////////////////////////////////////////


package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class LabFour{
	
	public static void main(String[] args) {
		
		// Input for Lab 3 will be read from the file "input_lab3.txt".
		try { 
			FileReader freader = new FileReader(new File("input_lab4.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			
			// Russian Peasant Multiplication Problem.
			// First row of the input file has 
			// 3 pairs of positive integers to be multiplied
			
			System.out.print("--- Q1 -------\n");
			row = readInput.readLine();
			String[] pairs = row.split(" ",6);
			for (int i=0; i<6;) {
				int m = Integer.parseInt(pairs[i++]);
				int n = Integer.parseInt(pairs[i++]);
				System.out.println("Russian Peasant Multiplication of "+m+" and "+n+".");
				System.out.println(+m+" X "+n+" = "+ rpMultiplication(m,n));
				System.out.print("--------------\n");
			}
			
			// Horner’s Rule for Polynomial Evaluation.
			// Second row of the input file row has 3 6-tuples 
			// First value is x, and the other 5 powers of x
			
			System.out.print("--- Q2 -------\n");
			row = readInput.readLine();
			String[] values = row.split(" ",18);
			for (int i=0; i<18;) {
				int x = Integer.parseInt(values[i++]);
				String output = "For x="+x+": Solve p(x)=";
				int[] coefficients = new int [5];
				for (int j=0; j<5; j++) {
					coefficients[j] = Integer.parseInt(values[i++]);
					if (j<4)
						output = output + coefficients[j]+"(X^"+(4-j)+")+";
				}
				System.out.println(output+coefficients[4]+"(X^0).");
				System.out.println("p("+x+") = "+polyEvaluation(x, coefficients));
			}
			
			// Closest Pair by Divide-and-Conquer.
			// Third row of the input file row has 3 arrays
			// For each array find the closest Pair
						
			System.out.print("--- Q3 -------\n");
			int n1=6;
			int n2=8;
			int n3=10;
			row = readInput.readLine();
			String[] numbers = row.split(" ",n1+n2+n3);
			for (int i=0; i<n1+n2+n3;) {
				int[] array_1 = new int [n1];
				String out = "For array [ ";
				for (int j=0; j<n1; j++) {
					array_1[j] = Integer.parseInt(numbers[i++]);
					out = out + array_1[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(5,array_1));
				int[] array_2 = new int [n2];
				out = "For array [ ";
				for (int j=0; j<n2; j++) {
					array_2[j] = Integer.parseInt(numbers[i++]);
					out = out + array_2[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(8,array_2));
				int[] array_3 = new int [n3];
				out = "For array [ ";
				for (int j=0; j<n3; j++) {
					array_3[j] = Integer.parseInt(numbers[i++]);
					out = out + array_3[j] +" ";
				}
				System.out.println(out+"]:");
				System.out.println("Least distance between two points = "+ closestPair(10,array_3));
			}
			System.out.print("--------------\n");
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }

		
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
Do not make any other changes in the code.
*/
//////////////////////////////////////////////////
	
	// Solution for Question 1.
	// Returns the result of multiplication of integers m and n.
	
	public static int rpMultiplication(int m, int n) {
		// Write your code here
		return -1;
	}
	
	
	// Solution for Question 2.
	// Apply Horner’s Rule to evaluate Polynomial for x 
	// The coefficients of the polynomial are given in the array "coefficients"
	
	public static int polyEvaluation(int x, int[] coefficients) {
		// Write your code here
		return -1;
	}
	
	// Solution for Question 3.
	// Find the closest pair by distance in the
	// input array of numbers "input_array" of size "size"
		
	public static double closestPair(int size, int[] input_array) {
		// Write your code here
		return -1;
	}
}

/*
 * Sample output:
 * 
--- Q1 -------
Russian Peasant Multiplication of 18 and 85.
--------------
18 X 55 = 1530
18	85
9	170
4 	340
2 	680
1 	1360
--------------
Russian Peasant Multiplication of 85 and 18.
--------------
85 X 18 = 1530
85	18
42	36
21 	72
10 	144
5 	288
2	576
1	1152
--------------
Russian Peasant Multiplication of Y and Z.
--------------
Y X Z = xxxx
Y	Z
xx  x
xx  x
x   x
x   x
--------------
--- Q2 -------
For x=5: Solve p(x)=0(X^4)+1(X^3)+2(X^2)+3(X^1)+4(X^0).
p(5) = -1
For x=9: Solve p(x)=4(X^4)+3(X^3)+2(X^2)+1(X^1)+0(X^0).
p(9) = -1
For x=6: Solve p(x)=0(X^4)+2(X^3)+4(X^2)+3(X^1)+1(X^0).
p(6) = -1
--- Q3 -------
For array [ 1 12 3 24 55 56]:
Least distance between two points = 2.56
Closest pair = (1,12),(55,56)
For array [ 11 2 3 6 8 9 4 555 ]:
Least distance between two points = 1.05
Closest pair = (2,3),(6,8)
For array [ 23 7 4 10 20 0 16 100 95 33 ]:
Least distance between two points = 3.444
Closest pair = (4,7),(0,16)
--------------
 */