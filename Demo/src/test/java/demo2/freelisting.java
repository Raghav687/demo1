package demo2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class freelisting {
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
  @Test(priority = 1)
  public void openFreeListing()
  {
	  	driver.findElement(By.id("h_flist")).click();
	  	driver.findElement(By.id("fcom")).sendKeys("raghav car wash");
		driver.findElement(By.xpath("//*[@id=\"fmb0\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"add_div0\"]/div[3]/button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.pollingEvery(Duration.ofSeconds(5));
		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"intial_div\"]/span[2]"))).getText();
		System.out.println(message);
  }
  @Test(priority=2)
  public void closeDriver() {
	  driver.close();
  }
}






