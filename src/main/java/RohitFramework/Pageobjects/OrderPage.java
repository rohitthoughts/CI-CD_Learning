package RohitFramework.Pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RohitFramework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	//Creating constructor
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
	public boolean VerifyOrderDisplay(String Productname)
	{
		boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(Productname));
		return match;
	}

}
