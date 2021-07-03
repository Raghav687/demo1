package demo1;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import demo.screenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

public class login {
	WebDriver driver;
	Actions actions;
	int flag = 0;
	String b = "";
    @Test(priority=0)
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
    public void searchTextboxAndRemoveAd() throws IOException {
    	driver.findElement(By.id("city")).sendKeys("Delhi");
		driver.findElement(By.id("srchbx")).sendKeys("Car wash", Keys.ENTER);
		
		
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		wait.pollingEvery(Duration.ofSeconds(5));
		WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"best_deal_div\"]/section/span")));
		actions.moveToElement(cross).click().perform();*/
    }

    @Test(priority = 2)
    public void findBestDeal() {
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(By.className("store-details"));
		String[] phone = null;
		String phoneString = null;
		String[] stringArray = new String[0];
		int j = 0;
		float rat[]=new float[elements.size()]; 
		for(int i=0;i<elements.size();i++) {
			String temp =elements.get(i).findElement(By.className("green-box")).getText();
			rat[i]= Float.parseFloat(temp);
			String Vote = elements.get(i).findElement(By.xpath("//*[@id=\"bcard"+i+"\"]/div[1]/section/div[1]/p[1]/a/span[3]")).getText();
			String numberOnly= Vote.replaceAll("[^0-9]", "");
			int VoteInteger = Integer.parseInt(numberOnly);
			List<WebElement> PhoneNumberString =  driver.findElements(By.className("mobilesv"));
			if(flag == 0) {
				for(int k = 0;k<PhoneNumberString.size();k++) {
					phoneString = PhoneNumberString.get(k).getAttribute("class").split("-")[1];
					MobileDemo r = new MobileDemo();
					String temp2 = r.MobileClass(phoneString);
					b += temp2;
				}
				flag = 1;
				while(j<b.length()) {
					if(b.charAt(j) == '0') {
						int count = 0;  
						String var = "";
						while(j<b.length() && count <= 10) {
							var += b.charAt(j); 
							j++;count++;
						}
						stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
						stringArray[stringArray.length - 1] = var;
					}
					else if(b.charAt(j) == '+') {
						int count = 0;
						String var = "";
						while(j<b.length() && count <= 15) {
							var += b.charAt(j); 
							j++;count++;
						}
						stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
						stringArray[stringArray.length - 1] = var;
					}
				}
			}
			if(rat[i]>=4.0 && VoteInteger > 20) {
				System.out.print("Name: ");
				System.out.print(elements.get(i).findElement(By.className("lng_cont_name")).getText());
				System.out.println();
				
				System.out.print("Address: ");
				System.out.print(elements.get(i).findElement(By.className("cont_sw_addr")).getText());
				System.out.println();
				
				System.out.print("PhoneNumber: "); 
				System.out.print(stringArray[i]);
				System.out.println();
				System.out.println();
			}
		}
    }
    @Test(priority=3)
    public void closeDriver() {
    	driver.close();
    }
}
