package UC04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testcase {

	
	WebDriver driver;
	@BeforeTest
	public void init() {
		 WebDriverManager.firefoxdriver().setup();
		    
		    FirefoxOptions options = new FirefoxOptions();
		//    options.addArguments("-private"); // Chạy chế độ riêng tư để tránh xung đột profile

		    driver = new FirefoxDriver(options);
		    driver.get("http://localhost:8080/FootFlower%20Shop/guest.htm");
	}
	
	
	
	@Test
	public void TC_001() {
		
		WebElement name = driver.findElement(By.xpath("//input[@id='username']"));
		name.sendKeys("phantienwk@gmail.com");
		
		WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
		pass.sendKeys("123456");
		
		WebElement pass1 = driver.findElement(By.xpath("//button[contains(text(),'Log')]"));
		pass1.click();
		
		
	}
	
	
	@Test
	public void TC_002() {
		
		WebElement btn = driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']"));
		btn.click();
		
		WebElement btn1 = driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']"));
		btn1.click();
		
		WebElement result1 = driver.findElement(By.xpath("//div[@class='h4 text-uppercase']"));
		WebElement result2 = driver.findElement(By.xpath("//td[normalize-space()='29-05-2025 08:58']"));
		WebElement result3 = driver.findElement(By.xpath("//td[normalize-space()='29-05-2025 09:28']"));
		WebElement result4 = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[4]/ul[1]/li[1]"));
		Assert.assertEquals("ĐƠN HÀNG29-05-2025 08:5829-05-2025 09:28Giày thể thao nam", result1.getText()+result2.getText()+result3.getText()+result4.getText());
	}
	
	
	//121
	//tbody[1]/tr[1]/td[1]/a[1]/i[1]
	//div[@class='h5 ps-3']
	//div[@class='text-secondary fst-italic']
	//div[@class='h4 text-uppercase']
	@Test
	public void TC_003() {
		
		WebElement btn = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[1]/a[1]/i[1]"));
		btn.click();
		
		WebElement result1 = driver.findElement(By.xpath("//div[@class='h5 ps-3']"));
		WebElement result2 = driver.findElement(By.xpath("//div[@class='text-secondary fst-italic']"));
		WebElement result4 = driver.findElement(By.xpath("//div[@class='h4 text-uppercase']"));
		Assert.assertEquals("Trần Nguyên Nguyên (+84) 0856738591A50, Phường Đa Kao, Quận 1, TP. Hồ Chí MinhCHI TIẾT ĐƠN HÀNG", result1.getText()+result2.getText()+result4.getText());
	}
	
	@Test
	public void TC_004() {
		
		WebElement result1 = driver.findElement(By.xpath("//span[@class='badge bg-warning']"));
		WebElement result2 = driver.findElement(By.xpath("//button[contains(text(),'Hủy yêu cầu')]"));
		Assert.assertEquals("Chờ Xác NhậnHủy yêu cầu", result1.getText()+result2.getText());
	}
	//a[contains(text(),'Hủy yêu cầu')]
	
	@Test
	public void TC_005() {
		
		WebElement btn1 = driver.findElement(By.xpath("//a[contains(text(),'Hủy yêu cầu')]"));
		WebElement btn2 = driver.findElement(By.xpath("//button[contains(text(),'Hủy yêu cầu')]"));
		btn2.click();
		btn1.click();
		
		
		WebElement result1 = driver.findElement(By.xpath("//span[@class='badge bg-danger']"));
		Assert.assertEquals("Đã Hủy", result1.getText());
	}

	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
	
}
