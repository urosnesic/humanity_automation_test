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

class BackButton {
	
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
	
	//Testing click on browser back button after successful sign out
	@Test
	public void successfulSignout() {
		
		this.gettingUserAndPass();
		
		this.sendingKeys();
		
		driver.findElement(By.id("tr_avatar")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.navigate().back();
		
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals("https://test763.humanity.com/app/", currentUrl);
		
	}
	
}








































