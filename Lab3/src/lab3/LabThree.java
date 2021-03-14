//////////////////////////////////////////////////
/*
Lab 3 - CPS688 - W21
Name: Rohan  Chopra
St ID: 500900481
*/
//////////////////////////////////////////////////

package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LabThree{
	public static void main(String[] args) {
		
		
		System.out.print("\n--- Q1 -------\n\n");
		/*
		 * For each amount given in the array "amounts",
		 * using the currency denominations: 1c, 5c, 10c, 25c, $1, 
		 * pay amount to customer using fewest numbers of coins.
		 */
		int[] amounts = {0,34,37,97,100};
		for (int m=0; m<5; m++) {
			System.out.print("Amount = "+amounts[m]+" cents.\n");
			System.out.print("Payout:\n");
			payout_in_coins (amounts[m]);
		}
		///////////////////////////////////////////////////////////
		System.out.print("\n--- Q2 -------\n");
		// Input for Q2 will be read from a file "input_Q2.txt".
		// There can be more than one input graphs.
		// For a set of n vertices, set {1,2,3,...,n} will be used as the set of vertices
		// First row of the input graph has 2 positive integers separated by a space.
		// These two values are the number of vertices and the number of edges, respectively.
		// Second row of the input graph has one integer value indicating the starting vertex for Prim’s algorithm.
		// Every subsequent row contains 3 positive integers separated by a space.
		// Among  these 3 values, the first two values are the end-points of an edge in the graph.
		// The third value is the weight of the edge in the graph.
		
		try { 
			FileReader freader = new FileReader(new File("input_Q2.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				System.out.print("\nInput Graph:\n");
				String[] graph_parameters = row.split(" ",2);
				int num_vertices = Integer.parseInt(graph_parameters[0]);
				int num_edges = Integer.parseInt(graph_parameters[1]);
				int starting_vertex = Integer.parseInt(readInput.readLine().split(" ",1)[0]);
				int[][] graph = new int [num_edges][3];
				System.out.print("There are "+num_vertices+" vertices and "+num_edges+" edges.\n");
				System.out.print("Edge   Weight\n");
				for (int i=0; i<num_edges; i++) {
					row = readInput.readLine();
					String[] triples = row.split(" ",3);
					for (int j=0; j<3; j++)
						graph[i][j] = Integer.parseInt(triples[j]);
					System.out.print("("+graph[i][0]+","+graph[i][1]+")   "+graph[i][2]+"\n");
				}
				System.out.print("\nMST by Prim's algorithm:\n");
				// Call method to find MST using Prim's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Prim = mst_Prim (num_vertices, num_edges, graph, starting_vertex);
				System.out.print("The total weight of the spanning tree is "+total_weight_Prim+".\n");
				
				
				System.out.print("\nMST by Kruskal's algorithm:\n");
				// Call method to find MST using Kruskal's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Kruskal = mst_Kruskal (num_vertices, num_edges, graph);
				System.out.print("The total weight of the spanning tree is "+total_weight_Kruskal+".\n");
				
				readInput.readLine();
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
*/
//////////////////////////////////////////////////
	
	//For the integer value "amount",
	// using the currency denominations: 1c, 5c, 10c, 25c, $1, 
	// methods pay amount to customer using fewest numbers of coins.
	public static void payout_in_coins (int amount) {
                int coins[] = {1, 5, 10, 25, 100};
                ArrayList<Integer> coinsSelected = new ArrayList<Integer>();

                for(int i = coins.length-1; i >= 0; i--){
                    while(amount >= coins[i]){
                        amount = amount - coins[i];
                        coinsSelected.add(coins[i]);
                    }
                }
                
                if(coinsSelected.isEmpty()){
                    System.out.println("--no possible combination--"); 
                }
                int pennies = 0;
                int nickels = 0;
                int dimes = 0;
                int quarter = 0;
                int dollar = 0;
                for(int i = 0; i < coinsSelected.size(); i++){
                    if(coinsSelected.get(i) == 1){
                        pennies++;
                    }else if(coinsSelected.get(i) == 5){
                        nickels++;
                    }else if(coinsSelected.get(i) == 10){
                        dimes++;
                    }else if(coinsSelected.get(i) == 25){
                        quarter++;
                    }else if(coinsSelected.get(i) == 100){
                        dollar++;
                    }
                }
                if(dollar > 0){
                    System.out.print(dollar+" coin(s) of "+1+" dollar.\n");
                }
                if(quarter > 0){
                    System.out.print(quarter+" coin(s) of "+25+" cents.\n");
                }
                if(dimes > 0){
                    System.out.print(dimes+" coin(s) of "+10+" cents.\n");
                }
                if(nickels > 0){
                    System.out.print(nickels+" coin(s) of "+5+" cents.\n");
                }
                if(pennies > 0){
                   System.out.print(pennies+" coin(s) of "+1+" cents.\n"); 
                }
        
	}
	
	// Method to find MST using Prim's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Prim (int num_vertices, int num_edges, int[][] graph, int starting_vertex) {
                final int inf = 2147483647;
		int total_weight=0;
                //cost adj matrix skips first row to make things easier
                int[][] costAdjMatrix = new int[num_vertices+1][num_vertices+1];
                for(int i = 0; i < costAdjMatrix.length; i++){
                    // first vertex: g[i][0]
                    // second vertex: g[i][1]
                    costAdjMatrix[graph[i][0]][graph[i][1]] = graph[i][2];
                    // if statement componsates for a loop in the graph
                    if(graph[i][0] != graph[i][1]){
                        costAdjMatrix[graph[i][1]][graph[i][0]] = graph[i][2];
                    }
                }
                
                // adding infinity values to matrix, for vertices that have no edge connection
                for(int i = 0; i < costAdjMatrix.length; i++){
                    for(int j = 0; j < costAdjMatrix[i].length; j++){
                        if(costAdjMatrix[i][j] == 0){
                            costAdjMatrix[i][j] = inf;
                        }                        
                    }
                }
                
//                System.out.println("--Cost Adjacency Matrix--");
//                for(int i = 0; i < costAdjMatrix.length; i++){
//                    for(int j = 0; j < costAdjMatrix[i].length; j++){
//                        if(costAdjMatrix[i][j] == inf){
//                            System.out.print(" inf");
//                        }else{
//                           System.out.print("  " + costAdjMatrix[i][j]); 
//                        }
//                    }
//                    System.out.print("\n");
//                }
                
                ArrayList<int[]> mst = new ArrayList<int[]>();
                int[] nearVertex = {inf, inf, inf, inf, inf, inf};
                
                // finding minimum cost edge to starting vertex (step 2)
                int minStartCost = costAdjMatrix[starting_vertex][1];
                int minStartVertex = starting_vertex;
                for(int i = 1; i < costAdjMatrix[starting_vertex].length; i++){
                    if(costAdjMatrix[starting_vertex][i] < minStartCost){
                        minStartCost = costAdjMatrix[starting_vertex][i];
                        minStartVertex = i;
                    }
                }
                
                
                int[] arr = {1, minStartVertex, minStartCost};
                mst.add(arr);
                
                nearVertex[starting_vertex] = 0;
                nearVertex[minStartVertex] = 0;
                
                for(int i = 1; i <  nearVertex.length; i++){
                    if(nearVertex[i] != 0){
                        if(costAdjMatrix[i][starting_vertex] < costAdjMatrix[i][minStartVertex]){
                            nearVertex[i] = starting_vertex;
                        }else{
                            nearVertex[i] = minStartVertex;
                        }
                    }
                    
                }
                
                int minVertex = inf;
                int k = 0;
                for(int i = 1; i < num_vertices-1; i++){
                    minVertex = inf;
                    for(int j = 1; j <= num_vertices; j++){
                        if(nearVertex[j] != 0 && costAdjMatrix[j][nearVertex[j]] < minVertex){
                            k = j;
                            minVertex = costAdjMatrix[j][nearVertex[j]];
                        }
                    }
                    
                    int[] edge = {k, nearVertex[k], minVertex};
                    mst.add(edge);
                    nearVertex[k] = 0;
                    
                    for(int j = 1; j <= num_vertices; j++){
                        if(nearVertex[j] != 0 && costAdjMatrix[j][k] < costAdjMatrix[j][nearVertex[j]]){
                            nearVertex[j] = k;
                        }
                    }
                }
                
                
                //OUTPUT

                System.out.println("There are " + num_vertices + " and " + num_edges + " edges");
                System.out.println("\nEdge  | Weight");
                for(int i = 0; i < mst.size(); i++){
                    
                    System.out.println("(" + mst.get(i)[0] + ", " + mst.get(i)[1] + ")  " + mst.get(i)[2]);
                    System.out.println("\n");
                }
                
                
                for(int i = 0; i < mst.size(); i++){
                    total_weight = total_weight + mst.get(i)[2];
                }
          
		return total_weight;
	}
	
	// Call method to find MST using Kruskal's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Kruskal (int num_vertices, int num_edges, int[][] graph) {
		int total_weight=0;
                final int inf = 2147483647;
                int[] set = {-1, -1, -1, -1, -1, -1};
                int[] isIncluded = {0, 0, 0, 0, 0, 0};
                ArrayList<int[]> mst = new ArrayList<int[]>();
                
                
                
                
                
                
		return total_weight;
	}
}

