package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
public class GetCellsIfWinnerTest {
	
	private Board testBoard = new Board();
	private int[] result = null;
	
	@Test
	public void checkWin () {
		
		int[] parametro = {0,1,2};

		for (int cell: parametro) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}
		
		result = testBoard.getCellsIfWinner("X");
		assertNotNull("El jugador pasado no ha ganado la partida.",result);
		
		assertTrue("Se esperaban las celdas " + parametro + " y se han recibido las celdas " ,
				Arrays.equals(parametro, result));
		
	}
	
	@Test
	public void checkDefeat () {
		
		int[] parametro = {0,1,2};

		for (int cell: parametro) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}
		
		result = testBoard.getCellsIfWinner("O");
		assertNull("El jugador pasado ha ganado la partida.",result);
		
	}

}
