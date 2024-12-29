package sandeepkumar.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import sandeepkumar.pageObject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		//Email -sandeepkumar123@gmail.com
		//paswword -Sandy@123
		//My name is sandeep
		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
	
	
		
		driver.findElement(By.id("userEmail")).sendKeys("sandeepkumar123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sandy@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//Finding product and verifing it
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod =products.stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
			
		//Visible of adding card 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//.ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//verifying actual product present or not in cart section
		List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//actions key to send keys
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
