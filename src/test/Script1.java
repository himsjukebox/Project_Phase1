package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Script1 {

	public static void main(String[] args) throws InterruptedException {
		
				/* Project Statement
		 * 1) Create new Java Project from scratch : Phase1Project 
		 * 2) Add all the selenium Lib 
		 * 3) Add the chrome driver to project 
		 * 4) You need to launch Amazon.in 
		 * 5) Search the product samsung and click on go button 
		 * 6) Find all* the product search results and their prices and print on the console 
		 * 7) Go inside the first product by clicking on the link 
		 * 8) You need to validate the name is same on the product description page
		 */

//		Initializing web driver
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		Launching Amazon
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
//		Search Samsung		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("samsung");

//		Clicking on search button
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		searchButton.click();

//		Locators for mobile name list and price list
//		List<WebElement> mobNameList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		List<WebElement> mobNameList = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
		List<WebElement> mobPriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

//		Printing mobile name and prices
		for(int i=0; i<mobNameList.size();i++) {
			System.out.println("Mobile Name is:" + mobNameList.get(i).getText() + " with price "+ mobPriceList.get(i).getText());
		}

//		Saving Window handle to a string & Clicking on the first result to open in new tab
		String ParentWindow = driver.getWindowHandle();
		String expectedValue = mobNameList.get(0).getText();
		Thread.sleep(5000);
		mobNameList.get(0).click();

//		Handling multiple windows and shifting control to child window/tab.
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for(String window:allWindowHandles) {
			if(!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
			}
		}

//		Comparing & Verifying expected and actual text. 
		WebElement actualValLoc = driver.findElement(By.id("productTitle"));
		String actualValue = actualValLoc.getText();
		System.out.println(actualValue);
		
		if(actualValue.equals(expectedValue)) {
			System.out.println("Name matched hence Test Case is passed");
		} else {
			System.out.println("Name did not match hence test case failed");
		}
		
		
		
		
		
		
		
		
	}

}
