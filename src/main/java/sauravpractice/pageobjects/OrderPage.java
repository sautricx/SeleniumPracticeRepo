package sauravpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	public String prodName;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderName;
	
	public boolean verifyOrderDisplay(String productName)
	{
		boolean orderMatch = orderName.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return orderMatch;
	}

}
