package UC01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;

public class search {

	WebDriver driver;
	@BeforeTest
	public void init() {
		 WebDriverManager.firefoxdriver().setup();
		    
		    FirefoxOptions options = new FirefoxOptions();
		//    options.addArguments("-private"); // Chạy chế độ riêng tư để tránh xung đột profile

		    driver = new FirefoxDriver(options);
		    driver.get("http://localhost:8080/FootFlower%20Shop/index.htm");
	}
	@Test
	public void TC_001() {
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Tìm']"));
		search.sendKeys("sneaker"); 
			
		search.sendKeys(Keys.ENTER);
		WebElement result= driver.findElement(By.xpath("//h2[normalize-space()='Giày sneaker A.F.12']"));
		Assert.assertEquals("Giày sneaker A.F.12", result.getText());
		
		
		
		
	}
	
//	@Test
//	public void TC_002() {
//		WebElement search = driver.findElement(By.xpath("//input[@id='checkin']"));
//		search.sendKeys("2025-03-18"); 
//		
//		WebElement search1 = driver.findElement(By.xpath("//input[@id='checkout']"));
//		search1.sendKeys("2024-1-31");
//		
//		WebElement searchbnt= driver.findElement(By.xpath("//button[normalize-space()='Tìm']"));
//		searchbnt.click();
//		
//		WebElement result= driver.findElement(By.xpath("//div[@class='ant-modal-body']"));
//		Assert.assertEquals("Ngày bắt đầu phải nhỏ hơn ngày kết thúc !", result.getText());
//		
//		
//		
//		
//	}
//	
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
