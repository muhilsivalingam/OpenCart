package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public  Logger logger;//log4j
	public Properties p;
	@BeforeClass(groups= {"Sanity","Master","Regression","DataDriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException
	{
		//loading config.prop file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		switch(br.toLowerCase())
		{
		case "chrome":
		driver=new ChromeDriver();
		break;
		case "edge":
		driver=new EdgeDriver();
		break;
		case "firefox":
		driver=new FirefoxDriver();
		break;
		default:
		System.out.println("Invalid browser name");return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));//reading url from properties file.
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity","Master","Regression","DataDriven"})
	public void tearDown() {
	    driver.quit();
	}
	public String randomString(int no)
	{
	String generatedString=RandomStringUtils.secure().nextAlphabetic(no);
	return generatedString;
	}
	public String randomNumber(int no)
	{
	String generatedNumber=RandomStringUtils.secure().nextNumeric(no);
	return generatedNumber;
	}
	public String randomAlphanumeric(int no)
	{
	String generatedAlphanumeric=RandomStringUtils.secure().nextAlphanumeric(no);
	return generatedAlphanumeric;
     }
	public String captureScreen(String tname) throws IOException {
	    
	    // Generate timestamp for unique screenshot names
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    
	    // Take screenshot
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	    
	    // Create target path with timestamp
	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	    
	    // Move screenshot to target location
	    sourceFile.renameTo(targetFile);
	    
	    return targetFilePath;
	}

}
