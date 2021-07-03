package demo3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class gym {
  WebDriver driver;
   @Test(priority = 0)
	public void openSite()
	{
	  	WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);	
		driver.get("https://www.justdial.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@Test(priority=1)
	public void selectFitnessOption() 
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("ContextualHotkey_27")).click();
	}
	
	@Test(priority=2)
	public void selectGymOption() 
	{
		
		driver.findElement(By.xpath("//*[@id=\'mnintrnlbnr\']/ul/li[3]/a/span[2]")).click();
	}
	
	@Test(priority=3)
	public void printAllOptions() 
	{
		List<WebElement> options= driver.findElements(By.xpath("//*[@id='mnintrnlbnr']/ul/li/a/span[2]"));
	
		for (int i=0;i<options.size();i++)
		{
			System.out.println(options.get(i).getAttribute("title"));
		}
	}
	@Test(priority=4)
	public void Closebrowser() { 
		driver.close(); 
	}
}
