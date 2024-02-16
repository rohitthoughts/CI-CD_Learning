package RohitFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import RohitFramework.Pageobjects.CartPage;
import RohitFramework.Pageobjects.CheckoutPage;
import RohitFramework.Pageobjects.ConfirmationPage;
import RohitFramework.Pageobjects.OrderPage;
import RohitFramework.Pageobjects.ProductsCatalogue;
import RohitFramework.TestComponents.BaseTest;
import RohitFramework.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest{

	String Productname="ZARA COAT 3";

	@Test(dataProvider = "getData", groups="purchase",retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException
	{


		ProductsCatalogue productCatalogue = Lpage.UserLogin(input.get("email"),input.get("pwd"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match  = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.SelectCountry(input.get("countryName"));
		checkoutPage.verifySubmit();
		Thread.sleep(3000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods= {"submitOrder"},retryAnalyzer=Retry.class)
	public void OrderHistoryTest()
	{
		String Email = "punekar435@gmail.com";
		String password = "Punekar@435";
		ProductsCatalogue productCatalogue = Lpage.UserLogin(Email, password);
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(Productname));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RohitFramework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData()
//	{	
//		return new Object[][] {{"punekar435@gmail.com","Punekar@435","ZARA COAT 3","India"}, 
//			{"punekarrohit49@gmail.com","Rohit@435","IPHONE 13 PRO","India"}};
//	}
	
	
	
	
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "punekar435@gmail.com");
//	map.put("pwd", "Punekar@435");
//	map.put("productName","ZARA COAT 3");
//	map.put("countryName", "India");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "punekarrohit49@gmail.com");
//	map1.put("pwd", "Rohit@435");
//	map1.put("productName","IPHONE 13 PRO");
//	map1.put("countryName", "India");


}
