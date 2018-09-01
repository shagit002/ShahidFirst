package selenium.practice.one;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Calendar {
	
	public WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucky\\eclipse-workspace\\SeleniumPractice\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://www.path2usa.com/travel-companions");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void getCalendar() {
		
		driver.findElement(By.id("travel_from")).sendKeys("Denver");
		driver.findElement(By.id("travel_to")).sendKeys("LAX");
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("travel_date"))).click();
		
		
		
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.close();
		driver=null;
	}
}
