package sauravpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow .btn-primary")
	WebElement checkoutButton;
	
	public Boolean getCartItem(String prodName)
	{
		Boolean result = cartProduct.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(prodName));
		return result;
	}
	
//    Assert.assertTrue(result);
    
    public ProductCheckoutPage productCheckout() throws InterruptedException
    {
    	//WebElement checkoutButton = driver.findElement(By.cssSelector(".totalRow .btn-primary"));
    	//waitForWebElementToAppear(checkoutButton);
    	//JavascriptExecutor js = (JavascriptExecutor)driver;
	    //js.executeScript("arguments[0]. scrollIntoView(true);",checkoutButton);
	    waitForWebElementToAppear(checkoutButton);
    	checkoutButton.click();
    	ProductCheckoutPage prodCheckout = new ProductCheckoutPage(driver);
    	return prodCheckout;
    }
    
   

}
