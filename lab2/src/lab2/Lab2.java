
//////////////////////////////////////////////////
/*
Lab 2 - CPS688 - W21
*/
//////////////////////////////////////////////////
package lab2;

public class Lab2{
	public static void main(String[] args) {
	
		// Input for Q1 is an undirected graph "graph1".
		// Input graph has "n1" vertices.
		// The set of vertices = {0,1,...,(n1-1)}
		System.out.print("--- Q1 -------\n");
		int n1=9; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph1 = {{0,0},{0,1},{1,2},{1,2},{2,5},{2,5},{2,5},{3,3},{4,5},{5,6},{5,7},{6,7}}; 
		question1(n1,graph1);
		
		// Input for Q2 is an acyclic directed graph "graph2".
		// Input graph has "n2" vertices.
	    // The set of vertices = {0,1,...,(n2-1)}
		// Your search should start at vertex "start_node1".
		System.out.print("--- Q2 -------\n");
		int n2=8; 
		int start_node1=0; // set of vertices = {0,1,2,3,4,5,6,7}
		int[][] graph2 = {{2,3},{3,6},{4,0},{4,7},{5,0},{5,2},{6,1},{7,1}}; 
		question2(start_node1,n2,graph2);
		
		// Input for Q3 is an undirected graph "graph3".
		// Input graph has "n3" vertices.
	    // The set of vertices = {0,1,...,(n3-1)}
		// Your search should start at vertex "start_node2".
		System.out.print("--- Q3 -------\n");
		int n3=9; 
		int start_node2=0; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph3 = {{0,1},{0,5},{0,7},{1,2},{2,3},{2,7},{2,8},{3,4},{4,6},{4,8},{5,6},{6,8}}; 
		question3(start_node2,n3,graph3);
		
	}
	
//////////////////////////////////////////////////

	// Solution for Question 1.
	
	static void question1 (int n, int[][] g) {
	
		// Write your code here
                
                // create the edge endpoint function of the graph
                for(int i = 0; i < g.length; i++){
                    System.out.print("e" + i + ": {");
                    for(int j = 0; j < g[i].length; j++){
                       System.out.print(g[i][j] + ",");
                    }
                    System.out.print("}\n");
                }
                
                // create an adjacency matrix of the graph
                int[][] adjMatrix = new int[n][n];
                for(int i = 0; i < g.length ; i++){
//                    for(int j = 0; j < g[i].length; j++){
//                        int num = g[i][j];
//                        adjMatrix[i][num] = 1;
//                        adjMatrix[num][i] = 1;
//                    }
                    // first vertex: g[i][0]
                    // second vertex: g[i][1]
                    
                    adjMatrix[g[i][0]][g[i][1]] = 1;
                    adjMatrix[g[i][1]][g[i][0]] = 1;
                    
                }
                

	}
	
	// Solution for Question 2.
	
	static void question2 (int m, int n, int[][] g) {
		
		// Write your code here
		
	}
	
	// Solution for Question 2.
	
	static void question3 (int m, int n, int[][] g) {
		
		// Write your code here
	}
}

