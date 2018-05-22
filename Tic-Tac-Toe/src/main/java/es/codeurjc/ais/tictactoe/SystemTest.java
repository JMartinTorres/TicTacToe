package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SystemTest {

	// private WebDriver driverFirefox = new FirefoxDriver();
	private WebDriver driverChrome = new ChromeDriver();
	private WebDriver driverChrome2 = new ChromeDriver();
	// private WebDriverWait wait = new WebDriverWait(driverChrome, 30); // seconds
	private boolean player1Turn;

	@BeforeClass
	public static void setChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	}

	@Before
	public void setTurn() {
		player1Turn = true;
	}

	@Test
	public void testDraw() {

		int[] parametro = { 0, 2, 1, 3, 5, 4, 6, 7, 8 };

		driverChrome.get("localhost:8083");
		driverChrome2.get("localhost:8083");

		driverChrome.findElement(By.id("nickname")).sendKeys("Nick Jonas");
		driverChrome.findElement(By.id("startBtn")).click();

		driverChrome2.findElement(By.id("nickname")).sendKeys("Joe Jonas");
		driverChrome2.findElement(By.id("startBtn")).click();

		for (int i = 0; i <= 8; i++) {
			if (player1Turn) {
				driverChrome.findElement(By.id("cell-" + parametro[i])).click();
			} else {
				driverChrome2.findElement(By.id("cell-" + parametro[i])).click();
			}
			player1Turn = !player1Turn;
		}

		assertTrue("El mensaje mostrado por el alert no es de empate. ",
				driverChrome.switchTo().alert().getText().equals
				(driverChrome2.switchTo().alert().getText()));

	}
	

}
