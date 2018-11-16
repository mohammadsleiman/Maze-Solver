package Maze;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class mazeTester {

	Maze program; //holds instance of program; remade each test
	ArrayList<Node> output; //output of program when ran in each test
	ArrayList<Node> expected;
	
	@Before
	public void setUp() throws Exception {
		
		program = new Maze(4); //Makes a new maze
		
	}

	@Test
	public void test1() {
		
		expected = null;
		output = null;
		assertEquals(expected, output);
	}

}
