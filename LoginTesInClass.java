
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import Template.General;

public class LoginTesInClass {
	private WebDriver driver;
	private String baseUrl = "http://hrm.seleniumminutes.com";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();	}
	
	@Test
	public void testValidLogin() {	
		General.login(driver, "admin", "Password");
		
		String actualGreeting = driver.findElement(By.id("welcome")).getText();
		assertEquals("Welcome Admin", actualGreeting);
	}
	
	@Test
	public void testInvalidUsername() {
		String expected = "Invalid credentials";
		String invalid = "abcdef";
		
		General.login(driver, invalid, "Password");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	@Test
	public void testInvalidPassword() {
		String expected = "Invalid credentials";
		String invalid = "abcdef";
		
		General.login(driver, "admin", invalid);
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	@Test
	public void testEmptyUsername() {
		String expected = "Username cannot be empty";
		
		General.login(driver, "", "Password");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	@Test
	public void testEmptyPassword() {
		String expected = "Password cannot be empty";
		
		General.login(driver, "admin", "");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}

}
