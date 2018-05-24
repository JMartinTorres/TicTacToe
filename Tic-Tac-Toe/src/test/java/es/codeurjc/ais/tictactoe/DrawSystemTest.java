package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class DrawSystemTest extends DrawParameters {

	private WebDriver driverChrome = new ChromeDriver();
	private WebDriver driverChrome2 = new ChromeDriver();

	@BeforeClass
	public static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebApp.start();
	}
	
	@Before
	public void setTurn() {
		player1Turn = true;
	}
	
	@Test
	public void drawTest() {
		
		driverChrome.get("localhost:8080");
		driverChrome2.get("localhost:8080");

		driverChrome.findElement(By.id("nickname")).sendKeys("Player 1");
		driverChrome.findElement(By.id("startBtn")).click();

		driverChrome2.findElement(By.id("nickname")).sendKeys("Player 2");
		driverChrome2.findElement(By.id("startBtn")).click();

		for (int i = 0; i <= 8; i++) {
			if (i % 2 == 0) {
				driverChrome.findElement(By.id("cell-" + parameter.get(i))).click();
			} else {
				driverChrome2.findElement(By.id("cell-" + parameter.get(i))).click();
			}
			player1Turn = !player1Turn;
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
				+ " no es el de empate.", alertP1, "Draw!");
		
		assertEquals("El mensaje mostrado por el alert en el navegador del jugador 2"
				+ " no es el de empate.", alertP2, "Draw!");
		
		/* Los navegadores se cierran para evitar una acumulación indeseada de conexiones 
		 * a la aplicación. */
		
		driverChrome.close();
		driverChrome2.close();
		
	}

}
