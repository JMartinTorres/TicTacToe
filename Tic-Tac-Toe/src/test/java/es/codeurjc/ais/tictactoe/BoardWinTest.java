package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BoardWinTest extends WinParameters {

	private Board testBoard = new Board();
	private int[] result;
	private ArrayList<Integer> line; // Posiciones de tres celdas que forman una línea

	@Test
	public void player1WinTest() {
		
		line = new ArrayList<>(Arrays.asList(parameter.get(0),parameter.get(2),parameter.get(4)));
		
		for (int cell : line) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "X";
		}

		// Comprobación checkDraw

		assertFalse("No se debería haber producido un empate", testBoard.checkDraw());

		// Comprobación getCellsIfWinner

		result = testBoard.getCellsIfWinner("X");
		assertNotNull("El jugador pasado debería haber ganado la partida.", result);

		assertNull("El jugador pasado no debería haber ganado la partida.", testBoard.getCellsIfWinner("O"));

		assertEquals("Se esperaban las celdas " + line + " y se han recibido las celdas ",
				line, new ArrayList<Integer>(){{ for (int i : result) add(i); }});

	}

	@Test
	public void player2WinTest() {
		
		line = new ArrayList<>(Arrays.asList(parameter.get(0),parameter.get(2),parameter.get(4)));

		for (int cell : line) {
			testBoard.getCell(cell).active = true;
			testBoard.getCell(cell).value = "O";
		}

		// Comprobación checkDraw

		assertFalse("No se debería haber producido un empate", testBoard.checkDraw());

		// Comprobación getCellsIfWinner

		result = testBoard.getCellsIfWinner("O");
		assertNotNull("El jugador pasado debería haber ganado la partida.", result);

		assertNull("El jugador pasado no debería haber ganado la partida.", testBoard.getCellsIfWinner("X"));

		assertEquals("Se esperaban las celdas " + line + " y se han recibido las celdas " + result,
				line, new ArrayList<Integer>(){{ for (int i : result) add(i); }});

	}

}
