package loginTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginPageTesting {

	static WebDriver driver;
	
	private WebElement username;
	private WebElement password;
	
	public void gettingUserAndPass() {
		
		this.username = driver.findElement(By.id("email"));
		this.password = driver.findElement(By.id("password"));
		
	}
	
	public void sendingKeys() {
		
		this.username.sendKeys("validUsername");
		this.password.sendKeys("validPassword");
		this.username.submit();
		
	}
	
	@BeforeAll
	public static void browserOpen() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}
	
	@BeforeEach
	public void humanityMain() {

		String baseUrl = "https://www.humanity.com/";
		driver.get(baseUrl);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement login = driver.findElement(By.linkText("LOGIN"));
		login.click();
		
	}
	
	//Testing login with valid username and valid password
	@Test
	public void testingLoginUsername() {
		
		this.gettingUserAndPass();
		
		username.sendKeys("validUsername");
		password.sendKeys("validPassword");
		username.submit();
		
		String welcomeUser = driver.findElement(By.className("dashwidget_label")).getText();
		assertEquals("Welcome to Humanity!", welcomeUser);
		
	}
	
	//Testing login with valid email and valid password
	@Test
	public void testingLoginEmail() {
		
		this.gettingUserAndPass();
		
		username.sendKeys("validEmail");
		password.sendKeys("validPassword");
		username.submit();
		
		String welcomeUser = driver.findElement(By.className("dashwidget_label")).getText();
		assertEquals("Welcome to Humanity!", welcomeUser);
		
	}
	
	//Testing login with facebook
	@Test
	public void testingLoginFacebook() {
		
		WebElement facebookLogin = driver.findElement(By.linkText("Log in with Facebook"));
		facebookLogin.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.id("email")).sendKeys("validEmail");
		driver.findElement(By.id("pass")).sendKeys("validPassword");
		driver.findElement(By.id("loginbutton")).click();
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		String welcomeUser = driver.findElement(By.className("dashwidget_label")).getText();
		assertEquals("Welcome to Humanity!", welcomeUser);
		
	}
	
	//Testing login with enter key
	@Test
	public void testingLoginWithEnter() {
		
		this.gettingUserAndPass();
		
		username.sendKeys("validUsername");
		password.sendKeys("validPassword");
		username.sendKeys(Keys.ENTER);
		
		String welcomeUser = driver.findElement(By.className("dashwidget_label")).getText();
		assertEquals("Welcome to Humanity!", welcomeUser);
		
	}
	
	//Testing add new employee feature
	@Test
	public void testingAddNewEmployee() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		driver.findElement(By.id("sn_staff")).click();
		driver.findElement(By.id("act_primary")).click();
		
		driver.findElement(By.id("_asf1")).sendKeys("FirstName");
		driver.findElement(By.id("_asl1")).sendKeys("LastName");
		driver.findElement(By.id("_ase1")).sendKeys("emailAddress");
		
		driver.findElement(By.id("_as_save_multiple")).click();
		
		WebElement newEmployee = driver.findElement(By.linkText("FirstName LastName"));
		assertNotNull(newEmployee);
		
	}
	
	//Test adding multiple new employees
	@Test
	public void testingMultipleEmployees() {
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.id("sn_staff")).click();
		driver.findElement(By.id("act_primary")).click();
		
		int beforeAdd = driver.findElements(By.tagName("a")).size();
		
		driver.findElement(By.id("_asf1")).sendKeys("FirstName");
		driver.findElement(By.id("_asl1")).sendKeys("LastName");
		driver.findElement(By.id("_ase1")).sendKeys("jelena@myaddress.com");
		
		driver.findElement(By.id("_asf2")).sendKeys("FirstName");
		driver.findElement(By.id("_asl2")).sendKeys("LastName");
		driver.findElement(By.id("_ase2")).sendKeys("dusan@myaddress.com");
		
		driver.findElement(By.id("_as_save_multiple")).click();
		
		int afterAdd = driver.findElements(By.tagName("a")).size();
		
		int newAdded = beforeAdd - afterAdd;
		
		assertEquals(2, newAdded);
		
	}
	
	//Testing Save Employees when no new employee is added
	@Test
	public void noNewEmployee() {
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.id("sn_staff")).click();
		driver.findElement(By.id("act_primary")).click();
		
		driver.findElement(By.id("_as_save_multiple")).click();
		
		String attribute = driver.findElement(By.id("_status")).getAttribute("style");
		
		String compare = attribute.substring(0, 14);
		
		assertEquals("diplay: block", compare);
		
	}
	
	/*
	 * Test deleting employee
	 * 
	 * Note: Employee to be deleted is manually added
	 * before each test run, so there is no dependency on other tests
	 */
	@Test
	public void testDeletingEmployee() {
		
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		driver.findElement(By.id("sn_staff")).click();
		driver.findElement(By.linkText("FirstName LastName")).click();
		driver.findElement(By.linkText("Click Here")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		assertEquals(0, driver.findElements(By.linkText("FirstName LastName")).size());
		
	}
	
	@AfterEach
	public void signOut() {
		
		driver.findElement(By.id("tr_avatar")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		
		String baseUrl = "https://www.humanity.com/";
		driver.get(baseUrl);
		
	}
	

}


































