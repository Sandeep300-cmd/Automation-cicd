package sandeepkumar.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sandeepkumar.TestComponent.BaseTest;
import sandeepkumar.pageObject.CartPage;
import sandeepkumar.pageObject.CheckoutPage;
import sandeepkumar.pageObject.ConfirmationPage;
import sandeepkumar.pageObject.OrderPage;
import sandeepkumar.pageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	//Email -sandeepkumar123@gmail.com
	//paswword -Sandy@123
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData" ,groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage =checkoutPage.submitOrder();		
		String confirmMessage =confirmationPage.getConfirmationMessage(); 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	// to verify product is displaying in order page
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue =landingPage.loginApplication("sandeepkumar123@gmail.com", "Sandy@123");
		OrderPage ordersPage =productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//sandeepkumar//data//PurchaseOrder.json");
		return new Object [][] { {data.get(0)}, {data.get(1)} }; 
	
		
	}
	
	
	
	
	
	/*@DataProvider
	public Object[][] getData() throws IOException
	{
		
		HashMap<String ,String> map = new HashMap<String ,String>();
		map.put("email", "sandeepkumar123@gmail.com");
		map.put("password", "Sandy@123");	
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String ,String> map1 = new HashMap<String ,String>();
		map1.put("email", "rahulkumar123@gmail.com");
		map1.put("password", "Rahul@123");	
		map1.put("product", "IPHONE 13 PRO"); 
		
		return new Object [][] { {map}, {map1} }; 
	
		
	}*/
	
	
	
	
	
	
	
	//2nd type of Data provider if not use HashMap
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object [][] { {"sandeepkumar123@gmail.com", "Sandy@123","ADIDAS ORIGINAL"}, {"rahulkumar123@gmail.com", "Rahul@123","IPHONE 13 PRO"} };
	
	}*/

}
