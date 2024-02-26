package sauravpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class ProductCheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public ProductCheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css="[placeholder*=\"Country\"]")
	WebElement countryText;
	
	 //WebElement countryText = driver.findElement(By.cssSelector("[placeholder*=\"Country\"]"));
	public void placeOrder() throws InterruptedException
	{
		countryText.sendKeys("IND");
	    Thread.sleep(2000);
	    Actions action = new Actions(driver);
	    action.sendKeys(Keys.ARROW_DOWN).build().perform();
	    action.sendKeys(Keys.ARROW_DOWN).build().perform();
	    action.sendKeys(Keys.RETURN).build().perform();
	}
	    

}
