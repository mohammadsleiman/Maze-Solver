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
	String MazeString[]; //The Array of Strings containing each printable line of our maze
	public int time;
	
	public Maze(int MazeDimension) {
		
		this.MazeDimension = MazeDimension;
		Mazesize = MazeDimension * MazeDimension;
		NodeMaze = new Node[Mazesize];
		fillMaze();
		connectMaze();
		createMaze();
		//DepthFirstSearch();
		//BreadthFirstSearch();
		//printBFS();
		createMazeString();
		printMazeString();
		
		
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
			if(NodeVal > MazeDimension) //Finds Above Neighbor
			{
				NeighborVal = NodeVal - MazeDimension;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if(NodeVal < (Mazesize - MazeDimension+1)) //Finds Below Neighbor
			{
				NeighborVal = NodeVal + MazeDimension;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if((NodeVal % MazeDimension) != 0) //Finds Right Neighbor
			{
				NeighborVal= NodeVal + 1;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			if((((NodeVal-1)%MazeDimension) != 0) && (NodeVal != 1)) //Finds Left Neighbor
			{
				NeighborVal = NodeVal - 1;
				NodeMaze[i].addNeighbor(NodeMaze[NeighborVal -1]);
			}
			
		}
	}
	
	
	void createMaze() //USE DFS TO CREATE RANDOM MAZE
	{
		int visited = 1;
		
		Node current = NodeMaze[0];
		Node neighbor;
		int currentVal = current.getVal();
		
		Random rand = new Random(1);
		int randomNeighborIndex;
		Boolean validNeighbor;
		
		
		while(visited < Mazesize)
		{
			
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
		DepthFirstSearchVisit(NodeMaze[0]);
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
	
	
	
	void printBFS()
	{
		String UnderLine = "+ +-+-+-+";
		System.out.println(UnderLine);
		String MainLine = "|";
	    UnderLine = "+";
		
		for(int i = 0; i< 4; i++)
		{
			
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				if(NodeMaze[i].getDiscovered()>10)
				{
					MainLine = MainLine + " " + (NodeMaze[i].getDiscovered()%10) ;
				}
				else
				{
					MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				}
				
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
		for(int i = 12; i< 15;i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				if(NodeMaze[i].getDiscovered()<10)
				{
					MainLine = MainLine + " ";
				}
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
			}
			
		}
		MainLine = MainLine + "|";
		UnderLine = "+--+--+--+  +";
		
		System.out.println(MainLine);
		System.out.println(UnderLine);
		MainLine = "| ";
	
	}
	
	void printDFS()
	{
		String UnderLine = "+  +--+--+--+";
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
		for(int i = 12; i< 15;i++)
		{
			if(NodeMaze[i].getConnectedNeighbors().contains(NodeMaze[i+1]))
			{
				if(NodeMaze[i].getDiscovered()<10)
				{
					MainLine = MainLine + " ";
				}
				MainLine = MainLine + NodeMaze[i].getDiscovered() + " ";
				
			}
			else
			{
				MainLine = MainLine + NodeMaze[i].getDiscovered() +"| ";
			}
			
		}
		MainLine = MainLine + "|";
		UnderLine = "+--+--+--+  +";
		
		System.out.println(MainLine);
		System.out.println(UnderLine);
		MainLine = "| ";
	
	}
	
	void createMazeString()
	{
		int printHeight = (MazeDimension*2)+1;
		int MazeIndex = 0;
		int tempIndex = 0;
		MazeString = new String[(MazeDimension*2)+1];
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
	
		/** Top and Bottom Openings for Maze **/
		MazeString[0] = Opening;
		MazeString[printHeight-1] = "";
		for(int n = 1; n< MazeDimension; n++)
		{
			MazeString[0] = MazeString[0] + rightClosing;
			MazeString[printHeight -1] = MazeString[printHeight -1] + leftClosing;
		}
		MazeString[printHeight -1 ] = MazeString[printHeight -1] + Opening;
		
		
		/** Maze Left Boundary **/
		for(int o = 1; o< printHeight -1; o++)
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
	
		
		/** Inside The Maze and Right Boundary **/
		for(int i = 1; i < printHeight-1; i++)
		{
			tempIndex = MazeIndex;
		//	System.out.println(MazeIndex);
			for(int j = 0; j<MazeDimension; j++)
			{
				if(MazeIndex < Mazesize-1)
				{
					if(i%2 == 1 || i == 1) //if we are in Vertical Mode(creating |  |  |)
					{
						
						if(NodeMaze[MazeIndex].getConnectedNeighbors().contains(NodeMaze[MazeIndex+1]))
						{
							MazeString[i] = MazeString[i] + noWall; //When there is no wall
						}
						else
						{
							MazeString[i] = MazeString[i] +  rightWall; //When there is a wall
						}
						
						MazeIndex++;
					}
					if(i%2 == 0) //if we are in Horizontal Mode (creating +-+-+)
					{
						if(NodeMaze[MazeIndex].getConnectedNeighbors().contains(NodeMaze[MazeIndex + MazeDimension]))
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
				else
				{
					//Add the very last | to our maze, it's separated to avoid null index issues (no NodeMaze[MazeIndex +1])
					MazeString[printHeight-2] = MazeString[printHeight-2] + rightWall;
				}
				
				
			}
			if(i%2 == 1 || i ==1 ) //Resets MazeIndex to run through the row of Nodes again to create the floor (+-+ +-+-+)
			{
				MazeIndex = tempIndex;
			}
			
		}
		
		 
		
		
		
		
		
	}
	
	void printMazeString()
	{
		for(int i = 0; i< MazeString.length; i++)
		{
			System.out.println(MazeString[i]);
		}
	}
	
	

	
	public static void main(String[]args)
	{ 
		
		Maze m1 = new Maze(15);

		System.out.println("program ended");
		
	}
	
}
