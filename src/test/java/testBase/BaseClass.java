package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
public class BaseClass {
	
	//reusable methods
	public static WebDriver driver;//we created Baseclass obj in Extendreport that is another driver there so it will be conflict so making static
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})//we need to add all the groups before and after classes. Eventhough you extend Base class it wont trigger.In xml file we named methods thats why only look for that method 
	@Parameters ({"os","browser"})
	public void setUp(String os,String br) throws IOException {
		
		//loading config properties file
		
		FileReader file=new FileReader("./src//test//resources//config.properties");// reading mode-  ./indicates whichever project we are working on
		p=new Properties();
		p.load(file);
		
	 logger=LogManager.getLogger(this.getClass());//this represent class name.we are dynamically taking class name whichever TC executes
	 
	 //remote machine
	 if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			 {
		 			DesiredCapabilities capabilities = new DesiredCapabilities();
		 			
		 			//	OS-we have to set in the master suite which os and br-add below if any OS and br missing
		 			if(os.equalsIgnoreCase("windows"))
		 			{
		 				capabilities.setPlatform(Platform.WIN11);
		 			}
		 			else if(os.equalsIgnoreCase("linux"))
		 			{
		 				capabilities.setPlatform(Platform.LINUX);
		 			}
		 			
		 			else if(os.equalsIgnoreCase("mac"))
		 			{
		 				capabilities.setPlatform(Platform.MAC);
		 			}
		 			else
		 			{
		 				System.out.println("No matching os");
		 				return;
		 			}
		 			//browser
		 			
		 			switch(br.toLowerCase())
		 			{
		 			case "chrome": capabilities.setBrowserName("chrome");break;
		 			case "edge":capabilities.setBrowserName("microsoftedge");break;
		 			case "firefox": capabilities.setBrowserName("firefox");break;
		 			default: System.out.println("No matching browser");return;
		 			}
		 			
		 			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);//created driver for remote execution convert in url format and add 2nd parameter as capabilites obj
		 			
			 }
	 
	 //local machine
	 if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	 {
	 	switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		case "firefox" : driver = new FirefoxDriver();break;
		default : System.out.println("invalid browser name..");return;//it will come out of whole method
		}
	 }
	 
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL"));//reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		
	}
	
	public String randomNumber()
	{
		String generatednumber =RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomAlphaNumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber =RandomStringUtils.randomNumeric(4);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname)throws IOException
	{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
	
		return targetFilePath;
		
	}


}
