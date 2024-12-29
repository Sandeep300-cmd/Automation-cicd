package sandeepkumar.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import sandeepkumar.TestComponent.BaseTest;
import sandeepkumar.TestComponent.Retry;
import sandeepkumar.pageObject.CartPage;
import sandeepkumar.pageObject.CheckoutPage;
import sandeepkumar.pageObject.ConfirmationPage;
import sandeepkumar.pageObject.LandingPage;
import sandeepkumar.pageObject.ProductCatalogue;

public class ErrorValidation extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		landingPage.loginApplication("sandeepkumar123@gmail.com", "Sandy@0123");
		//landingPage.geterrorMessage();
		Assert.assertEquals("Incorrect email password.", landingPage.geterrorMessage());;
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue =landingPage.loginApplication("sandeepkumar123@gmail.com", "Sandy@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart("ADIDAS ORIGINAL");
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINALS");
		Assert.assertFalse(match);
		
	}

}
