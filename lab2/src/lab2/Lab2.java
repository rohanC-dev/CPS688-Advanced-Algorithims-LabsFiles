
//////////////////////////////////////////////////
/*
Lab 2 - CPS688 - W21
*/
//////////////////////////////////////////////////
package lab2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

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
                    System.out.print(i + ": {");
                    for(int j = 0; j < g[i].length; j++){
                       System.out.print(g[i][j] + ",");
                    }
                    System.out.print("}\n");
                }
                
                // create an adjacency matrix of the graph
                int[][] adjMatrix = new int[n][n];
                for(int i = 0; i < g.length ; i++){
                    // first vertex: g[i][0]
                    // second vertex: g[i][1]
                    adjMatrix[g[i][0]][g[i][1]]++;
                    // if statement componsates for a loop in the graph
                    if(g[i][0] != g[i][1]){
                        adjMatrix[g[i][1]][g[i][0]]++;
                    }
                }
                //printing the adjacency matrix
                System.out.println("--Adjacency Matrix--");
                for(int i = 0; i < adjMatrix.length; i++){
                    for(int j = 0; j < adjMatrix[i].length; j++){
                        System.out.print(" " + adjMatrix[i][j]);
                    }
                    System.out.print("\n");
                }
                
                
                // print the degree of all the verticies
                int[] degrees = new int[n];
                for(int i = 0; i < adjMatrix.length; i++){
                    for(int j = 0; j < adjMatrix[i].length; j++){
                        if(i == j){
                            // loops are counted twice
                            degrees[i] = degrees[i] + 2*adjMatrix[i][j];
                        }else{
                            degrees[i] = degrees[i] + adjMatrix[i][j];
                        }
                    }
                }
                
                System.out.println("---The Degrees of All Vertices---");
                System.out.print("{");
                
                for(int i = 0;  i < degrees.length; i++){
                    System.out.print(degrees[i] + ", ");
                }
                System.out.println("}");
                

                // the total degree of the graph
                int sum = 0;
                for(int i = 0; i < degrees.length; i++){
                    sum = sum + degrees[i];
                }
                System.out.println("--Total degree of graph--\n" + sum);
                
                // determine any isolated verticies
                System.out.println("--Isolated Verticies--");
                // isolated vertices are the ones in the matrix that have a value of 0 for in every colum or just a 1 in the diagonal part
                for(int i = 0; i < adjMatrix.length; i++){
                    boolean isIsolated = true;
                    for(int j = 0; j < adjMatrix[i].length; j++){
                        if(adjMatrix[i][j] != 0){
                            // check for loops
                            if(i != j){
                                isIsolated = false;
                            }
                        }
                    }
                    if(isIsolated == true){
                        System.out.println(i);
                    }
                }
                
                // determine if simple graph or not
                
                // checking for A[i, j] > 1
                boolean parallelEdgesExist = false;
                for(int i = 0; i < adjMatrix.length; i++){
                    for(int j = 0; j < adjMatrix[i].length; j++){
                        if(adjMatrix[i][j] > 1){
                            parallelEdgesExist = true;
                            break;
                        }
                        
                    }
                }
                
                //checking for loops
                System.out.println("The input graph is simple:");
                boolean loopsExist = false;
                for(int i = 0; i < adjMatrix.length; i++ ){
                    if(adjMatrix[i][i] != 0){
                        loopsExist = true;
                        break;
                    }
                }
                
                if(parallelEdgesExist && loopsExist){
                    //System.out.println("The graph is not a simple graph because loops and parallel edges exist.");
                    System.out.println("NO");
                }else if(parallelEdgesExist && !loopsExist){
                    //System.out.println("The graph is not a simple graph because parallel edges exist.");
                    System.out.println("NO");
                }else if(!parallelEdgesExist && loopsExist){
                    //System.out.println("The graph is not a simple graph because loops exist.");
                    System.out.println("NO");
                }else{
                    //System.out.println("The graph is a simple graph because loops and parallel edges don't exist.");
                    System.out.println("YES");
                }

	}
	
	// Solution for Question 2.
	static void DFS(int x, int[] visitedVertex, Stack<Integer> s, ArrayList<ArrayList<Integer>> adjM){
            visitedVertex[x] = 1;
            int i;
            Iterator<Integer> iterator = adjM.get(x).iterator();
            while(iterator.hasNext()){
                i = iterator.next();
                if(visitedVertex[i] == 0){
                    DFS(i, visitedVertex, s, adjM);
                }
            }
            s.push(new Integer(x));
        }
	static void question2 (int m, int n, int[][] g) {
		
		// Write your code here
                ArrayList<ArrayList<Integer>> adjM = new ArrayList<ArrayList<Integer>>();
                for(int i =0; i<n; i++){
                    adjM.add(new ArrayList<Integer>());
                }
                for(int i = 0; i <n; i++){
                    adjM.get(g[i][0]).add(g[i][1]);
                }
                
                Stack<Integer> s = new Stack<Integer>();
                int visitedVertex[] = new int[n];
                for(int i = 0; i < visitedVertex.length; i++){
                    if(visitedVertex[i] == 0){
                        DFS(i, visitedVertex, s, adjM);
                    }
                }
                System.out.print("{");
                while(!s.empty()){
                    System.out.print(s.pop() + ", ");
                }
                System.out.println("}");
           
//                int[] visitedVertex = new int[n];
//                // stores in-degrees of vertices
//                int[] inDegVertex = new int[n];
//                for(int i = 0; i < adjMatrix.length; i++){
//                    for(int j = 0; j < adjMatrix.length; j++){
//                        inDegVertex[i] = inDegVertex[i] + adjMatrix[j][i];
//                    }
//                }
//
//                Queue<Integer> q = new ArrayDeque<>();
//                q.add(m);
//                for(int i = 0; i < inDegVertex.length; i++){
//                    if(inDegVertex[i] == 0 && i != m){
//                        q.add(i);
//                    }
//                }
	
	}
	
	// Solution for Question 2.
	
	static void question3 (int m, int n, int[][] g) {
		
		// Write your code here
                int[][] adjMatrix = new int[n][n];
                for(int i = 0; i < g.length ; i++){
                    // first vertex: g[i][0]
                    // second vertex: g[i][1]
                    adjMatrix[g[i][0]][g[i][1]]++;
                    // if statement componsates for a loop in the graph
                    if(g[i][0] != g[i][1]){
                        adjMatrix[g[i][1]][g[i][0]]++;
                    }
                }
                
                
                 
                int[] visitedVertex = new int[n];
                // queue for storing vertex left to visit
                Queue<Integer> q = new ArrayDeque<>();
                // queue to store BFS traversal and print it
                Queue<Integer> qBFS = new ArrayDeque<>();
                int x;
                qBFS.add(m);
                visitedVertex[m] = 1;
                q.add(m);
                while(q.isEmpty()==false){
                    x = q.remove();
                    for(int i = 0; i <= n-1; i++){
                        if(adjMatrix[x][i] > 0){
                            if(visitedVertex[i] == 0){
                                // discovery edge
                                qBFS.add(i);
                                visitedVertex[i] = 1;
                                q.add(i);
                                System.out.println("{" + x + ", " + i + "}  D");
                            }else if(visitedVertex[i] > 0){
                                // checking for cross edges
                                if(i >= x){
                                    System.out.println("{" + i + ", " + x + "}  C");
                                }
                                
                            }
                        }
                    }
                    
                }
                System.out.println("--BFS Traversal--");
                System.out.print("{");
                int sizeOfQueue = qBFS.size();
                for(int i = 0; i < sizeOfQueue; i++){
                    System.out.print(qBFS.remove() + ", ");
                }
                System.out.println("}");
                
                
	}
}

