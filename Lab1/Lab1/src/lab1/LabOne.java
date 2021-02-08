
//////////////////////////////////////////////////
/*
Write your solution for Questions 5, 6 and 7.

You have to provide functionality (body) of three methods:

Q5: public static int gcdByEuclid (int,int) 
Q6: public static void hashMe(int[])
Q:7 public static int[] generatePseudos (int,int,int,int,int)

Do not make any changes in the provided code anywhere else.
(You are allowed to import another standard java library, if required)
*/
//////////////////////////////////////////////////

package lab1;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LabOne{
        
	public static void main(String[] args) {
		
		System.out.print("--- Q5 -------\n");
		// Input for Q5 will be read from a file.
		// Each row of the file has 2 positive integers separated by a space.
		// The pair of integers corresponds to p and q, as described in the handout.
		try { 
                        FileReader freader = new FileReader(new File("input_Q5.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				String[] pairs = row.split(" ",2);
				int p = Integer.parseInt(pairs[0]);
				int q = Integer.parseInt(pairs[1]);
				System.out.println(gcdByEuclid(p,q));
			} 
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }
		
		///////////////////////////////////////////////////////////
		System.out.print("--- Q6 -------\n");
		int[] numbers = {12,44,13,88,23,94,11,39,20,16,5};
		hashMe(numbers);
		
		///////////////////////////////////////////////////////////
		System.out.print("--- Q7 -------\n");
		// Input for Q7 will be read from a file.
		// Each row of the file has 5 positive integers separated by a space.
		// First 4 integers corresponds to m,a,c and x_0, as described in the handout.
		// The fifth integer in each row, n, tells how many random numbers to generate.
		try { 
			FileReader freader = new FileReader(new File("input_Q7.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				String[] values = row.split(" ", 5);
				int m = Integer.parseInt(values[0]);
				int a = Integer.parseInt(values[1]);
				int c = Integer.parseInt(values[2]);
				int x_0 = Integer.parseInt(values[3]);
				int n = Integer.parseInt(values[4]);
				int pseudos[] = generatePseudos (m,a,c,x_0,n);
				for (int i=0; i<n; i++) {
					System.out.print(pseudos[i]+" ");
				}
				System.out.println();
			} 
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
Do not make any other changes in the code,
as it might alter the ouput,
or the xpecetd format of th etoutput.
*/
//////////////////////////////////////////////////
	
	// Method to find the greatest common divisor (gcd) 
	// of two positive integers using Euclid's algorithm.
	// Solution for Question 5.
	
	public static int gcdByEuclid (int p, int q) {
                
		// Write your code here
                if(q == 0){
                    return p;
                }else{
                
                    return gcdByEuclid(q, (p%q));
                }
	
	}
	
	// Method to generate pseudorandom numbers
	// using linear congruential method.
	// Solution for Question 6.
	
	public static void hashMe(int[] values) {
		// Your code should print 3 sequences of integers on separate lines
		// Integers in each sequence should be separetd by a single space
		// The sequences should corresponds to the three cases explained in the handout
		
		// Write your code here
                System.out.println("---------Chaining---------");
                LinkedList<Integer>[] hashTable = new LinkedList[values.length];
                for(int i = 0; i < values.length; i++){
                    hashTable[i] = new LinkedList();
                }
                for(int i = 0; i < values.length; i++){
                    int index = ((3*values[i])+5)%11;
                    hashTable[index].add(values[i]);
                }
      
                for(int i = 0; i < values.length; i++){
                    int size = hashTable[i].size();
                    for(int j = 0; j < size; j++){
                        System.out.println("HashTable Index: " + i);
                        System.out.println("LinkList Index: " + j);
                        System.out.println("Value:" + hashTable[i].get(j));
                        System.out.println("--------------------");
                    }
                }
                
                System.out.println("---------Linear Probing---------");
                int[] hashTable2 = new int[values.length];
                for(int i = 0; i < values.length; i++){
                    int index = ((3*values[i])+5)%11;
                    if(hashTable2[index] == 0){
                        hashTable2[index] = values[i];
                    }else{
                        while(hashTable2[index] != 0){
                            index = (index+1)%11 ;
                            
                        }
                        hashTable2[index] = values[i];
                    }
                    
                }
                
                System.out.println("---------Double Hasing---------");
                
		
	}
	
	// Method to generate pseudorandom numbers
	// using linear congruential method.
	// Solution for Question 7.
	
	public static int[] generatePseudos (int m, int a, int c, int x_0, int n) {
		
		// Write your code here

	}
}

