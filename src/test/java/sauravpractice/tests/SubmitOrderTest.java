package sauravpractice.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sauavpractice.testcomponents.BaseTest;
import sauravpractice.pageobjects.CartPage;
import sauravpractice.pageobjects.LoginPage;
import sauravpractice.pageobjects.OrderPage;
import sauravpractice.pageobjects.ProductCataloguePage;
import sauravpractice.pageobjects.ProductCheckoutPage;

public class SubmitOrderTest extends BaseTest {

	String prodName = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData")
	public void submitOrderTest(String userName,String password, String prodName) throws InterruptedException, IOException {
		
		//LoginPage loginPage = launchApplication();
		ProductCataloguePage productCatalogue = loginPage.loginApplication(userName, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(prodName);
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
		//driver.findElement(By.cssSelector(".action__submit i")).click();
		//System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
		// driver.quit();
	}
	
	@Test(dependsOnMethods={"submitOrderTest"})
	public void orderHistoryTest()
	{
		ProductCataloguePage productCatalogue = loginPage.loginApplication("sausejneh@gmail.com", "LWpass11$");
		OrderPage orderPage =  productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(prodName));
		
	}
	
	@DataProvider
	public Object[][] getData() {
	return new Object[][] {{"srvmishra111@gmail.com","LWpass11$","ZARA COAT 3"},{"sausejneh@gmail.com","LWpass11$","ADIDAS ORIGINAL"}};
	}
	
}
	
