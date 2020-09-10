package Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	
	static WebDriver driver;
	
	static String baseUrl = "https://techfios.com/test/101/index.php";
	
	public static WebDriver lunchWebsite() {	
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		BasePage.implicitWait(driver, 10);
		return driver;		
	}

}
