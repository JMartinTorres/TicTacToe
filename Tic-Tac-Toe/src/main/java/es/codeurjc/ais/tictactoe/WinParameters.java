package es.codeurjc.ais.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class WinParameters {

	protected ArrayList<Integer> parameter;
	protected ArrayList<Integer> loserPositions;
	protected static final ArrayList<Integer> cells = 
			new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
	protected Random rnd = new Random();
	protected int totalTurns;
	
	// Las posiciones pares de los arrays corresponden a celdas de lineas ganadoras
	@Parameters
	public static Collection<Object[]> winPositions() {

		Object[][] winPositions = { { 0,3,1,5,2,7 },
									{ 3,0,4,1,5,7 },
									{ 3,0,4,1,5,8 },
									{ 3,0,4,1,5,6 },
									{ 3,0,4,1,5,7 },
									{ 3,0,4,1,5,8 },
									{ 3,0,4,1,5,6 }
									};

		return Arrays.asList(winPositions);

	}

	@Parameter(0) public static int firstCell;
	@Parameter(1) public static int secondCell;
	@Parameter(2) public static int thirdCell;
	@Parameter(3) public static int fourthCell;
	@Parameter(4) public static int fifthCell;
	@Parameter(5) public static int sixthCell;

	@Before
	public void loadParameters() {
		parameter = new ArrayList<>(Arrays.asList(firstCell,secondCell,thirdCell, fourthCell,
				fifthCell, sixthCell));
		totalTurns = parameter.size()-1;
		loserPositions = (ArrayList<Integer>) cells.clone();
		loserPositions.removeAll(parameter);
	}
	
}
