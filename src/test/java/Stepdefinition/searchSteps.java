package Stepdefinition;



import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchSteps {
	
	public static WebDriver driver;
	
	 @Given("^I am on Amazon home page$")
	    public void i_am_on_amazon_home_page() throws Throwable {
		 
		
			System.setProperty("webdriver.chrome.driver", "D:/selenium/chromedriver.exe");
			driver = new ChromeDriver();
			
		driver.get("http://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	         }

	    @When("^search box is available search product with name$")
	    public void i_search_for_a_product_with_name() throws Throwable {
	       driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Apple iPhone 13 (128GB) - Midnight"); 
	       driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
	       driver.findElement(By.xpath("//span[text()='Apple iPhone 13 (128GB) - Midnight']")).click(); 
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	      }

	    @When("^product displayed click on add to cart$")
	    public void Adding_to_cart() throws Throwable {
	    	
	   
	    	String mainWindowHandle = driver.getWindowHandle(); 
	    	Set<String> allWindowHandles = driver.getWindowHandles();
	     	Iterator<String> iterator = allWindowHandles.iterator(); // Here we will check if child window has other child windows and will fetch the heading of the child window 
	    	while (iterator.hasNext()) {
	    	String ChildWindow = iterator.next(); 
	    	
	    	if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) { 
	    		driver.switchTo().window(ChildWindow); 
	    		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
	    		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
	    		driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
	    		
	    		
	    		
	    	}
	    	}
	    	
	    }

	  	    @Then("^compare the added product with expected$")
	    public void compare_product() throws Throwable {
	    	
	    	String text = driver.findElement(By.xpath("(//span[text()='Apple iPhone 13 (128GB) - Midnight'])[2]")).getText(); 
    		System.out.println("value is "+text);
    		try {
    		Assert.assertEquals("Apple iPhone 13 (128GB) - Midnight", text);
    		}catch (AssertionError e) {
    		    System.out.println("Not equal");
    		    throw e;
    		}
    		System.out.println("product selected is matching");
    		
	        
	    }


}
