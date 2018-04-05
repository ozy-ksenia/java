
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginLogOut {
	private WebDriver driver; // WebDriver is container which contains a driver
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();  // FirefoxDriver() is object
		baseUrl = "http://hrm.seleniumminutes.com"; // object
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		driver.get(baseUrl);
		driver.findElement(By.id("txtUsername")).sendKeys("admin");           //input username
		driver.findElement(By.id("txtPassword")).sendKeys("Password");        //input password
		driver.findElement(By.id("btnLogin")).click();                        //click Login button
		
		String actualWelcome = driver.findElement(By.id("welcome")).getText(); //receive a text "Welcome ...." 
		assertEquals("Welcome Admin", actualWelcome);                          //expected and actual results
	
		// Log out
		sleep(1);
		driver.findElement(By.id("welcome")).click();
		sleep(1);
		driver.findElement(By.linkText("Logout")).click();
	}
	
	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
