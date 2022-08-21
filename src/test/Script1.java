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
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("samsung");
		
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		searchButton.click();
		
//		List<WebElement> mobNameList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		List<WebElement> mobNameList = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
		List<WebElement> mobPriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		for(int i=0; i<mobNameList.size();i++) {
			System.out.println("Mobile Name is:" + mobNameList.get(i).getText() + " with price "+ mobPriceList.get(i).getText());
		}
		
		String ParentWindow = driver.getWindowHandle();
		String expectedValue = mobNameList.get(0).getText();
		Thread.sleep(5000);
		mobNameList.get(0).click();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for(String window:allWindowHandles) {
			if(!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
			}
		}
		
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
