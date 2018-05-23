package es.codeurjc.ais.tictactoe;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DrawParameters {
	
	protected boolean player1Turn;

	@Parameters
	public static Collection<Object[]> DrawPositions() {

		Object [][] drawPositions = { { 1, 0, 2, 5, 3, 6, 4, 7, 8 } };

		return Arrays.asList(drawPositions);

	}

}
