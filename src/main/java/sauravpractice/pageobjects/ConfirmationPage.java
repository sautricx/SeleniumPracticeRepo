package sauravpractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import sauravpractice.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getConfirmationMessage(String confirmationMessage)
	{
		return confirmationMessage;
	}

}
