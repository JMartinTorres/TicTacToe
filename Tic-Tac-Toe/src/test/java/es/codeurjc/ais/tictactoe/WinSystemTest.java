package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class WinSystemTest extends WinParameters {

	private WebDriver driverChrome = new ChromeDriver();
	private WebDriver driverChrome2 = new ChromeDriver();

	@BeforeClass
	public static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebApp.start();
	}
	
	
	@Test
	public void player1WinTest() {
		
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
		
		// Se hace una espera de un segundo para asegurar que aparece el mensaje del alert.
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String alertP1 = driverChrome.switchTo().alert().getText();
		String alertP2 = driverChrome2.switchTo().alert().getText();
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 1"
				+ " no es el de victoria del jugador 1.", alertP1, "Player 1 wins! Player 2 looses.");
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 2"
				+ " no es el de victoria del jugador 1.", alertP2, "Player 1 wins! Player 2 looses.");
		
		driverChrome.close();
		driverChrome2.close();
		
	}
	
	@Test
	public void player2WinTest() {
		
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
				+ " no es el de victoria del jugador 2.", alertP1, "Player 2 wins! Player 1 looses.");
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 2"
				+ " no es el de victoria del jugador 2.", alertP2, "Player 2 wins! Player 1 looses.");
		
		/* Los navegadores se cierran para evitar una acumulación indeseada de conexiones 
		 * a la aplicación. */
		
		driverChrome.close();
		driverChrome2.close();
		
	}

}
