//////////////////////////////////////////////////
/*
Lab 5 - CPS688 - W21
Name: Rohan  Chopra
St ID: 500900481
*/
//////////////////////////////////////////////////
package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LabFive {

    public static void main(String[] args) {

        System.out.print("\n--- Q1 -------\n\n");
        int num_vertices = 10;
        int[][] bipat_graph = {
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0}
        };
        System.out.print("\nMaximum Matching:\n");
        max_match(num_vertices, bipat_graph);

        System.out.print("\n--- Q2 -------\n\n");
        int num_nodes =6;
		int[][] digraph = { {0, 10, 8, 0, 0, 0},
                                    {0, 0, 5, 5, 0, 0},
                                    {0, 4, 0, 0, 10, 0},
                                    {0, 0, 9, 0, 10, 3},
                                    {0, 0, 0, 6, 0, 14},
                                    {0, 0, 0, 0, 0, 0}
                            };
		int source =0;
		int sink =5;
        System.out.println("\nMaximum Possible Flow = " + max_flow(num_nodes, digraph, source, sink));
    }

//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
     */
//////////////////////////////////////////////////
    // For the bipartite graph "g" with "v" vertices,
    // function "max_match" prints out the edges of the maximum matching
    public static void max_match(int v, int[][] g) {

        // generate directed graph
        
        // selects the upper triangular portion of the matrix
        int[][] directedBipartiteGraph = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = i; j < v; j++) {
                directedBipartiteGraph[i][j] = g[i][j];
            }
        }

        int matching[] = new int[v];

        for (int i = 0; i < matching.length; i++) {
            matching[i] = -1;
        }

        int result = 0;
        for (int u = 0; u < v; u++) {

            boolean seen[] = new boolean[v];

            if (bipartiteMatching(directedBipartiteGraph, u, seen, matching) == 1) {
                result++;
            }
        }
        
        //System.out.println(result);
    }

    public static int bipartiteMatching(int directedBipartiteGraph[][], int u, boolean seen[], int matching[]) {

        for (int v = 0; v < directedBipartiteGraph.length; v++) {

            if (directedBipartiteGraph[u][v] == 1 && !seen[v]) {

                seen[v] = true;

                if (matching[v] < 0 || bipartiteMatching(directedBipartiteGraph, matching[v], seen, matching) == 1) {
                    matching[v] = u;
                    System.out.println("(" + matching[v] + "," + v +")");
                    return 1;
                }
            }
        }
        return 0;
    }

    // For the graph "g" with "v" vertices,
    // function "max_flow" prints out the value of maximum flow of the network
    public static int max_flow(int v, int[][] g, int s, int t) {
        
        // generate residual graph
        
        // flip each value accross diagonal
        int[][] residualGraph = new int[v][v];
        
        for(int i = 0; i < v; i++){
            for(int j = 0; j < v; j++){
                residualGraph[i][j] = g[i][j];
            }
        }
        
        int[] augmentingPath = new int[v];
        
        int maxFlow = 0;
        int u;
        
        while(BFS(s, t, v, residualGraph, augmentingPath)){
            
            int path_flow = 2147483647;
            for(int i = t; i != s; i = augmentingPath[i]){
                u = augmentingPath[i];
                path_flow = Math.min(path_flow, residualGraph[u][i]);
            }
            
            for(int i = t; i != s; i = augmentingPath[i]){
                u = augmentingPath[i];
                residualGraph[u][i] = residualGraph[u][i] - path_flow;
                residualGraph[i][u] = residualGraph[i][u] + path_flow;
            }
            
            maxFlow = maxFlow + path_flow;
        }
        

        return maxFlow;

    }
    // m = start node, t = sink node, n = number of nodes
    // BFS algorithm from lab2, modified for ford-fulkerson algorithm
    static boolean BFS(int m, int t,int n, int[][] g, int[] augmentingPath) {
                int[][] adjMatrix = g;
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
                                if(i == t){
                                    augmentingPath[i] = x; 
                                    return true;
                                }
                                qBFS.add(i);
                                augmentingPath[i] = x; 
                                visitedVertex[i] = 1;
                                q.add(i);
                                //System.out.println("{" + x + ", " + i + "}  D");
                            }else if(visitedVertex[i] > 0){
                                // checking for cross edges
                                if(i >= x){
                                    //System.out.println("{" + i + ", " + x + "}  C");
                                }
                                
                            }
                        }
                    }
                    
                }
//                System.out.println("--BFS Traversal--");
//                System.out.print("{");
//                int sizeOfQueue = qBFS.size();
//                for(int i = 0; i < sizeOfQueue; i++){
//                    System.out.print(qBFS.remove() + ", ");
//                }
//                System.out.println("}");
                
                return false;
                
                
	}
    // BFS algorithm from lab2, modified for ford-fulkerson algorithm
//    public static boolean BFS(int source, int sink ,int n, int[][] g, int[] augmentingPath) {
//		
//		
//
//                int[] visitedVertex = new int[n];
//                // queue for storing vertex left to visit
//                Queue<Integer> q = new ArrayDeque<>();
//                // queue to store BFS traversal and print it
//                Queue<Integer> qPrint = new ArrayDeque<>();
//                int x = 0;
//                qPrint.add(source);
//                visitedVertex[source] = 1;
//                q.add(source);
//                augmentingPath[x] = -1;  
//                while(q.isEmpty()==false){
//                    x = q.remove();
//                    
//                    for(int i = 0; i <= n-1; i++){
//                        if(g[x][i] > 0){
//                            if(visitedVertex[i] == 0){
//                                // discovery edge
//                                if(i == sink){
//                                    augmentingPath[i] = x;  
//                                    return true;
//                                }
//                                qPrint.add(i);
//                                augmentingPath[i] = x;  
//                                visitedVertex[i] = 1;
//                                q.add(i);
//                            }
//                        }
//                    }
//                    
//                }
//                System.out.println("--BFS Traversal--");
//                System.out.print("{");
//                int sizeOfQueue = qPrint.size();
//                for(int i = 0; i < sizeOfQueue; i++){
//                    System.out.print(qPrint.remove() + ", ");
//                }
//                System.out.println("}");
//                
//                return false;
//                
//                
//	}
    
    
    
    
    
}




//References:
//https://www.geeksforgeeks.org/maximum-bipartite-matching/
//https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/