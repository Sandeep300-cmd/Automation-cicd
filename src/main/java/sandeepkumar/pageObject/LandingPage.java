package sandeepkumar.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandeepkumar.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	//WebElement userName =driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(id ="userEmail")
	WebElement userName;
	
	@FindBy(id ="userPassword")
	WebElement passwordEle;
	
	@FindBy(id ="login")
	WebElement submit;
	
	@FindBy(css ="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email ,String password)
	{
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	//.ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	public String geterrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	
	
	public void goToWebsite()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
}
