package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BoardWinTest extends WinParameters {

	private Board testBoard = new Board();
	private int[] result;

	@Test
	public void checkPlayer1Win() {

		for (int cell : parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}

		// Comprobación checkDraw

		assertFalse("No se debería haber producido un empate", testBoard.checkDraw());

		// Comprobación getCellsIfWinner

		result = testBoard.getCellsIfWinner("X");
		assertNotNull("El jugador pasado debería haber ganado la partida.", result);

		assertNull("El jugador pasado no debería haber ganado la partida.", testBoard.getCellsIfWinner("O"));

		assertEquals("Se esperaban las celdas " + parameter + " y se han recibido las celdas ",
				parameter, new ArrayList<Integer>(){{ for (int i : result) add(i); }});

	}

	@Test
	public void checkPlayer2Win() {

		for (int cell : parameter) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "O";
		}

		// Comprobación checkDraw

		assertFalse("No se debería haber producido un empate", testBoard.checkDraw());

		// Comprobación getCellsIfWinner

		result = testBoard.getCellsIfWinner("O");
		assertNotNull("El jugador pasado debería haber ganado la partida.", result);

		assertNull("El jugador pasado no debería haber ganado la partida.", testBoard.getCellsIfWinner("X"));

		assertEquals("Se esperaban las celdas " + parameter + " y se han recibido las celdas " + result,
				parameter, new ArrayList<Integer>(){{ for (int i : result) add(i); }});

	}

}
