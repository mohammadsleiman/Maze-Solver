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
						// Clears maze / needed??
						// Normally I'd initiate the maze here, but the size needs to change

	}

	@Test
	public void test1() //tests a size 4 maze's DFS
	{
		program = new Maze(4); // new maze of size 4
		program.DepthFirstSearch();
		output = program.printMazeString();
		System.out.println(program.printMazeString());
		String expected = "+ +-+-+-+ |1 2 3 4| +-+-+-+ + |8 7|6 5| +-+ + +-+ |3|6|7 8| + + +-+ + |2 1 0 9| +-+-+-+ + ";
		
		assertEquals(expected, output);

	}

	// test 1, size 4 maze
	@Test
	public void test2() {

		program = new Maze(4); // new maze of size 4
		
		String[] expected = {"+ +-+-+-+\r\n" , 
				"|# # # #|\r\n" , 
				"+-+-+-+ +\r\n" , 
				"|   |# #|\r\n" ,
				"+-+ + +-+\r\n" , 
				"| | |# #|\r\n" , 
				"+ + +-+ +\r\n" , 
				"|      #|\r\n" , 
				"+-+-+-+ +"};
		output = null;
		assertArrayEquals(expected, output);
	}

	// test 2, size 5 maze
	@Test
	public void test3() {

		program = new Maze(15); // new maze of size 5
		String[] expected = { "+ +-+-+-+-+-+-+-+-+-+-+-+-+-+-+\r\n" + 
						   	  "|# # # # # # # # # #|   |     |\r\n" +
						   	  "+-+-+-+ +-+-+-+-+-+ + +-+ +-+-+\r\n" + 
						   	  "|   | | |   |# # # #|     |   |\r\n" + 
						   	  "+ + + +-+ +-+ +-+-+-+-+ +-+-+ +\r\n" + 
						   	  "| |   |     |#| |# # # # # # #|\r\n" + 
						   	  "+ + +-+ + +-+ + + + +-+ +-+-+ +\r\n" + "| |     | |# #|# #| | | | |# #|\r\n"
				+ "+ +-+-+-+ + +-+ +-+-+ +-+ + +-+\r\n" + "| |   |   |# # #|   | |# #|# #|\r\n"
				+ "+ + + + + + +-+-+ +-+ + + +-+ +\r\n" + "|   | | | |   |   |   |#|# # #|\r\n"
				+ "+ +-+ + +-+-+-+-+ + + + +-+ + +\r\n" + "| |   |           | |# #| | | |\r\n"
				+ "+ +-+-+-+-+-+-+-+ +-+ +-+ +-+-+\r\n" + "| |   | |         |# #|       |\r\n"
				+ "+ + +-+ + +-+ +-+ + +-+-+-+ +-+\r\n" + "|   |     | |   | |#|# # #|   |\r\n"
				+ "+-+-+-+ + + +-+ +-+ + +-+ + +-+\r\n" + "|   |   |     |   |# #|# #|   |\r\n"
				+ "+ +-+-+ +-+-+ + +-+ +-+ +-+ +-+\r\n" + "|     | | |   |   | | |# #|   |\r\n"
				+ "+ +-+ + + + +-+-+-+-+ +-+ + + +\r\n" + "| |   |   |     |   |   |#| | |\r\n"
				+ "+-+ +-+ + + +-+ + +-+ + + +-+ +\r\n" + "|       | |   | |   | | |# # #|\r\n"
				+ "+-+ +-+-+-+-+ + + +-+ +-+-+-+ +\r\n" + "|     |   |   |           |# #|\r\n"
				+ "+ + +-+ +-+-+ +-+-+-+-+-+ + +-+\r\n" + "| |       |       |        # #|\r\n"
				+ "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ +" };
		output = program.getMazeString();
		assertArrayEquals(expected, output);
	}


}
