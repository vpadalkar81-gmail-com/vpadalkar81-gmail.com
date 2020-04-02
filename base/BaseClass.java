package com.clover.base;




import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.clover.utility.ReaderClass;


public class BaseClass extends TestListenerAdapter {


	public static WebDriver driver;
	

	public static String browser;
	public static  Actions actions;
	public static void init() throws IOException, InterruptedException {
		browser=ReaderClass.getPropertyFile().getProperty("driver");
		
	
		if(browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			actions=new Actions(driver);
			//driver.wait();
			
		}
		else if(browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//drivers//geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  actions= new Actions(driver);
			  //driver.wait();


		}
		}
	
		@Override
		public void onTestFailure(ITestResult tr) {
			// TODO Auto-generated method stub
			super.onTestFailure(tr);
			
			System.out.println("failure detected...");
			String fileName="Screenshot"+tr.getMethod().getMethodName()+Calendar.getInstance().getTimeInMillis()+".png";
		//	WebDriver driver=(WebDriver) tr.getTestContext().getAttribute("WebDriver");
			//driver=(WebDriver) tr.getTestContext().getAttribute("webDriver");
			TakesScreenshot ts= (TakesScreenshot) driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File target= new File(System.getProperty("user.dir")+"/Screenshots/"+fileName);
			try {
				FileUtils.copyFile(src,target);
				System.out.println("Screenshot taken");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}
