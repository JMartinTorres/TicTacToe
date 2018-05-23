package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
@RunWith(Parameterized.class)
public class BoardPlayerWinTest extends WinParameters {
	
	private Board testBoard = new Board();
	private int[] result = null;
	
	@Before 
	public void setParameter () {
	parameter = new int[]{firstCell,secondCell,thirdCell};
	}
	
	@Test
	public void checkPlayer1Win () {
		
		for (int cell: parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}
		
		result = testBoard.getCellsIfWinner("X");
		assertNotNull("El jugador pasado debería haber ganado la partida.",result);
		
		assertNull("El jugador pasado no debería haber ganado la partida.",
				testBoard.getCellsIfWinner("O"));
		
		assertTrue("Se esperaban las celdas " + parameter + " y se han recibido las celdas " ,
				Arrays.equals(parameter, result));
		
	}	
	
	@Test
	public void checkPlayer2Win () {
		
		for (int cell: parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "O";
		}
		
		result = testBoard.getCellsIfWinner("O");
		assertNotNull("El jugador pasado debería haber ganado la partida.",result);
		
		assertNull("El jugador pasado no debería haber ganado la partida.",
				testBoard.getCellsIfWinner("X"));
		
		assertTrue("Se esperaban las celdas " + parameter + " y se han recibido las celdas " ,
				Arrays.equals(parameter, result));
		
	}	
	
}
