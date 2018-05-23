package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class DrawSystemTest extends DrawParameters {

	// private WebDriver driverFirefox = new FirefoxDriver();
	private WebDriver driverChrome = new ChromeDriver();
	private WebDriver driverChrome2 = new ChromeDriver();
	// private WebDriverWait wait = new WebDriverWait(driverChrome, 30); // seconds

	@BeforeClass
	public static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebApp.start();
	}

	@Before
	public void setTurn() {
		player1Turn = true;
		parameter = new int[]{firstCell,secondCell,thirdCell, fourthCell,
				fifthCell, sixthCell, seventhCell, eighthCell, ninethCell};
	}

	@AfterClass
	public static void closeApp() {
		WebApp.stop();
	}
	
	@Test
	public void testDraw() {

		driverChrome.get("localhost:8083");
		driverChrome2.get("localhost:8083");

		driverChrome.findElement(By.id("nickname")).sendKeys("Player 1");
		driverChrome.findElement(By.id("startBtn")).click();

		driverChrome2.findElement(By.id("nickname")).sendKeys("Player 2");
		driverChrome2.findElement(By.id("startBtn")).click();

		for (int i = 0; i <= 8; i++) {
			if (player1Turn) {
				driverChrome.findElement(By.id("cell-" + parameter[i])).click();
			} else {
				driverChrome2.findElement(By.id("cell-" + parameter[i])).click();
			}
			player1Turn = !player1Turn;
		}

		assertTrue("El mensaje mostrado por el alert no es de empate. ",
				driverChrome.switchTo().alert().getText().equals
				(driverChrome2.switchTo().alert().getText()));
		
		driverChrome.close();
		driverChrome2.close();
		
	}

}
