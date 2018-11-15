package Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
	Node[] NodeMaze; //2D array of Nodes (the maze)
	int Mazesize; //size of Maze ->  Mazesize x Mazesize Nodes
	public int time;
	
	public Maze(int MazeDimension) {
		
		Mazesize = MazeDimension * MazeDimension;
		NodeMaze = new Node[Mazesize];
		fillMaze();
		connectMaze();
		createMaze();
		printMaze();
		
	}
	
	void fillMaze() //Initializes Nodes of Maze
	{
		int Nodevalue = 1; //NODE VALUES RANGE IS (1 TO MAZESIZE)
		for(int i = 0; i< Mazesize; i++)
		{
			NodeMaze[i] = new Node(Nodevalue);
			Nodevalue++;
			
		}
	}
	
	void connectMaze() //Stores Neighboring Nodes (Shared Maze walls) in each Node
	{
		int NodeVal; //NodeVal is 1 more than NodeVal's Index (NodeVal starts at 1 vs NodeVal's NodeMaze Index starts at 0)
		int NeighborVal; //NeighborVal is 1 more than NeighborVal's Index
		for(int i = 0; i<Mazesize; i++) //WILL BREAK IF NODE VALUES RANGE CHANGES (Range 1 to MazeSize)
		{
			NodeVal = NodeMaze[i].getVal();
			if(NodeVal > 4) //Finds Above Neighbor
			{
				NeighborVal = NodeVal - 4;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if(NodeVal < 13) //Finds Below Neighbor
			{
				NeighborVal = NodeVal + 4;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if((NodeVal % 4) != 0) //Finds Right Neighbor
			{
				NeighborVal= NodeVal + 1;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if((((NodeVal-1)%4) != 0) && (NodeVal != 1)) //Finds Left Neighbor
			{
				NeighborVal = NodeVal - 1;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			
		}
	}
	
	void createMaze() //DFS FUNSIES
	{
		int visited = 1;
		
		Node current = NodeMaze[0];
		Node neighbor;
		int currentVal = current.getVal();
		
		Random rand = new Random(0);
		int randomNeighborIndex;
		Boolean validNeighbor;
		
		Stack nodeStack = new Stack();
		nodeStack.push(current);
		
	
		
		//Set<Int> neighborIndexHashSet = new HashSet<Int>(); 
		
		while(visited < Mazesize)
		{
			System.out.println(current.getVal()  + "      VISITED: " + visited);
			
			validNeighbor = false;
			current.setSeen(true);
			
			randomNeighborIndex = rand.nextInt(current.getNeighbors().size());
			neighbor = current.getNeighbors().get(randomNeighborIndex);

			
			while(!validNeighbor)
			{
				if(!neighbor.Seen())
				{
					validNeighbor = true;
					visited++;
				}
				else if(neighbor.Seen() && current.getConnectedNeighbors().contains(neighbor))
				{
					validNeighbor = true;
				}
				else
				{
					randomNeighborIndex = rand.nextInt(current.getNeighbors().size());
					neighbor = current.getNeighbors().get(randomNeighborIndex);
				}
				//System.out.println("Still running...");
				
			}
			
			current.setConnectedNeighbor(neighbor); //break the walls
			neighbor.setConnectedNeighbor(current); // between both neighbors
			neighbor.setSeen(true);
			current.setSeen(true);
			current = neighbor;
			neighbor = null;
			
			
			
			//if seen, only go if connected neighbor
			
			
			
			
		}
		
		
		
	}
	
	
	
	void DepthFirstSearch()
	{
		unSeeMaze();
		time = 0;
		for(int i = 0; i< Mazesize; i++)
		{
			if(!(NodeMaze[i]).Seen())
			{
				DepthFirstSearchVisit(NodeMaze[i]);
				
			}
		}
		
	}
	
	void DepthFirstSearchVisit(Node n)
	{
		n.setSeen(true);
	    time++;
	    n.setDiscovered(time);
	    for(int i = 0; i<n.getConnectedNeighborsList().size(); i++)
	    {
	    	if(!n.getConnectedNeighborsList().get(i).Seen())
	    	{
	    		DepthFirstSearchVisit(n.getConnectedNeighborsList().get(i));
	    	}
	    }
	    
	    n.setDone(false);
	    time++;
	    n.setFinished(time);
		
	}
	
	void unSeeMaze() //make all nodes 'not visited yet'
	{

		for(int i = 0; i < Mazesize; i++)
		{
			NodeMaze[i].setSeen(false);
			NodeMaze[i].setDone(false);
			NodeMaze[i].setDiscovered(-1);
			NodeMaze[i].setFinished(-1);
		}
	}
	
	
	
	void printMaze()
	{
		/*
		String neighbors = "";
		for(int i = 0; i< Mazesize; i++)
		{
			neighbors = "";
			for(int j = 0; j<NodeMaze[i].getNeighbors().size(); j++)
			{
				neighbors = neighbors + ", " + NodeMaze[i].getNeighbors().get(j).getVal();
			}
			System.out.print(NodeMaze[i].getVal() + "   N: " + neighbors);
			System.out.println("");
		}
		*/
			String UnderLine = "+ +-+-+-+";
			System.out.println(UnderLine);
			String MainLine = "| ";
		    UnderLine = "+";
			
			for(int i = 0; i< 4; i++)
			{
				if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
				{
					MainLine = MainLine + "  ";
					
				}
				else
				{
					MainLine = MainLine + "| ";
				}
				
			}
			
			for(int i = 0; i< 4;i++)
			{
				if(!NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+4]))// do not include in last loop
				{
					UnderLine = UnderLine + "-";
				}
				else
				{
					UnderLine = UnderLine + " ";
				}
				UnderLine = UnderLine + "+";
			}
			System.out.println(MainLine);
			System.out.println(UnderLine);
			MainLine = "| ";
			UnderLine = "+";
			
			
			for(int i = 4; i< 8;i++)
			{
				if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
				{
					MainLine = MainLine + "  ";
					
				}
				else
				{
					MainLine = MainLine + "| ";
				}
				
			}
			
			for(int i = 4; i< 8;i++)
			{
				if(!NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+4]))// do not include in last loop
				{
					UnderLine = UnderLine + "-";
				}
				else
				{
					UnderLine = UnderLine + " ";
				}
				
				UnderLine = UnderLine + "+";
			}
			System.out.println(MainLine);
			System.out.println(UnderLine);
			MainLine = "| ";
			UnderLine = "+";
			
			for(int i = 8; i< 12;i++)
			{
				if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
				{
					MainLine = MainLine + "  ";
					
				}
				else
				{
					MainLine = MainLine + "| ";
				}
				
			}
			
			for(int i = 8; i< 12;i++)
			{
				if(!NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+4]))// do not include in last loop
				{
					UnderLine = UnderLine + "-";
				}
				else
				{
					UnderLine = UnderLine + " ";
				}
				UnderLine = UnderLine + "+";
			}
			System.out.println(MainLine);
			System.out.println(UnderLine);
			MainLine = "| ";
			UnderLine = "+";
			for(int i = 12; i< 15;i++)
			{
				if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
				{
					MainLine = MainLine + "  ";
					
				}
				else
				{
					MainLine = MainLine + "| ";
				}
				
			}
			MainLine = MainLine + "|";
			UnderLine = "+-+-+-+ +";
			
			System.out.println(MainLine);
			System.out.println(UnderLine);
			MainLine = "| ";
		
			
			
		
		
	}
	
	
	
	public static void main(String[]args)
	{ 
		
		Maze m1 = new Maze(4);

		System.out.println("program ended");
		
	}
	
}
