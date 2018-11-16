package Maze;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class mazeTester {

	Maze program; //holds instance of program; remade each test
	
	//TODO: Code program so it saves output as some sort of data type instead of just a print
	//For now it's a string, ArrayList also considered; discuss
	String output; //output of program when ran in each test
	String expected;
	
	@Before
	public void setUp() throws Exception {
		 
		program = null; //Clears maze / needed?? 
						//Normally I'd initiate the maze here, but the size needs to change
		
	}

	//test testing just the data itself.
	
	@Test
	public void test() {
		program = new Maze(4); //new maze of size 4
		
		expected = null; //temp
		output = null; //temp
		assertEquals(expected, output);
		
	}
	
	//test 1, size 4 maze
	@Test
	public void test1() {
		
		program = new Maze(4); //new maze of size 4
		
		/*expected = "+ +-+-+-+\r\n" + 
				   "|0 1 2 3| \r\n" + 
				   "+-+-+-+ +\r\n" + 
				   "|   |5 4| \r\n" + 
				   "+-+ + +-+\r\n" + 
				   "| | |6 7| \r\n" + 
				   "+ + +-+ +\r\n" + 
				   "|  10 9 8|\r\n" + 
				   "+-+-+-+ +";
		*/
		
		expected = null;
		output = null;
		assertEquals(expected, output);
	}

	//test 2, size 5 maze
	@Test
	public void test2() {
		
		//TODO: Gives error when maze is done at size 3, 5, and goes forever???? with 6.
		program = new Maze(5); //new maze of size 5
		
		/*expected = "+ +-+-+-+\r\n" + 
				     "|0 1 2 3| \r\n" + 
				     "+-+-+-+ +\r\n" + 
				     "|   |5 4| \r\n" + 
				     "+-+ + +-+\r\n" + 
				     "| | |6 7| \r\n" + 
				     "+ + +-+ +\r\n" + 
				     "|  10 9 8|\r\n" + 
				     "+ + +-+ +\r\n" + 
				     "|  10 9 8|\r\n" + 
				     "+-+-+-+ +";
		*/
		expected = null;
		output = null;
		assertEquals(expected, output);
	}
	
}
