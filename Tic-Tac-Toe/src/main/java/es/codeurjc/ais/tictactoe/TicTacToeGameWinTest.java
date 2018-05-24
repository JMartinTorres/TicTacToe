package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

@RunWith(Parameterized.class)
public class TicTacToeGameWinTest extends WinParameters {

	private TicTacToeGame testedGame = new TicTacToeGame(); // Paso 1 - Creación del objeto TicTacToeGame
	private Connection c1 = mock(Connection.class); // Paso 2 - Creación del objeto Connection 1
	private Connection c2 = mock(Connection.class); // Paso 2 - Creación del objeto Connection 2
	private Player p1 = new Player(0, "X", "Player 1"); // Paso 4 - Creación del objeto Player 1
	private Player p2 = new Player(1, "O", "Player 2"); // Paso 4 - Creación del objeto Player 2

	@Test
	public void player1WinTest() {

		testedGame.addConnection(c1); // Paso 3 - Se añade Connection 1 a TicTacToeGame
		testedGame.addConnection(c2); // Paso 3 - Se añade Connection 2 a TicTacToeGame

		testedGame.addPlayer(p1); // Paso 5 - Se añade Player 1 a TicTacToeGame
		testedGame.addPlayer(p2); // Paso 5 - Se añade Player 2 a TicTacToeGame

		verify(c1, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		// Paso 6 - Se comprueba que Connection 1 recibe el evento JOIN_GAME, con Player
		// 1 y Player 2
		verify(c2, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		// Paso 7 - Se comprueba que Connection 2 recibe el evento JOIN_GAME, con Player
		// 1 y Player 2

		for (int i = 0; i < totalTurns; i++) {
			if (i % 2 == 0) {
				assertTrue("Jugador incorrecto en el turno " + (i+1) + ".", testedGame.checkTurn(0)); // Player 1 tiene id 0
			} else {
				assertTrue("Jugador incorrecto en el turno " + (i+1) + ".", testedGame.checkTurn(1)); // Player 2 tiene id 1
			}
			testedGame.mark(parameter.get(i));
		}
		
		assertTrue("Se esperaba una victoria. ", testedGame.checkWinner().win);

		ArgumentCaptor<Player> playerArgument = ArgumentCaptor.forClass(Player.class);
		ArgumentCaptor<WinnerValue> winnerArgument = ArgumentCaptor.forClass(WinnerValue.class);

		// Comprobación de envío de mensajes de cambio de turno y marcado de celdas

		verify(c1, times(totalTurns)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), playerArgument.capture());
		verify(c1, times(totalTurns)).sendEvent(eq(TicTacToeGame.EventType.MARK), playerArgument.capture());
		verify(c2, times(totalTurns)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), playerArgument.capture());
		verify(c2, times(totalTurns)).sendEvent(eq(TicTacToeGame.EventType.MARK), playerArgument.capture());

		// Comprobación de envío de mensajes de fin de partida con el jugador correcto

		verify(c1, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), winnerArgument.capture());

		assertEquals("El jugador que se envía como ganador no es el correcto.",
				winnerArgument.getValue().player.getName(), p1.getName());

		verify(c2, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), winnerArgument.capture());

		assertEquals("El jugador que se envía como ganador no es el correcto.",
				winnerArgument.getValue().player.getName(), p1.getName());

	}

	@Test
	public void player2WinTest() {

		testedGame.addConnection(c1); // Paso 3 - Se añade Connection 1 a TicTacToeGame
		testedGame.addConnection(c2); // Paso 3 - Se añade Connection 2 a TicTacToeGame

		testedGame.addPlayer(p1); // Paso 5 - Se añade Player 1 a TicTacToeGame
		testedGame.addPlayer(p2); // Paso 5 - Se añade Player 2 a TicTacToeGame

		verify(c1, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		// Paso 6 - Se comprueba que Connection 1 recibe el evento JOIN_GAME, con Player
		// 1 y Player 2
		verify(c2, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		// Paso 7 - Se comprueba que Connection 2 recibe el evento JOIN_GAME, con Player
		// 1 y Player 2

		for (int i = 0; i <= totalTurns; i++) {
			if (i % 2 == 0) {
				assertTrue("Jugador incorrecto en el turno " + (i+1) + ".", testedGame.checkTurn(0)); // Player 1 tiene id 0
				testedGame.mark(parameter.get(i+1));
			} else {
				assertTrue("Jugador incorrecto en el turno " + (i+1) + ".", testedGame.checkTurn(1)); // Player 2 tiene id 1
				testedGame.mark(parameter.get(i-1));
			}
			
		}

		assertTrue("Se esperaba una victoria. ", testedGame.checkWinner().win);

		ArgumentCaptor<Player> playerArgument = ArgumentCaptor.forClass(Player.class);
		ArgumentCaptor<WinnerValue> winnerArgument = ArgumentCaptor.forClass(WinnerValue.class);

		// Comprobación de envío de mensajes de cambio de turno y marcado de celdas

		verify(c1, times(totalTurns + 1)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), playerArgument.capture());
		verify(c1, times(totalTurns + 1)).sendEvent(eq(TicTacToeGame.EventType.MARK), playerArgument.capture());
		verify(c2, times(totalTurns + 1)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), playerArgument.capture());
		verify(c2, times(totalTurns + 1)).sendEvent(eq(TicTacToeGame.EventType.MARK), playerArgument.capture());

		// Comprobación de envío de mensajes de fin de partida con el jugador correcto

		verify(c1, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), winnerArgument.capture());

		assertEquals("El jugador que se envía como ganador no es el correcto.",
				winnerArgument.getValue().player.getName(), p2.getName());

		verify(c2, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), winnerArgument.capture());

		assertEquals("El jugador que se envía como ganador no es el correcto.",
				winnerArgument.getValue().player.getName(), p2.getName());

	}

}
