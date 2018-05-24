package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
@RunWith(Parameterized.class)
public class BoardDrawTest extends DrawParameters {
	
	private Board testBoard = new Board();

	@Test
	public void drawTest () {
		
		for (int i = 0; i <= 8; i++) {
			testBoard.getCell(parameter.get(i)).active = true;
			if (player1Turn) {
				testBoard.getCell(parameter.get(i)).value = "X";
			} else {
				testBoard.getCell(parameter.get(i)).value = "O";
			}
			player1Turn = !player1Turn;
		}
		
		// Comprobación checkDraw

		assertTrue("Se debería haber producido un empate", testBoard.checkDraw());

		// Comprobación getCellsIfWinner
		
		assertNull("No debería haber ganado ningún jugador.",
				testBoard.getCellsIfWinner("X"));
		assertNull("No debería haber ganado ningún jugador.", 
				testBoard.getCellsIfWinner("O"));

		
	}
		
	

}
