package RohitFramework.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RohitFramework.Pageobjects.CartPage;
import RohitFramework.Pageobjects.CheckoutPage;
import RohitFramework.Pageobjects.ConfirmationPage;
import RohitFramework.Pageobjects.LandingPage;
import RohitFramework.Pageobjects.ProductsCatalogue;
import RohitFramework.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductsCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_Landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_Username_Password(String userName,String password)
	{
		productCatalogue = Lpage.UserLogin(userName,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_Add_Product_To_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_And_Submit_the_Order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match  = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.SelectCountry("india");
		checkoutPage.verifySubmit();
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} Message is displayed on Confirmation Page")
	public void Message_Is_Displayed_On_Confirmation_Page(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}


}
