package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
@RunWith(Parameterized.class)
public class BoardDrawTest extends DrawParameters {
	
	private Board testBoard = new Board();
	
	@Before 
	public void setParameter () {
	parameter = new int[]{firstCell,secondCell,thirdCell, fourthCell,
			fifthCell, sixthCell, seventhCell, eighthCell, ninethCell};
	}
	
	@Test
	public void checkDraw () {
		
		for (int i = 0; i <= 8; i++) {
			testBoard.getCell(parameter[i]).active = true;
			if (player1Turn) {
				testBoard.getCell(parameter[i]).value = "X";
			} else {
				testBoard.getCell(parameter[i]).value = "O";
			}
			player1Turn = !player1Turn;
		}
		
		assertTrue("Se esperaba un empate.", testBoard.checkDraw());
		assertNull("No debería haber ganado ningún jugador.",
				testBoard.getCellsIfWinner("X"));
		assertNull("No debería haber ganado ningún jugador.", 
				testBoard.getCellsIfWinner("O"));

		
	}
		
	

}
