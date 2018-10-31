package Maze;

import java.util.ArrayList;

public class Maze {
	Node[][] NodeMaze; //2D array of Nodes (the maze)
	int Mazesize; //size of Maze Mazesize x Mazesize
	
	public Maze(int Mazesize) {
		
		this.Mazesize = Mazesize;
		NodeMaze = new Node[Mazesize][Mazesize];
		
		int Nodevalue = 0;
		for(int i = 0; i< Mazesize; i++)
		{
			for(int j = 0; j< Mazesize; j++)
			{
				NodeMaze[i][j] = new Node(Nodevalue);
				Nodevalue++;
			}
		}
		
	}
	
	void printMaze()
	{
		for(int i = 0; i< Mazesize; i++)
		{
			for(int j = 0; j< Mazesize; j++)
			{
				System.out.print(NodeMaze[i][j].getVal());
			}
			System.out.println("");
		}
		
		
	}
	
	
	public static void main(String[]args)
	{ 
		/*
		Maze m1 = new Maze(4);
		m1.printMaze();
		System.out.println("program ended");
		*/
	}
	
}
