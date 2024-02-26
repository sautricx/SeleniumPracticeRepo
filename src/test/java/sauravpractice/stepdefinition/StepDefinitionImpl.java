package sauravpractice.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sauavpractice.testcomponents.BaseTest;
import sauravpractice.pageobjects.CartPage;
import sauravpractice.pageobjects.ConfirmationPage;
import sauravpractice.pageobjects.LoginPage;
import sauravpractice.pageobjects.ProductCataloguePage;
import sauravpractice.pageobjects.ProductCheckoutPage;

public class StepDefinitionImpl extends BaseTest{
	
	public LoginPage loginPage;
	public ProductCataloguePage productCatalogue;
	
	@Given("I landed on Ecommerce web page")
	public void I_Landed_On_Ecommerce_Webpage() throws IOException {
		
		loginPage = launchApplication();	
	}
	
	// Below as we are using variables we need to completely bind @Given statement in regex within ^ 
	//and $ which was not required in above.
	@Given("^I login with (.+)  and (.+)$") 
	public void login_with_user_password(String userName, String password)
	{
		productCatalogue = loginPage.loginApplication(userName, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
	}
	
	// And statement is also written under When if it is completing soe part of when and similalry for Given and Then.
	@When("Checkout(.+) and submit")
	public void Checkout_product_and_submit(String prodName) throws InterruptedException
	{
		productCatalogue.addProductToCart(prodName);
		Thread.sleep(2000);
		CartPage cart = productCatalogue.goToCart();
		Thread.sleep(3000);
		Boolean result = cart.getCartItem(prodName);
		Assert.assertTrue(result);
		Thread.sleep(2000);
		ProductCheckoutPage prodCheckout = cart.productCheckout();
		prodCheckout.placeOrder();
		Thread.sleep(2000);
	}
	
	//In below Then statement in feature file we had directly text file in When statement so we
	// had to write here only {String} no regex is required as we are not taking data from table.
	// Below line is giving error as we have not defined any confirmation page in our framework.
//	@Then("{String} message is dispalyed on ConfirmationPage")
//	public void message_is_displayed_on_confirmation_page(String message)
//	{
//		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
//		String confirmMessage = confirmationPage.getConfirmationMessage("THANKYOU FOR THE ORDER.");
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
//		
//		
//	}

}
