package RohitFramework.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RohitFramework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	//Creating constructor
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="[class*='action__submit']")
	WebElement submit;
	
	@FindBy(xpath="(//*[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	By select = By.cssSelector("[class*='ta-results']");
	By verifySubmitBtn = By.cssSelector("[class*='action__submit']");
	
	
	public void SelectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		WaitForElementToAppear(select);
		SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	public void verifySubmit()
	{
		ScrollingUp();
		WaitForElementToAppear(verifySubmitBtn);
	}
	
}
