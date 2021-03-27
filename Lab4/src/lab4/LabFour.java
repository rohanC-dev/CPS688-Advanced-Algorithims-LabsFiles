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
import java.util.ArrayList;

public class LabFour {

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
            String[] pairs = row.split(" ", 6);
            for (int i = 0; i < 6;) {
                int m = Integer.parseInt(pairs[i++]);
                int n = Integer.parseInt(pairs[i++]);
                System.out.println("Russian Peasant Multiplication of " + m + " and " + n + ".");
                System.out.println(+m + " X " + n + " = " + rpMultiplication(m, n));
                System.out.print("--------------\n");
            }

            // Horner’s Rule for Polynomial Evaluation.
            // Second row of the input file row has 3 6-tuples 
            // First value is x, and the other 5 powers of x
            System.out.print("--- Q2 -------\n");
            row = readInput.readLine();
            String[] values = row.split(" ", 18);
            for (int i = 0; i < 18;) {
                int x = Integer.parseInt(values[i++]);
                String output = "For x=" + x + ": Solve p(x)=";
                int[] coefficients = new int[5];
                for (int j = 0; j < 5; j++) {
                    coefficients[j] = Integer.parseInt(values[i++]);
                    if (j < 4) {
                        output = output + coefficients[j] + "(X^" + (4 - j) + ")+";
                    }
                }
                System.out.println(output + coefficients[4] + "(X^0).");
                System.out.println("p(" + x + ") = " + polyEvaluation(x, coefficients));
            }

            // Closest Pair by Divide-and-Conquer.
            // Third row of the input file row has 3 arrays
            // For each array find the closest Pair
            System.out.print("--- Q3 -------\n");
            int n1 = 6;
            int n2 = 8;
            int n3 = 10;
            row = readInput.readLine();
            String[] numbers = row.split(" ", n1 + n2 + n3);
            for (int i = 0; i < n1 + n2 + n3;) {
                int[] array_1 = new int[n1];
                String out = "For array [ ";
                for (int j = 0; j < n1; j++) {
                    array_1[j] = Integer.parseInt(numbers[i++]);
                    out = out + array_1[j] + " ";
                }
                System.out.println(out + "]:");
                System.out.println("Least distance between two points = " + closestPair(5, array_1));
                int[] array_2 = new int[n2];
                out = "For array [ ";
                for (int j = 0; j < n2; j++) {
                    array_2[j] = Integer.parseInt(numbers[i++]);
                    out = out + array_2[j] + " ";
                }
                System.out.println(out + "]:");
                System.out.println("Least distance between two points = " + closestPair(8, array_2));
                int[] array_3 = new int[n3];
                out = "For array [ ";
                for (int j = 0; j < n3; j++) {
                    array_3[j] = Integer.parseInt(numbers[i++]);
                    out = out + array_3[j] + " ";
                }
                System.out.println(out + "]:");
                System.out.println("Least distance between two points = " + closestPair(10, array_3));
            }
            System.out.print("--------------\n");
            freader.close();
            readInput.close();
        } catch (IOException e) {
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
        ArrayList<Integer> nRows = new ArrayList<Integer>();
        ArrayList<Integer> mRows = new ArrayList<Integer>();

        nRows.add(n);
        mRows.add(m);
        while (n != 1) {
            if (n % 2 == 0) {
                // n is even
                n = n / 2;
                m = 2 * m;
                nRows.add(n);
                mRows.add(m);
            } else {
                // n is odd
                n = (n - 1) / 2;
                m = 2 * m; // m = 2*m + m?
                nRows.add(n);
                mRows.add(m);
            }
        }

        for (int i = 0; i < nRows.size(); i++) {
            System.out.println(nRows.get(i) + ", " + mRows.get(i));
        }
        for (int i = 0; i < nRows.size(); i++) {
            if (nRows.get(i) % 2 == 0) {
                nRows.remove(i);
                mRows.remove(i);
                i = 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < mRows.size(); i++) {
            sum = sum + mRows.get(i);
        }

        return sum;
    }

    // Solution for Question 2.
    // Apply Horner’s Rule to evaluate Polynomial for x 
    // The coefficients of the polynomial are given in the array "coefficients"
    public static int polyEvaluation(int x, int[] coefficients) {
        // Write your code here
        int evalutation = coefficients[0];
        for (int i = 1; i < coefficients.length; i++) {
            evalutation = evalutation * x + coefficients[i];
        }
        return evalutation;
    }

    // Solution for Question 3.
    // Find the closest pair by distance in the
    // input array of numbers "input_array" of size "size"
    public static double closestPair(int size, int[] input_array) {
        // Write your code here

        // transforming input array into 2d array of points
        int[][] points = new int[input_array.length / 2][2];

        int xVal = 0;
        for (int i = 0; i < input_array.length / 2; i++) {
            points[i][0] = input_array[xVal];
            xVal = xVal + 2;

        }
        int yVal = 1;
        for (int i = 0; i < input_array.length / 2; i++) {
            points[i][1] = input_array[yVal];
            yVal = yVal + 2;
        }

        // sort all the points in the array by x-cordinate (bubble sort)
        bubbleSortX(points);
        
        double closestPair = closestPairUtil(points, points.length);
        return closestPair;
    }
    
    public static double closestPairUtil(int[][] points, int num_points){
        if(num_points <= 3){
            
            return closestPairUtil2(points, num_points);
        }
        int midCord = num_points/2;
        int[] midPoint = points[midCord];
        double dL = closestPairUtil(generateLeftPoints(points, midCord), midCord);
        double dR = closestPairUtil(generateRightPoints(points, midCord), num_points-midCord);
        
        double minD;
        if(dL < dR){
            minD = dL;
        }else{
            minD = dR;
        }
        int j = 0;
        int[][] verticalStrip = new int[points.length][2];
        for(int i = 0; i < num_points; i++){
            if(Math.abs(points[i][0] - midPoint[0]) < minD){
                verticalStrip[j] = points[i];
                j++;
            }
        }
            
        
        return minD < stripClosestPair(verticalStrip, j, minD)? minD: stripClosestPair(verticalStrip, j, minD);
    }
    
    public static double stripClosestPair(int[][] verticalStrip, int size, double minD){
        double min = minD;
        bubbleSortY(verticalStrip);
        for(int i = 0; i < size; i++){
            for(int j = i + 1;  j < size && (verticalStrip[j][1]- verticalStrip[i][1]) < min; j++){
                if(calcDistance(verticalStrip[i][0], verticalStrip[i][1], verticalStrip[j][0], verticalStrip[j][1]) < min){
                    min = calcDistance(verticalStrip[i][0], verticalStrip[i][1], verticalStrip[j][0], verticalStrip[j][1]);
                }
            }
        }
        return min;
    }
    
    public static int[][] generateLeftPoints(int[][] points, int midCord){
        int[][] leftPoints = new int[midCord][2];
        for(int i = 0; i < midCord; i++){
            leftPoints[i] = points[i];
        }
        return leftPoints;
    }
    
    public static int[][] generateRightPoints(int[][] points, int midCord){
        int[][] rightPoints = new int[midCord][2];
        int pointsIndex = midCord;
        for(int i = 0; i < midCord; i++){
            rightPoints[i] = points[pointsIndex];
            pointsIndex++;
        }
        return rightPoints;
    }
    
    public static double closestPairUtil2(int[][] points, int num_points){
        double minDistance = 2147483647;
        
        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                if(calcDistance(points[i][0], points[i][1], points[j][0], points[j][1]) < minDistance){
                    minDistance = calcDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
                }
            }
        }
        return minDistance;
    }
    
    // calculates the distance between 2 points
    public static double calcDistance(int Px, int Py, int Qx, int Qy){
        //return Math.sqrt((Px-Qx)*(Px-Qx) + (Py-Qy)*(Py-Qy));
        return Math.sqrt(Math.pow((Px-Qx),2) + Math.pow((Py-Qy),2));
    }
    
    // modified bubble sort algorithm for 2d-array, sorts by x-coordinate
    public static void bubbleSortX(int[][] points){
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (points[j][0] > points[j + 1][0]) {
                    int[] temp = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = temp;
                }
            }
        }
    }
    // modified bubble sort algorithm for 2d-array, sorts by y-coordinate
    public static void bubbleSortY(int[][] points){
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (points[j][1] > points[j + 1][1]) {
                    int[] temp = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = temp;
                }
            }
        }
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
