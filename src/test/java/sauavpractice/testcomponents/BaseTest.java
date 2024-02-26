package sauavpractice.testcomponents;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import sauravpractice.pageobjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Study\\Eclipse\\seleniumFrameworkDesign\\src\\main\\java\\sauravpractice\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
//			Point newPoint = new Point(1400, 900);
//			driver.manage().window().setSize(newPoint);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		
		return driver;
		
	}
	
	@BeforeMethod
	public LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToWeb();
		return loginPage;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
			//Convert json to strijng using readFileToString()
			String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
			
			//String to Hashmap - jackson Databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
			return data;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		//File destFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		String filePath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png";
//		File destFile = new File(System.getProperty("user.dir")+ "//src//test//java//sauravpractice//screenshot//" + testCaseName + ".png");
		File destFile = new File(filePath); 
		FileUtils.copyFile(sourceFile, destFile);
		//return System.getProperty("user.dir")+ "//src//test//java//sauravpractice//screenshot//" + testCaseName + ".png";
		return filePath;
		
		
	}
	

}
