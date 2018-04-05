
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTestWait {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 5);
		baseUrl = "http://hrm.seleniumminutes.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void validLogin() {
		driver.get(baseUrl);
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Password");
		driver.findElement(By.id("btnLogin")).click();
		
		String actualWelcome = driver.findElement(By.id("welcome")).getText();
		assertEquals("Welcome Admin", actualWelcome);
		
		// -- Log out
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("welcome"))).click();
		driver.findElement(By.linkText("Logout")).click();
		
	}


}
