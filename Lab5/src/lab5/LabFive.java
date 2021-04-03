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

public class LabFive{

	public static void main(String[] args) {
		
		System.out.print("\n--- Q1 -------\n\n");
		int num_vertices =10;
		int[][] bipat_graph = {
		{0,0,0,0,0,1,1,0,0,0},
		{0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,1,0,1,1,0},
		{0,0,0,0,0,0,1,0,0,1},
		{0,0,0,0,0,0,1,0,0,1},
		{1,0,1,0,0,0,0,0,0,0},
		{1,1,0,1,1,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0},
		{0,0,0,1,1,0,0,0,0,0}
		};
		System.out.print("\nMaximum Matching:\n");
		max_match (num_vertices, bipat_graph);
	
		System.out.print("\n--- Q2 -------\n\n");
		int num_nodes =10;
		int[][] digraph = {
				{0,0,0,0,0,1,1,0,0,0},
				{0,0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,1,0,1,1,0},
				{0,0,0,0,0,0,1,0,0,1},
				{0,0,0,0,0,0,1,0,0,1},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}
				};
		int source =1;
		int sink =10;
		System.out.print("\nMaximum Possible Flow = " + max_flow (num_nodes, digraph, source, sink));
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
*/
//////////////////////////////////////////////////

	// For the bipartite graph "g" with "v" vertices,
	// function "max_match" prints out the edges of the maximum matching
	public static void max_match (int v, int[][] g) {
	            	
	}
	
	// For the graph "g" with "v" vertices,
	// function "max_flow" prints out the value of maximum flow of the network
	public static int max_flow (int v, int[][] g, int s, int t) {
		return 0;
		            	
	}
}