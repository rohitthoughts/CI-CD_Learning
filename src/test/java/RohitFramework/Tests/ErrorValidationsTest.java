package RohitFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RohitFramework.Pageobjects.CartPage;
import RohitFramework.Pageobjects.ProductsCatalogue;
import RohitFramework.TestComponents.BaseTest;
import RohitFramework.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	
		@Test(groups = {"Error Handling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException
		{
		String Productname="ZARA COAT 3";
		String CountryName = "India";

		 Lpage.UserLogin("punekar435@gmail.com", "Pune");
		 Assert.assertEquals("Incorrec email or password.",Lpage.VerifyErrorMessage());
		}

		@Test(retryAnalyzer=Retry.class)
		public void ProductErrorValidation() throws  IOException, InterruptedException
		{
			String Productname="ZARA COAT 3";
			String Email = "punekarrohit49@gmail.com";
			String password = "Rohit@435";

			ProductsCatalogue productCatalogue = Lpage.UserLogin(Email, password);
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(Productname);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match  = cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
		}
	

}
