package es.codeurjc.ais.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class WinParameters {

	protected ArrayList<Integer> parameter;
	protected ArrayList<Integer> loserPositions;
	protected static final ArrayList<Integer> cells = 
			new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
	protected Random rnd = new Random();
	protected int totalTurns;
	

	@Parameters
	public static Collection<Object[]> winPositions() {

		Object[][] winPositions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 6, 4, 2 } };

		return Arrays.asList(winPositions);

	}

	@Parameter(0)
	public static int firstCell;
	@Parameter(1)
	public static int secondCell;
	@Parameter(2)
	public static int thirdCell;

	@Before
	public void loadParameters() {
		parameter = new ArrayList<>(Arrays.asList(firstCell,secondCell,thirdCell));
		totalTurns = parameter.size()*2-1;
		loserPositions = (ArrayList<Integer>) cells.clone();
		loserPositions.removeAll(parameter);
	}
	
}
