package Maze;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {
	
	ArrayList<Node> Neighbors;
	
	HashSet<Node> connectedNeighbors;
	int Nodevalue;
	private Boolean seen; //Grayed out
	private Boolean done; //Blacked out
	
	public Node(int Nodevalue)
	{
		this.Nodevalue = Nodevalue;
		Neighbors = new ArrayList<Node>(); //EDGES or "Shared Walls" with other Nodes
		connectedNeighbors = new HashSet<Node>(); //EDGES THAT ARE "broken walls" with other Nodes
		seen = false;
		done = false;
	}
	
	void addNeighbor(Node n)
	{
		Neighbors.add(n);
	}
	
	int getVal()
	{
		return Nodevalue;
	}
	
	ArrayList<Node> getNeighbors() //get all Neighbors 
	{
		return Neighbors;
	}
	
	void setConnectedNeighbor(int NeighborsIndex)
	{
		connectedNeighbors.add(Neighbors.get(NeighborsIndex)); //We add from list of neighbors our connected neighbors based on NeighborsIndex, the index of our neighbors
		
	}
	void setConnectedNeighbor(Node NeighborNode)
	{
		connectedNeighbors.add(NeighborNode);
	}
	HashSet<Node> getConnectedNeighbors() //get Neighbors with "broken walls"
	{
		return connectedNeighbors;
	}
	
	
	void setSeenTrue()
	{
		seen = true;
	}
	void setDoneTrue()
	{
		done = true;
	}
	Boolean Seen()
	{
		return seen;
	}
	
	Boolean Done()
	{
		return done;
	}
	
	
	
}
