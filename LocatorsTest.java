import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Template.General;

public class LocatorsTest {
	WebDriver driver;
	String baseUrl = "http://hrm-online.portnov.com";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		General.login(driver, "admin", "password");
		driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
		// OTHER Locators
		// driver.findElement(By.className("firstLevelMenu")).click();
		// driver.findElement(By.cssSelector("#menu_leave_viewLeaveModule")).click();
		
		// looking for LOGO
		String width = driver.findElement(By.cssSelector("#branding>img")).getAttribute("width");
		String height = driver.findElement(By.xpath("//*[@id='branding']/img")).getAttribute("height");
		
		// not the best way
		//assertEquals(283, (int) Integer.valueOf(width));

		// better
		assertEquals("283", width);
		assertEquals("56", height);
				
		// positional
		Dimension size = driver.findElement(By.tagName("img")).getSize();
		assertEquals(283, size.width);
		assertTrue(size.height == 56);
		

		Point location = driver.findElement(By.xpath("//*[@id='branding']//*[@alt='OrangeHRM']")).getLocation();
		// top left corner
		assertTrue(location.x < 40);
		assertTrue(location.y < 40);
		
		// check that logo fits within left half of the document
		
		int documentWidth = driver.findElement(By.tagName("body")).getSize().width;
		
		// width + x
		int logoWidth = driver.findElement(By.cssSelector("[alt='OrangeHRM']")).getSize().width;
		int logoX = driver.findElement(By.tagName("img")).getLocation().x;
		int logoRightCorner = logoWidth + logoX;
		
		assertTrue(logoRightCorner < documentWidth / 2);
		
	}

}
