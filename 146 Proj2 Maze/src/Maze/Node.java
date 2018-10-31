package Maze;

import java.util.ArrayList;

public class Node {
	
	ArrayList<Node> edge;
	int Nodevalue;
	Boolean seen; //Grayed out
	Boolean done; //Blacked out
	
	public Node(int Nodevalue)
	{
		this.Nodevalue = Nodevalue;
	}
	
	void addEdge(Node n)
	{
		edge.add(n);
	}
	
	int getVal()
	{
		return Nodevalue;
	}
}
