package sauravpractice.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("sausejneh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("LWpass11$");
		driver.findElement(By.id("login")).click();
		String prodName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
//		Iterator<WebElement> itr = products.iterator();
//		while(itr.hasNext())
//		{
//			WebElement prod = itr.next();
//			String prod1 = prod.findElement(By.cssSelector(".col-lg-4 b")).getText();
//			if(prod1.equals("ADIDAS ORIGINAL"))
//			{
//				System.out.println("this product is disp-layed for "  + prod1);
//				break;
//			}
//			
//		}
		WebElement product = products.stream().filter(prod->prod.findElement(By.cssSelector(".col-lg-4 b")).getText().equals(prodName)).findFirst().orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
		Thread.sleep(3000);
		
	    List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean result = cartProduct.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(prodName));
	    Assert.assertTrue(result);
	    //Thread.sleep(3000);
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    //js.executeScript("window.scrollB(0,350)");
	    WebElement chkoutbutton = driver.findElement(By.cssSelector(".totalRow .btn-primary"));
	    js.executeScript("arguments[0]. scrollIntoView(true);",chkoutbutton);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
	    WebElement countryText = driver.findElement(By.cssSelector("[placeholder*=\"Country\"]"));
	    countryText.sendKeys("IND");
	    Thread.sleep(2000);
	    Actions action = new Actions(driver);
	    action.sendKeys(Keys.ARROW_DOWN).build().perform();
	    action.sendKeys(Keys.ARROW_DOWN).build().perform();
	    action.sendKeys(Keys.RETURN).build().perform();
	    Thread.sleep(2000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Place Order \"]")));
	    driver.findElement(By.cssSelector(".action__submit i")).click();
	    
	    //WebElement orderPlace = driver.findElement(By.cssSelector(".toast-bottom-right"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-bottom-right")));
	    System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());

	    
	    
	    
		
		
		//driver.quit();
	}

}
