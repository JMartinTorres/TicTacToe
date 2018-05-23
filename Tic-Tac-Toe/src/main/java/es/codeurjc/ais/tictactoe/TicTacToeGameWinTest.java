package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.mockito.ArgumentCaptor;

public class TicTacToeGameWinTest extends DrawParameters {
	
	private TicTacToeGame testedGame = new TicTacToeGame(); // Paso 1 - Creación del objeto TicTacToeGame
	private Connection c1 = mock(Connection.class); // Paso 2 - Creación del objeto Connection 1
	private Connection c2 = mock(Connection.class); // Paso 2 - Creación del objeto Connection 2
	private Player p1 = new Player(0, "X", "Player 1"); // Paso 4 - Creación del objeto Player 1
	private Player p2 = new Player(1, "O", "Player 2"); // Paso 4 - Creación del objeto Player 2
	
	
	@Before 
	public void setParameter () {
	parameter = new int[]{firstCell,secondCell,thirdCell, fourthCell,
			fifthCell, sixthCell, seventhCell, eighthCell, ninethCell};
	}
	
	@Test
	public void TicTacToeGameTestDraw () { 
		
		int[] parametro = {0,2,1,3,5,4,6,7,8};
		int turn = 1;
		
		testedGame.addConnection(c1); // Paso 3 - Se añade Connection 1 a TicTacToeGame
		testedGame.addConnection(c2); // Paso 3 - Se añade Connection 2 a TicTacToeGame
    
		testedGame.addPlayer(p1); // Paso 5 - Se añade Player 1 a TicTacToeGame
		testedGame.addPlayer(p2); // Paso 5 - Se añade Player 2 a TicTacToeGame
		
		verify(c1, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1,p2))); // Paso 6 - Se comprueba que Connection 1 recibe el evento JOIN_GAME, con Player 1 y Player 2
		verify(c2, times(2)).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(p1,p2))); // Paso 7 - Se comprueba que Connection 2 recibe el evento JOIN_GAME, con Player 1 y Player 2
		
		for (int cell : parametro) {
			if (turn<=9) {
				if (turn % 2 == 1) {
					assertTrue("Jugador incorrecto en el turno " + turn + ".", testedGame.checkTurn(0)); // Player 1 tiene id 0
				} else {
					assertTrue("Jugador incorrecto en el turno " + turn + ".", testedGame.checkTurn(1)); // Player 2 tiene id 1
				}
				testedGame.mark(cell);
				turn++;
			} else {
				assertFalse("No se espera ninguna victoria. ", testedGame.checkWinner().win);
				assertTrue("Se espera un empate. ", testedGame.checkDraw());
			}
				
		}
		
		ArgumentCaptor<Player> argument = ArgumentCaptor.forClass(Player.class);
		
		verify(c1, times(9)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), argument.capture());
		verify(c2, times(9)).sendEvent(eq(TicTacToeGame.EventType.SET_TURN), argument.capture());
		verify(c1, times(9)).sendEvent(eq(TicTacToeGame.EventType.MARK), argument.capture());
		verify(c2, times(9)).sendEvent(eq(TicTacToeGame.EventType.MARK), argument.capture());
		verify(c1, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), eq(null));
		verify(c2, times(1)).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), eq(null));
		
	}
	
}
