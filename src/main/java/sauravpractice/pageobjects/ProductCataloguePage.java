package sauravpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	//Action method to find product
	
	By productLocator = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By animation = By.cssSelector(".ng-animating");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productLocator);
		return products;
	}
	
	public WebElement getProductByName(String prodName)
	{
		WebElement product = products.stream().filter(prod->
		prod.findElement(By.cssSelector(".col-lg-4 b")).getText().equals(prodName)).findFirst().orElse(null);
		
		return product;
	}
	
	public void addProductToCart(String prodName)
	{
		WebElement prod = getProductByName(prodName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(animation);
	}
	
	
}
