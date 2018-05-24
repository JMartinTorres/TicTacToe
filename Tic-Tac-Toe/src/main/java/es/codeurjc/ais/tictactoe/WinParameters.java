package es.codeurjc.ais.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class WinParameters {

	protected ArrayList<Integer> parameter;
	protected int totalTurns;
	
	/* Las posiciones pares de los arrays corresponden a celdas de lineas ganadoras.
	 * Son marcadas en los tests por el jugador que se busca que gane la partida.
	 * El jugador perdedor marca las impares, que no forman ninguna línea. No se
	 * emplean arrays de 6 elementos por ninguna razón concreta. 
	 */
	@Parameters
	public static Collection<Object[]> winPositions() {

		Object[][] winPositions = { { 0,3,1,5,2,7 },
									{ 3,0,4,1,5,7 },
									{ 6,0,7,1,8,4 },
									{ 0,1,3,4,6,8 },
									{ 1,0,4,1,7,5 },
									{ 2,3,5,4,8,6 },
									{ 0,1,4,2,8,5 },
									{ 6,7,4,8,2,5 } };

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
	}
	
}
