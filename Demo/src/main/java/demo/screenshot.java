package demo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshot {
	public void screenshot(WebDriver driver) throws IOException {
		Date currentDate = new Date();
		
		String screenShotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File(".//ScreenShots//"+screenShotFileName+".png"));
	}
}
