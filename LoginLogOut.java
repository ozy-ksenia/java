
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginLogOut {
	private WebDriver driver;
	private String baseUrl = "http://.........com";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();  
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		driver.get(baseUrl);
		driver.findElement(By.id("txtUsername")).sendKeys("test");          
		driver.findElement(By.id("txtPassword")).sendKeys("test");        
		driver.findElement(By.id("btnLogin")).click();                       
		
		String actualWelcome = driver.findElement(By.id("welcome")).getText(); 
		assertEquals("Welcome Admin", actualWelcome);                         
	
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
