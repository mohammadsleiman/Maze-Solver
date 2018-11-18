package Maze;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

//J-Unit tests for our Maze program

public class mazeTester {

	Maze program; // holds instance of program; remade each test
	String output; // output of program when ran in each test

	@Before
	public void setUp() throws Exception {

		program = null;

	}

	@Test
	public void test1A() //tests a size 4 maze's DFS
	{
		program = new Maze(4); // new maze of size 4
		program.DepthFirstSearch();
		output = program.printMazeString();
		System.out.println(program.printMazeString());
		String expected = "+ +-+-+-+ |1 2 3 4| +-+-+-+ + |8 7|6 5| +-+ + +-+ |3|6|7 8| + + +-+ + |2 1 0 9| +-+-+-+ + ";
		
		assertEquals(expected, output);

	}
	
	@Test
	public void test1B() //tests a size 4 maze's BFS
	{
		program = new Maze(4); // new maze of size 4
		program.BreadthFirstSearch();
		output = program.printMazeString();
		System.out.println(program.printMazeString());
      String expected = "+ +-+-+-+ |# # # #| +-+-+-+ + |   |# #| +-+ + +-+ | | |# #| + + +-+ + |      #| +-+-+-+ +";
		//String expected = "+ +-+-+-+ |# # # #| +-+-+-+ + |   |# #| +-+ + +-+ | | |# #| + + +-+ + |      #| +-+-+-+ +";
		assertEquals(expected, output);
		
	}

	@Test
	public void test2A() //tests a size 10 maze's DFS
	{
		program = new Maze(10); // new maze of size 10
		program.DepthFirstSearch();
		output = program.printMazeString();
		System.out.println(program.printMazeString());
		String expected = "+ +-+-+-+-+-+-+-+-+-+ |1 2 3 4 7 8 9 0 1 2| +-+-+-+ +-+-+-+ +-+ + |6|1 0|5|8 7|8 7|0|3| + + + +-+-+ + +-+ +-+ |3 2|9 2 3 6|9|0|3 4| + +-+ +-+ +-+ + + + + |4|7 8 5|4|1 0|9 2|5| +-+ +-+ +-+ +-+ +-+ + |5 6|1|6 7|2 7 8|7 6| + +-+ +-+ + +-+-+ +-+ |4 3 0|1|8|3 4|7|8 9| +-+-+ + +-+-+-+ +-+ + |1|0 9|0 9 8|9 6 3 0| + +-+ + +-+ + +-+ + + |0|7 8|3|4 3|0|1|4|1| + + + + + + + + +-+-+ |9 8|3|4|5|2 1|0|9|6| + + +-+ +-+-+-+ + + + |4|7 6 5 6 7 8 9 4 5| +-+-+-+-+-+-+-+-+-+ + ";
		
		assertEquals(expected, output);

	}

	@Test
	public void test2B() //tests a size 10 maze's BFS
	{
		program = new Maze(10); // new maze of size 10
		program.BreadthFirstSearch();
		output = program.printMazeString();
		System.out.println(program.printMazeString());
		String expected = "";
		assertEquals(expected, output);
		
	}

}
