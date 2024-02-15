package RohitFramework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RohitFramework.Pageobjects.CartPage;
import RohitFramework.Pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="(//*[@class='btn btn-custom'])[3]")
	WebElement CartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;


	public void WaitForElementToAppear(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void WaitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void WaitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCartPage()
	{
		CartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage orderHeader = new OrderPage(driver);
		return orderHeader;
	}

	public void ScrollingUp()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1500)"); 
	}

}
