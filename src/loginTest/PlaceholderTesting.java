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

class PlaceholderTesting {

	static WebDriver driver;
	
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
	
	//Testing placeholder in username input field
	@Test
	public void usernamePlaceholder() {
		
		String userPlaceholder = driver.findElement(By.id("email")).getAttribute("placeholder");
		
		assertEquals("Email/Username", userPlaceholder);
		
	}
	
	//Testing placeholder in password input field
	@Test
	public void passwordPalceholder() {
		
		String passPlaceholder = driver.findElement(By.id("password")).getAttribute("placeholder");
		
		assertEquals("Password",passPlaceholder);
		
	}
	
	//Testing Forgot Password?
	public void testingForgotPass() {
		
		driver.findElement(By.className("lForgot")).click();
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("validEmail");
		email.submit();
		
		WebElement reset = driver.findElement(By.id("status-message"));
		
		assertNotNull(reset);
		
	}

}





































