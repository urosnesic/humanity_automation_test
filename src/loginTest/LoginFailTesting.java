package loginTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginFailTesting {
	
	static WebDriver driver;
	
	private WebElement username;
	private WebElement password;
	
	public void gettingUserAndPass() {
		
		this.username = driver.findElement(By.id("email"));
		this.password = driver.findElement(By.id("password"));
		
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
	
	//Testing login with valid username and invalid password
	@Test
	public void loginInvalidPass() {
		
		this.gettingUserAndPass();
		
		username.sendKeys("validUsername");
		password.sendKeys("invalidPassword");
		username.submit();
		
		String failedUrl = driver.getCurrentUrl();
		assertEquals("https://www.humanity.com/app/", failedUrl);
		
	}
		
	//Testing login with invalid username and valid password
	@Test
	public void loginInvalidUname() {
		
		this.gettingUserAndPass();
			
		username.sendKeys("invalidUsername");
		password.sendKeys("validPassword");
		username.submit();
			
		String failedUrl = driver.getCurrentUrl();
		assertEquals("https://www.humanity.com/app/", failedUrl);
		
	}
		
	//Testing login without username and password provided
	@Test
	public void loginNoPassUser() {
		
		WebElement username = driver.findElement(By.id("email"));
		
		username.submit();
		
	}

}





















