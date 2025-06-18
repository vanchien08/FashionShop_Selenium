package UC02;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testcase2 {

	WebDriver driver;
	@BeforeTest
	public void init() {
		 WebDriverManager.firefoxdriver().setup();
		    
		    FirefoxOptions options = new FirefoxOptions();
		//    options.addArguments("-private"); // Chạy chế độ riêng tư để tránh xung đột profile

		    driver = new FirefoxDriver(options);
		    driver.get("http://localhost:8080/FootFlower%20Shop/product/detail.htm?productId=1");
	}
	
	// Test case thêm vào giỏ hàng chưa đăng nhập
	@Test
	public void TC_001() {
		WebElement sanpham = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		sanpham.click(); 

		WebElement result= driver.findElement(By.xpath("//h1[@class='mb-1 h2 fw-bold']"));
		Assert.assertEquals("Sign in to Fashion Shop", result.getText());
	}
	
	@Test
	public void TC_002() {
		
		WebElement name = driver.findElement(By.xpath("//input[@id='username']"));
		name.sendKeys("phantienwk@gmail.com");
		
		WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
		pass.sendKeys("123456");
		
		WebElement pass1 = driver.findElement(By.xpath("//button[contains(text(),'Log')]"));
		pass1.click();
		
		driver.get("http://localhost:8080/FootFlower%20Shop/product/detail.htm?productId=1");
		WebElement result= driver.findElement(By.xpath("//h1[normalize-space()='Giày sneaker A.F.12']"));
		
//		//div[@class='mt-3 row justify-content-start g-2 align-items-center']//i[1]
//		WebElement pass2 = driver.findElement(By.xpath("//div[@class='mt-3 row justify-content-start g-2 align-items-center']//i[1]"));
//		pass2.click();
		Assert.assertEquals("Giày sneaker A.F.12", result.getText());
	}
	
	
	@Test
	public void TC_003() {
		WebElement name = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		name.click();
		
	}
	
	@Test
	public void TC_004() {
		//button[normalize-space()='36']
		WebElement size = driver.findElement(By.xpath("//button[normalize-space()='36']"));
		size.click();
		WebElement name = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		name.click();
		WebElement btn = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
		btn.click();
		//a[normalize-space()='Giày sneaker A.F.12']
		//small[normalize-space()='Size: 36']
		//label[normalize-space()='1']
		WebElement result1 = driver.findElement(By.xpath("//a[normalize-space()='Giày sneaker A.F.12']"));
		WebElement result2 = driver.findElement(By.xpath("//small[normalize-space()='Size: 36']"));
		WebElement result3 = driver.findElement(By.xpath("//label[normalize-space()='1']"));
		Assert.assertEquals("Giày sneaker A.F.12Size: 361", result1.getText()+result2.getText()+result3.getText());
	}
	
	@Test
	public void TC_005() {
		driver.get("http://localhost:8080/FootFlower%20Shop/product/detail.htm?productId=1");
		//button[normalize-space()='36']
		WebElement size = driver.findElement(By.xpath("//button[normalize-space()='36']"));
		size.click();
		WebElement name = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		name.click();
		WebElement btn = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
		btn.click();
		//a[normalize-space()='Giày sneaker A.F.12']
		//small[normalize-space()='Size: 36']
		//label[normalize-space()='1']
		WebElement result1 = driver.findElement(By.xpath("//a[normalize-space()='Giày sneaker A.F.12']"));
		WebElement result2 = driver.findElement(By.xpath("//small[normalize-space()='Size: 36']"));
		WebElement result3 = driver.findElement(By.xpath("//label[normalize-space()='2']"));
		Assert.assertEquals("Giày sneaker A.F.12Size: 362", result1.getText()+result2.getText()+result3.getText());
	}
	
	//i[@class='bi bi-trash']
	@Test
	public void TC_006() {
		WebElement xoa = driver.findElement(By.xpath("//i[@class='bi bi-trash']"));
		xoa.click();
		driver.get("http://localhost:8080/FootFlower%20Shop/product/detail.htm?productId=1");
		//button[normalize-space()='36']
		WebElement size1 = driver.findElement(By.xpath("//button[normalize-space()='36']"));
		size1.click();
		WebElement name1 = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		name1.click();
		WebElement size2 = driver.findElement(By.xpath("//button[normalize-space()='37']"));
		size2.click();
		WebElement name2 = driver.findElement(By.xpath("//a[@id='btn-add-cart']"));
		name2.click();
		
		
		WebElement btn = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
		btn.click();
		//tbody[1]/tr[1]/td[2]/div[1]/div[1]/h5[1]/a[1]
		//small[normalize-space()='Size: 36']
		//tbody[1]/tr[1]/td[4]/label[1]
		//tbody[1]/tr[1]/td[2]/div[1]/div[1]/h5[1]/a[1]
		//small[normalize-space()='Size: 37']
		//tbody[1]/tr[1]/td[4]/label[1]
		
		WebElement result1 = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[2]/div[1]/div[1]/h5[1]/a[1]"));
		WebElement result2 = driver.findElement(By.xpath("//small[normalize-space()='Size: 36']"));
		WebElement result3 = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[4]/label[1]"));
		WebElement result4 = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[2]/div[1]/div[1]/h5[1]/a[1]"));
		WebElement result5 = driver.findElement(By.xpath("//small[normalize-space()='Size: 37']"));
		WebElement result6 = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[4]/label[1]"));
		
		Assert.assertEquals("Giày sneaker A.F.12Size: 361Giày sneaker A.F.12Size: 371", result1.getText()+result2.getText()+result3.getText()+result4.getText()+result5.getText()+result6.getText());
	}
	
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
