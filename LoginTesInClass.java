
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import Template.General;

public class LoginTesInClass {
	private WebDriver driver;
	private String baseUrl = "http://...........com";

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
	
	// positive test
	@Test
	public void testValidLogin() {	
		General.login(driver, "test", "test");
		
		String actualGreeting = driver.findElement(By.id("welcome")).getText();
		assertEquals("Welcome Admin", actualGreeting);
	}
	
	// invalid username
	@Test
	public void testInvalidUsername() {
		String expected = "Invalid credentials";
		String invalid = "qwerty";
		
		General.login(driver, invalid, "test");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	// invalid password
	@Test
	public void testInvalidPassword() {
		String expected = "Invalid credentials";
		String invalid = "qwerty";
		
		General.login(driver, "test", invalid);
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	// empty username
	@Test
	public void testEmptyUsername() {
		String expected = "Username cannot be empty";
		
		General.login(driver, "", "test");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}
	
	// empty password
	@Test
	public void testEmptyPassword() {
		String expected = "Password cannot be empty";
		
		General.login(driver, "test", "");
		
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		assertEquals(expected, actualMessage);
	}

}
