package selenium.practice.one;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrokenLinksandImages {
	public WebDriver driver;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucky\\eclipse-workspace\\SeleniumPractice\\chromedriver.exe");
		 driver =new ChromeDriver();
		driver.get("https://freecrm.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void getbrokenlinksandImages() throws MalformedURLException, IOException {
		driver.findElement(By.name("username")).sendKeys("naveenk");
		driver.findElement(By.name("password")).sendKeys("test@123");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.switchTo().frame("mainpanel");
		
		//Add all the links and images
		List<WebElement> linkList=driver.findElements(By.tagName("a"));
		linkList.addAll(driver.findElements(By.tagName("img")));
		System.out.println(linkList.size());
		
		List<WebElement> activeLinks=new ArrayList<WebElement>();
		//iterating through the links and images and exclude links/images that do not have "href" attribute
		
		for (int i=0;i<linkList.size(); i++) {
			if (linkList.get(i).getAttribute("href") !=null && (!linkList.get(i).getAttribute("href").contains("javascript")))
				activeLinks.add(linkList.get(i));
			System.out.println(activeLinks.size());
			
			
			//check the href url, with httpconnection api;
			for (int j=0;j<activeLinks.size();j++) {
				
				HttpURLConnection connection=(HttpURLConnection)new URL(activeLinks.get(j).getAttribute("href")).openConnection();
				connection.connect();
				String response=connection.getResponseMessage();
				connection.disconnect();
				System.out.println(activeLinks.get(j).getAttribute("href")+ "------->"+ response);
			}
		}
		}
  @AfterTest
  public void tearDown() {
	 driver.close();
	 driver=null;
  }

}
