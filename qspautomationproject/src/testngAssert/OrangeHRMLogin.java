package testngAssert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrangeHRMLogin {
	WebDriver driver;
	@BeforeTest
	public void setProperty()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}
	
	@BeforeMethod
	public void launchBrowser()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		
	}
	
	@Test
	public void testCase() throws InterruptedException
	{
		SoftAssert softassert = new SoftAssert();
		String actualLoginPageTitle = driver.getTitle();
		softassert.assertEquals(actualLoginPageTitle, "OrangrHRM");
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		WebElement dashbord =driver.findElement(By.xpath("//a[.='Dashboard']"));
		boolean statusOfDashbord = dashbord.isDisplayed();
		Assert.assertEquals(statusOfDashbord, true);
		Thread.sleep(4000);
		
		String actualHomePageTitle = driver.getTitle();
		softassert.assertEquals(actualHomePageTitle,"OrangeHRMHomePage");
		Reporter.log("create user", true);
		Reporter.log("create contact", true);
		softassert.assertAll();
		
		
		
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
		
	}
	
	
	


}
