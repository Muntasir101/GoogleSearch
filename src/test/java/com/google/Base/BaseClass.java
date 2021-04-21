package com.google.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.utilities.ReadConfigFile;


public class BaseClass {

	ReadConfigFile rcf = new ReadConfigFile();
	
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", rcf.getChromepath());
			driver = new ChromeDriver();
		} 
		else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", rcf.getFirefoxpath());
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
