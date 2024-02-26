package sauravpractice.abstractcomponents;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sauravpractice.pageobjects.CartPage;
import sauravpractice.pageobjects.OrderPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="[routerlink*=\"cart\"]")
	WebElement cartIcon;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement myOrder;
	
	public CartPage goToCart()
	{
		cartIcon.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", findBy);
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public OrderPage goToOrdersPage()
	{
		myOrder.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	} 
	

}
