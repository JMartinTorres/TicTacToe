package es.codeurjc.ais.tictactoe;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class WinSystemTest extends WinParameters {

	// private WebDriver driverFirefox = new FirefoxDriver();
	private WebDriver driverChrome = new ChromeDriver();
	private WebDriver driverChrome2 = new ChromeDriver();
	//private WebDriverWait wait = new WebDriverWait(driverChrome, 3);
	//private WebDriverWait wait2 = new WebDriverWait(driverChrome2, 3);

	@BeforeClass
	public static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebApp.start();
	}
	
//	private void waitForAlert(WebDriver driver)
//	{
//		   int i=0;
//		   while(i++<5)
//		   {
//		        try
//		        {
//		            Alert alert = driver.switchTo().alert();
//		            break;
//		        }
//		        catch(NoAlertPresentException e)
//		        {
//		          try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//		          continue;
//		        }
//		   }
//		}
	
	@Test
	public void player1WinTest() {
		
//		driverChrome.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//		driverChrome2.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		driverChrome.get("localhost:8083");
		driverChrome2.get("localhost:8083");

		driverChrome.findElement(By.id("nickname")).sendKeys("Player 1");
		driverChrome.findElement(By.id("startBtn")).click();

		driverChrome2.findElement(By.id("nickname")).sendKeys("Player 2");
		driverChrome2.findElement(By.id("startBtn")).click();
		
		for (int i = 0; i < totalTurns; i++) {
			if (i % 2 == 0) {
				driverChrome.findElement(By.id("cell-" + parameter.get(i))).click();
			} else {
				driverChrome2.findElement(By.id("cell-" + parameter.get(i))).click();
			}

		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String alertP1 = driverChrome.switchTo().alert().getText();
		String alertP2 = driverChrome2.switchTo().alert().getText();
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 1"
				+ " no es el de empate.", alertP1, "Player 1 wins! Player 2 looses.");
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 2"
				+ " no es el de empate.", alertP2, "Player 1 wins! Player 2 looses.");
		
		driverChrome.close();
		driverChrome2.close();
		
	}
	
//	public int nonWinnerCell (ArrayList<Integer> player1Cells) {
//		
//		for (Object line: winPositions()) {
//			Integer[] winPosition = (Integer []) line;
//			
//			for (Integer allowedCell : loserPositions) {
//				if (!parameter.contains(allowedCell) && 
//						!player1Cells.contains(allowedCell)) {
//					return allowedCell;
//				}
//			}
//			
//		}
//		
//		return 1;
//	}
	
	@Test
	public void player2WinTest() {
		
		ArrayList<Integer> player1Cells = new ArrayList<>();
		
		driverChrome.get("localhost:8083");
		driverChrome2.get("localhost:8083");

		driverChrome.findElement(By.id("nickname")).sendKeys("Player 1");
		driverChrome.findElement(By.id("startBtn")).click();

		driverChrome2.findElement(By.id("nickname")).sendKeys("Player 2");
		driverChrome2.findElement(By.id("startBtn")).click();
		
		for (int i = 0; i <= totalTurns; i++) {
			if (i % 2 == 0) {
				driverChrome.findElement(By.id("cell-" + parameter.get(i+1))).click();
			} else {
				driverChrome2.findElement(By.id("cell-" + parameter.get(i-1))).click();
			}

		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String alertP1 = driverChrome.switchTo().alert().getText();
		String alertP2 = driverChrome2.switchTo().alert().getText();
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 1"
				+ " no es el de empate.", alertP1, "Player 2 wins! Player 1 looses.");
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 2"
				+ " no es el de empate.", alertP2, "Player 2 wins! Player 1 looses.");
		
		driverChrome.close();
		driverChrome2.close();
		
	}

}
