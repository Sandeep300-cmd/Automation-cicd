package sandeepkumar.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sandeepkumar.TestComponent.BaseTest;
import sandeepkumar.pageObject.CartPage;
import sandeepkumar.pageObject.CheckoutPage;
import sandeepkumar.pageObject.ConfirmationPage;
import sandeepkumar.pageObject.LandingPage;
import sandeepkumar.pageObject.ProductCatalogue;

public class stepDefinationImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue ;
	public ConfirmationPage confirmationPage;
	
  

	
	@Given ("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage =launchApplication();
	}
	
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue =landingPage.loginApplication(username ,password);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage =checkoutPage.submitOrder();	
	}
	
	@Then (" ^\"([^\"]*)\" message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String strArg1)
	{
		String confirmMessage =confirmationPage.getConfirmationMessage(); 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(strArg1));
		driver.close();
		
	}
	
	 @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String strArg1) throws Throwable
	 {
	   
		 Assert.assertEquals(strArg1, landingPage.geterrorMessage());
		 driver.close();
	  }
	

}
