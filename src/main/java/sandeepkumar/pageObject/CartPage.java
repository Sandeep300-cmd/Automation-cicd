package sandeepkumar.pageObject;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sandeepkumar.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy (css=".totalRow button")
	WebElement checkoutEle;
	
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match=	cartProduct.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		CheckoutPage checkoutPage= new CheckoutPage(driver);
		return checkoutPage;
		
	}
}
