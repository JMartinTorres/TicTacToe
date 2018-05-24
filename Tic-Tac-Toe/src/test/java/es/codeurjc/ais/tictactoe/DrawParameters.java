package es.codeurjc.ais.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class DrawParameters {
	
	protected boolean player1Turn = true;
	protected ArrayList<Integer> parameter;
	
	/* Se cumple con todos los arrays siguientes que si el jugador que tiene el primer turno
	 * marca las celdas correspondientes a las posiciones pares de los mismos y el otro jugador
	 * elige aquellas en las posiciones impares, se alcanza una situación de empate. */
	@Parameters (name = "Celdas Player 1 [{0},{2},{4},{6},{8}], celdas Player 2 [{1},{3},{5},{7}]")
	public static Collection<Object[]> DrawPositions() {
		
		Object [][] drawPositions = { {0,1,2,3,5,4,6,8,7},
									  {0,1,2,3,4,6,5,8,7},
									  {0,2,1,3,5,4,6,7,8},
									  {0,2,1,3,5,4,6,8,7},
									  {1,0,2,5,3,6,4,7,8}, 
									  {1,0,3,2,5,4,6,7,8},
									  {1,0,3,2,4,5,6,7,8},
									  {1,0,4,2,5,3,6,7,8} };

		return Arrays.asList(drawPositions);

	}

	@Parameter(0) public static int firstCell;
	@Parameter(1) public static int secondCell;
	@Parameter(2) public static int thirdCell;
	@Parameter(3) public static int fourthCell;
	@Parameter(4) public static int fifthCell;
	@Parameter(5) public static int sixthCell;
	@Parameter(6) public static int seventhCell;
	@Parameter(7) public static int eighthCell;
	@Parameter(8) public static int ninethCell;
	
	@Before
	public void loadParameters() {
		parameter = new ArrayList<>(Arrays.asList(firstCell,secondCell,thirdCell, fourthCell,
				fifthCell, sixthCell, seventhCell, eighthCell, ninethCell));
	}
	
	

}
