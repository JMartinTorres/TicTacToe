package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
@RunWith(Parameterized.class)
public class BoardDrawTest extends DrawParameters {
	
	private Board testBoard = new Board();
	private int[] result = null;
	
	@Parameter(0) public static int firstCell;
	@Parameter(1) public static int secondCell;
	@Parameter(2) public static int thirdCell;
	@Parameter(3) public static int fourthCell;
	@Parameter(4) public static int fifthCell;
	@Parameter(5) public static int sixthCell;
	@Parameter(6) public static int seventhCell;
	@Parameter(7) public static int eighthCell;
	@Parameter(8) public static int ninethCell;
	
	private int[] parameter = new int[]{firstCell,secondCell,thirdCell, fourthCell,
			fifthCell, sixthCell, seventhCell, eighthCell, ninethCell};
	
	@Test
	public void checkDraw () {
		
	}
		
	

}
