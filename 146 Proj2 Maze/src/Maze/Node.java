package Maze;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {
	
	ArrayList<Node> Neighbors;
	
	HashSet<Node> connectedNeighbors;
	ArrayList<Node> connectedNeighborsList;
	int Nodevalue;
	Boolean seen; //Grayed out
	Boolean done; //Blacked out
	int discovered;
	int finished;
	
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
		connectedNeighborsList.add(Neighbors.get(NeighborsIndex));
	}
	void setConnectedNeighbor(Node NeighborNode)
	{
		connectedNeighbors.add(NeighborNode);
		connectedNeighborsList.add(NeighborNode);
	}
	HashSet<Node> getConnectedNeighbors() //get Neighbors with "broken walls"
	{
		return connectedNeighbors;
	}
	ArrayList<Node> getConnectedNeighborsList() //get Neighbors with "broken walls"
	{
		return connectedNeighborsList;
	}
	
	
	void setSeen(Boolean b)
	{
		seen = b;
	}
	void setDone(Boolean b)
	{
		done = b;
	}
	Boolean Seen()
	{
		return seen;
	}
	
	Boolean Done()
	{
		return done;
	}
	
	void setDiscovered(int n)
	{
		discovered = n;
	}
	void setFinished(int m)
	{
		finished = m;
	}
	
	
}
