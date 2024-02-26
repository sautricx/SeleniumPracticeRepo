package sauravpractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));	
//	Above line of code can be replaced by FactoryClass as below
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="iv[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public void goToWeb()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCataloguePage loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
