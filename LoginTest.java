import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {
	private WebDriver driver;
	private String baseUrl;

   @Before
   public void setUp() throws Exception {
	   System.setProperty("webdriver.gecko.driver", "geckodriver");
	   driver = new FirefoxDriver();
	   baseUrl = "http://..............com";
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }

   @After
   public void tearDown() throws Exception {
	   driver.quit();
   }

   @Test
   public void test() {
	   driver.get(baseUrl);                                                   // go to
	   driver.findElement(By.id("txtUsername")).sendKeys("test");            // input username
	   driver.findElement(By.id("txtPassword")).sendKeys("test");         // input password
	   driver.findElement(By.id("btnLogin")).click();                         // click login button
	   String actualWelcome = driver.findElement(By.id("welcome")).getText(); // receive "welcome" text
	   assertEquals("Welcome Admin", actualWelcome);                          // expected and actual result
	   
	   //Log out
	   sleep(3);
	   driver.findElement(By.id("welcome")).click();
	   sleep(1);
	   driver.findElement(By.linkText("Logout")).click();
   }
   
	//invalid username
   @Test
   public void invalidUsername() {
	   String invalidUsername = "qwerty";
	   String expected = "Invalid credentials";
	   
	   driver.get(baseUrl);
	   driver.findElement(By.id("txtUsername")).sendKeys(invalidUsername);
	   driver.findElement(By.id("txtPassword")).sendKeys("test");
	   driver.findElement(By.id("btnLogin")).click();
	   sleep(3);
	   String actualMessage = driver.findElement(By.id("spanMessage")).getText();
	   assertEquals(expected, actualMessage);	   
   }
   
	// empty username
   @Test
   public void emptyUsername() {
	   String expected = "Username cannot be empty";
	   
	   driver.get(baseUrl);
	   driver.findElement(By.id("txtPassword")).sendKeys("test");
	   driver.findElement(By.id("btnLogin")).click();
	   sleep(3);
	   String actualMessage = driver.findElement(By.id("spanMessage")).getText();
	   String message = "Expected to see '" + expected + "' but got '" + actualMessage + "' instead.";
	   assertEquals(message, expected, actualMessage);	   
   }
   
	// invalid password
   @Test
   public void invalidPassword() {
	   String invalidPassword = "qwerty";
	   String expected = "Invalid credentials";
	   
	   driver.get(baseUrl);
	   driver.findElement(By.id("txtUsername")).sendKeys("test");
	   driver.findElement(By.id("txtPassword")).sendKeys(invalidPassword);
	   driver.findElement(By.id("btnLogin")).click();
	   sleep(3);
	   String actualMessage = driver.findElement(By.id("spanMessage")).getText();
	   assertEquals(expected, actualMessage);	   
   }
   
	// empty password
   @Test
   public void emptyPassword() {
	   String expected = "Password cannot be empty";
	   
	   driver.get(baseUrl);
	   driver.findElement(By.id("txtUsername")).sendKeys("test");
	   driver.findElement(By.id("btnLogin")).click();
	   sleep(3);
	   String message = driver.findElement(By.id("spanMessage")).getText();
	   assertEquals(expected, message);
	   
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
