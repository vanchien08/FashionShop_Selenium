package UC06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;



public class wishlist {
	WebDriver driver;

	@BeforeTest
	public void init() {
		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		// options.addArguments("-private"); // Chạy chế độ riêng tư để tránh xung đột
		// profile

		driver = new FirefoxDriver(options);
		driver.get("http://localhost:8080/FootFlower%20Shop/index.htm");
	}

	@Test
	public void TC_005() {
	    WebDriverWait wait = new WebDriverWait(driver, 10);

	    
		WebElement prButton = driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']"));
		prButton.click();
		
		WebElement userlogin = driver.findElement(By.xpath("//input[@id='username']"));
		userlogin.sendKeys("nguyenchien113dddazs@gmail.com");
		
		WebElement passlogin = driver.findElement(By.xpath("//input[@id='password']"));
		passlogin.sendKeys("123456");
		
		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log')]"));
		loginButton.click();
	    WebElement productButton = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//a[@class='nav-link'][contains(text(),'Sản Phẩm')]")));
	    productButton.click();

	    WebElement imageElement = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//div[@class='row gx-10']//div[1]//img[1]")));
	    imageElement.click();

//	    // Đảm bảo đã vào trang chi tiết
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(
//	        By.xpath("//h1[contains(@class,'product-title')]")));

	    // Click nút yêu thích
//	    WebElement favoriteButton = wait.until(ExpectedConditions.elementToBeClickable(
//	        By.xpath("//div[@class='mt-3 row justify-content-start g-2 align-items-center']//i[1]")));
//	    favoriteButton.click();
	    
	    
		WebElement favoriteButton = driver.findElement(By.xpath("//a[contains(@class,'btn-light') and .//i[contains(@class,'bi-heart')]]"));
		favoriteButton.click();

		
		 
	

	    // Chờ alert hiển thị
	    WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//*[contains(@class, 'alert') or @role='alert']")));

	    Assert.assertEquals(alert.getText().trim(), "Thêm vào danh sách yêu thích thành công");
	}



	

	@Test
	public void TC_006() {
	    WebDriverWait wait = new WebDriverWait(driver, 10);

	    
		WebElement prButton = driver.findElement(By.xpath("//i[@class='bi bi-heart text-dark']"));
		prButton.click();
		
		
		
//		WebElement userlogin = driver.findElement(By.xpath("//input[@id='username']"));
//		userlogin.sendKeys("nguyenchien113dddazs@gmail.com");
//		
//		WebElement passlogin = driver.findElement(By.xpath("//input[@id='password']"));
//		passlogin.sendKeys("123456");
//		
//		WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log')]"));
//		loginButton.click();
		

		WebElement deleteButton = driver.findElement(By.xpath("//i[@class='bi bi-trash']"));
		deleteButton.click();
	  
		 
	
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//div[@class='alert alert-success alert-dismissible fade show']")
			));
	    Assert.assertEquals(alert.getText().trim(), "Xóa khỏi danh sách yêu thích thành công");
	}




	@AfterTest
	public void tearDown() {
		// driver.quit();
	}
}
