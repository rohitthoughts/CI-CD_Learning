package RohitFramework.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RohitFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	//Creating constructor
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	//Page factory
	@FindBy(id="userEmail")
	WebElement userName;

	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css="div[class*='toast-message']")
	WebElement error;
	
	
	public void LandingUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductsCatalogue UserLogin(String uname,String pwd)
	{
		userName.sendKeys(uname);
		password.sendKeys(pwd);
		Login.click();
		ProductsCatalogue productCatalogue = new ProductsCatalogue(driver);
		return productCatalogue;
	}
	
	public String VerifyErrorMessage()
	{
		WaitForWebElementToAppear(error);
		return error.getText();
	}

}
