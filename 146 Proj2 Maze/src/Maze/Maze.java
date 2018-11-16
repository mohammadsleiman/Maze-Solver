package Maze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Maze {
	Node[] NodeMaze; //2D array of Nodes (the maze)
	int Mazesize; //size of Maze ->  Mazesize x Mazesize Nodes
	int MazeDimension;
	public int time;
	
	public Maze(int MazeDimension) {
		
		this.Mazesize = MazeDimension;
		Mazesize = MazeDimension * MazeDimension;
		NodeMaze = new Node[Mazesize];
		fillMaze();
		connectMaze();
		createMaze();
		DepthFirstSearch();
		BreadthFirstSearch();
		printBFS();
		
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
		
		Random rand = new Random(1);
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
	
	void BreadthFirstSearch()
	{
		unSeeMaze();
		Queue<Node> BFSqueue = new LinkedList<Node>();
		BFSqueue.add(NodeMaze[0]);
		Node n = null;
		while(BFSqueue.size()!=0)
		{
			n = BFSqueue.remove();
			
			for(int j = 0; j<n.getConnectedNeighborsList().size(); j++)
			{
				if(!n.getConnectedNeighborsList().get(j).Seen())
				{
					n.getConnectedNeighborsList().get(j).setSeen(true);
					n.getConnectedNeighborsList().get(j).setDiscovered(n.getDiscovered()+1);
					NodeMaze[j].setFinished(n.getVal());
					BFSqueue.add(n.getConnectedNeighborsList().get(j));
				} 

			}

		}
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
	
	void printBFS()
	{
		String UnderLine = "+ +--+--+--+";
		System.out.println(UnderLine);
		String MainLine = "|";
	    UnderLine = "+";
		
		for(int i = 0; i< 4; i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
			}
			
		}
		
		for(int i = 0; i< 4;i++)
		{
			if(!NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+4]))// do not include in last loop
			{
				UnderLine = UnderLine + "--";
			}
			else
			{
				UnderLine = UnderLine + "  ";
			}
			UnderLine = UnderLine + "+";
		}
		System.out.println(MainLine);
		System.out.println(UnderLine);
		MainLine = "|";
		UnderLine = "+";
		
		
		for(int i = 4; i< 8;i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
			}
			
		}
		
		for(int i = 4; i< 8;i++)
		{
			if(!NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+4]))// do not include in last loop
			{
				UnderLine = UnderLine + "--";
			}
			else
			{
				UnderLine = UnderLine + "  ";
			}
			
			UnderLine = UnderLine + "+";
		}
		System.out.println(MainLine);
		System.out.println(UnderLine);
		MainLine = "|";
		UnderLine = "+";
		
		for(int i = 8; i< 12;i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
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
		MainLine = "|";
		UnderLine = "+";
		for(int i = 12; i< 15;i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
			}
			
		}
		MainLine = MainLine + "|";
		UnderLine = "+--+--+--+ +";
		
		System.out.println(MainLine);
		System.out.println(UnderLine);
		MainLine = "| ";
	
	}
	void printAnySize()
	{
		int printHeight = (MazeDimension*2)+1;
		int MazeIndex = 0;
		int tempIndex = 0;
		String[] MazeString = new String[(MazeDimension*2)+1];
		String rightWall = " |";
		String wall = "|";
		String noWall = "  ";
		String Floor = "+-";
		String noFloor = "+ ";
		String endCap = "+";
		String Opening = "+ +";
		String rightOpening = " +";
		String rightClosing = "-+";
		String leftClosing = "+-";
		Boolean firstTime = true;
	
		/** Top Opening for Maze **/
		MazeString[0] = Opening;
		for(int n = 1; n< MazeDimension-1; n++)
		{
			MazeString[0] = MazeString[0] + rightClosing;
		}
		
		/** Bottom Opening **/
		for(int m = 0; m<MazeDimension -1; m++)
		{
			MazeString[printHeight -1] = MazeString[printHeight -1] + leftClosing;
		}
		MazeString[printHeight -1 ] = MazeString[printHeight -1] + Opening;
		
		/** Maze Left Boundary**/
		for(int o = 1; o< printHeight -2; o++)
		{
			if(o%2 == 1 || o==1)
			{
				MazeString[o]= wall;
			}
			else
			{
				MazeString[o] =  endCap;
			}
		}
		
		
		/** Inside The Maze **/
		for(int i = 1; i < printHeight-1; i++)
		{
			tempIndex = MazeIndex;
			for(int j = 0; j<MazeDimension-1; j++)
			{
				if(i%2 == 1 || i == 1) //if we are in Vertical Mode
				{
					if((MazeIndex < NodeMaze[MazeIndex].getConnectedNeighborsList().size()) && (NodeMaze[MazeIndex].getConnectedNeighbors().contains(NodeMaze[MazeIndex+1])))
					{
						MazeString[i] = MazeString[i] + noWall; //When there is no wall
					}
					else
					{
						MazeString[i] = MazeString[i] +  rightWall; //When there is a wall
					}
					
					MazeIndex++;
				}
				if(i%2 == 0) //if we are in Horizontal Mode
				{
					if(NodeMaze[MazeIndex].getConnectedNeighbors().contains(NodeMaze[MazeIndex + 4]))
					{
						MazeString[i] = MazeString[i] + rightOpening;
					}
					else
					{
						MazeString[i] = MazeString[i] + rightClosing;
					}
					
					MazeIndex++;
				}
				
			}
			if(i%2 == 1 || i ==1 ) //ensures second loop for Maze Floor (horizontal Mode)
			{
				MazeIndex = tempIndex;
			}
			
		}
		
		for(int i = 0; i<MazeString.length; i++)
		{
			System.out.println(MazeString[i]);
		}
		
		
	}
	
	void printVals()
	{
	String UnderLine = "+ +-+-+-+";
	System.out.println(UnderLine);
	String MainLine = "|";
    UnderLine = "+";
	
	for(int i = 0; i< 4; i++)
	{
		if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
		{
			MainLine = MainLine + NodeMaze[i].getVal() + " ";
			
		}
		else
		{
			MainLine = MainLine + NodeMaze[i].getVal() +"| ";
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
	
	MainLine = "|";
	UnderLine = "+";
	for(int i = 4; i< 8;i++)
	{
		if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
		{
			MainLine = MainLine + NodeMaze[i].getVal() + " ";
			
		}
		else
		{
			MainLine = MainLine + NodeMaze[i].getVal() +"| ";
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
	
	MainLine = "|";
	UnderLine = "+";
	
	for(int i = 8; i< 12;i++)
	{
		if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
		{
			MainLine = MainLine + NodeMaze[i].getVal() + " ";
			
		}
		else
		{
			MainLine = MainLine + NodeMaze[i].getVal() +"| ";
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
	MainLine = "|";
	UnderLine = "+";
	for(int i = 12; i< 15;i++)
	{
		if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
		{
			MainLine = MainLine + NodeMaze[i].getVal() + " ";
			
		}
		else
		{
			MainLine = MainLine + NodeMaze[i].getVal() +"| ";
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
