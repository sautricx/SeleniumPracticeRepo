package sauravpractice.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
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

public class HashMapSubmitOrderTest extends BaseTest {

	String prodName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "smoke")
	public void submitOrderTest(HashMap<String,String> input)
			throws InterruptedException, IOException {

		// LoginPage loginPage = launchApplication();
		ProductCataloguePage productCatalogue = loginPage.loginApplication(input.get("email"),input.get("pass"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("prod"));
		productCatalogue.addProductToCart(input.get("prod"));
		Thread.sleep(2000);
		CartPage cart = productCatalogue.goToCart();
		Thread.sleep(3000);
		Boolean result = cart.getCartItem(input.get("prod"));
		Assert.assertTrue(result);
		Thread.sleep(2000);
		ProductCheckoutPage prodCheckout = cart.productCheckout();
		prodCheckout.placeOrder();
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void orderHistoryTest() {
		ProductCataloguePage productCatalogue = loginPage.loginApplication("sausejneh@gmail.com", "LWpass11$");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(prodName));

	}


	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","srvmishra111@gmail.com");
		map.put("pass", "LWpass11$");
		map.put("prod","ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","sausejneh@gmail.com");
		map1.put("pass", "LWpass11$");
		map1.put("prod","ADIDAS ORIGINAL");

		return new Object[][] {{map},{map1} };
	}

}
