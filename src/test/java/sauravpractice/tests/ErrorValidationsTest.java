package sauravpractice.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sauavpractice.testcomponents.BaseTest;
import sauravpractice.pageobjects.CartPage;
import sauravpractice.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer= sauavpractice.testcomponents.Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		String prodName = "ADIDAS ORIGINAL";
		ProductCataloguePage productCatalogue = loginPage.loginApplication("srvmishra111@gmail.com", "LWpass11$");
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		String prodName = "ADIDAS ORIGINAL";
		//LoginPage loginPage = launchApplication();
		ProductCataloguePage productCatalogue = loginPage.loginApplication("sausejneh@gmail.com", "LWpass11$");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(prodName);
		productCatalogue.addProductToCart(prodName);
		Thread.sleep(2000);
		CartPage cart = productCatalogue.goToCart();
		Thread.sleep(3000);
		Boolean result = cart.getCartItem(prodName);
		Assert.assertTrue(result);
	}
}
