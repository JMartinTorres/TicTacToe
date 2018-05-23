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
public class BoardWinTest extends WinParameters {
	
	private Board testBoard = new Board();
	private int[] result = null;
	
	@Parameter(0) public static int firstCell;
	@Parameter(1) public static int secondCell;
	@Parameter(2) public static int thirdCell;
	
	private int[] parameter = new int[]{firstCell,secondCell,thirdCell};
	
	@Test
	public void checkWin () {
		
		for (int cell: parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}
		
		result = testBoard.getCellsIfWinner("X");
		assertNotNull("El jugador pasado no ha ganado la partida.",result);
		
		assertTrue("Se esperaban las celdas " + parameter + " y se han recibido las celdas " ,
				Arrays.equals(parameter, result));
		
	}
	
	@Test
	public void checkDefeat () {

		for (int cell: parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}
		
		result = testBoard.getCellsIfWinner("O");
		assertNull("El jugador pasado ha ganado la partida.",result);
		
	}

}
