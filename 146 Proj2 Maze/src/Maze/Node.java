package Maze;

import java.util.ArrayList;

public class Node {
	
	ArrayList<Node> edge;
	int Nodevalue;
	
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
