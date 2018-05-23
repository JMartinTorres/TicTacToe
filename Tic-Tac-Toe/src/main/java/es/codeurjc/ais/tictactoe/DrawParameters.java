package es.codeurjc.ais.tictactoe;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DrawParameters {
	
	protected boolean player1Turn = true;
	protected int[] parameter = new int[]{};

	@Parameter(0) public static int firstCell;
	@Parameter(1) public static int secondCell;
	@Parameter(2) public static int thirdCell;
	@Parameter(3) public static int fourthCell;
	@Parameter(4) public static int fifthCell;
	@Parameter(5) public static int sixthCell;
	@Parameter(6) public static int seventhCell;
	@Parameter(7) public static int eighthCell;
	@Parameter(8) public static int ninethCell;
	
	@Parameters
	public static Collection<Object[]> DrawPositions() {
		
		Object [][] drawPositions = { {0,2,1,3,5,4,6,7,8}, 
									  {1,0,2,5,3,6,4,7,8 },
									  };

		return Arrays.asList(drawPositions);

	}

}
