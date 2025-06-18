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



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class search {

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

	@Test // tim kiem theo ten
	public void TC_001() {
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Tìm']"));
		search.sendKeys("sneaker");

		search.sendKeys(Keys.ENTER);
		WebElement result = driver.findElement(By.xpath("//h2[normalize-space()='Giày sneaker A.F.12']"));
		Assert.assertEquals("Giày sneaker A.F.12", result.getText());

	}

	@Test  // Loai san pham
	public void TC_002() {
		WebElement productButton = driver.findElement(By.xpath("//a[@class='nav-link'][contains(text(),'Sản Phẩm')]"));

		productButton.click();

		WebElement Typebutton = driver.findElement(By.xpath("//a[contains(text(),'Loại')]"));

		Typebutton.click();
		WebElement TypeDetailButton = driver.findElement(By.xpath("//a[normalize-space()='Sneaker']"));

		TypeDetailButton.click();

		WebElement result = driver.findElement(By.xpath("//div[@class='row gx-10']//div[1]//div[1]//div[1]//div[2]"));
		Assert.assertEquals("Sneaker", result.getText());

	}
	
	
	@Test  //gia giam dan
	public void TC_003() {
		WebElement productButton = driver.findElement(By.xpath("//a[@class='nav-link'][contains(text(),'Sản Phẩm')]"));
		productButton.click();

		WebElement Typebutton = driver.findElement(By.xpath("//a[contains(text(),'Loại')]"));
		Typebutton.click();

		WebElement TypeDetailButton = driver.findElement(By.xpath("//button[contains(text(),'Mới Nhất')]"));
		TypeDetailButton.click();

		WebElement SortButton = driver.findElement(By.xpath("//a[contains(text(),'Giá Giảm Dần')]"));
		SortButton.click(); // bạn đang click nhầm TypeDetailButton ở dòng này

	
		WebElement price1Element = driver.findElement(By.xpath("//div[@class='row gx-10']//div[1]//div[1]//div[1]//div[3]//div[1]//span[1]"));
		WebElement price2Element = driver.findElement(By.xpath("//div[contains(@class,'mt-8 mb-lg-14 mb-8')]//div[2]//div[1]//div[1]//div[3]//div[1]//span[1]"));
		WebElement price3Element = driver.findElement(By.xpath("//div[@class='row g-4 row-cols-xl-4 row-cols-lg-3 row-cols-2 row-cols-md-2 mt-2']//div[3]//div[1]//div[1]//div[3]"));

		String p1 = price1Element.getText();
		String p2 = price2Element.getText();
		String p3 = price3Element.getText();
		double price1 = convertPriceToDouble(p1);
		double price2 = convertPriceToDouble(p2);
		double price3 = convertPriceToDouble(p3);


		Assert.assertTrue(price1 >= price2, "price1 phải >= price2");
		Assert.assertTrue(price2 >= price3, "price2 phải >= price3");
	}


	private double convertPriceToDouble(String priceText) {

		String clean = priceText.replace(".", "").replace("đ", "").replace("\u00A0", "").trim();
		return Double.parseDouble(clean);
	}

	
	
	@Test // gia tang dan
	public void TC_004() {
		WebElement productButton = driver.findElement(By.xpath("//a[@class='nav-link'][contains(text(),'Sản Phẩm')]"));
		productButton.click();

		WebElement Typebutton = driver.findElement(By.xpath("//a[contains(text(),'Loại')]"));
		Typebutton.click();

		WebElement TypeDetailButton = driver.findElement(By.xpath("//button[contains(text(),'Mới Nhất')]"));
		TypeDetailButton.click();

		WebElement SortButton = driver.findElement(By.xpath("//a[contains(text(),'Giá Tăng Dần')]"));
		SortButton.click(); 
	
		WebElement price1Element = driver.findElement(By.xpath("//div[@class='row gx-10']//div[1]//div[1]//div[1]//div[3]"));

		WebElement price2Element = driver.findElement(By.xpath("//div[contains(@class,'mt-8 mb-lg-14 mb-8')]//div[2]//div[1]//div[1]//div[3]"));
		WebElement price3Element = driver.findElement(By.xpath("//div[@class='row g-4 row-cols-xl-4 row-cols-lg-3 row-cols-2 row-cols-md-2 mt-2']//div[3]//div[1]//div[1]//div[3]"));

		String p1 = price1Element.getText(); 
		String p2 = price2Element.getText();
		String p3 = price3Element.getText();

		double price1 = convertPriceToDouble(p1);
		double price2 = convertPriceToDouble(p2);
		double price3 = convertPriceToDouble(p3);

		Assert.assertTrue(price1 <= price2, "price1 phải >= price2");
		Assert.assertTrue(price2 <= price3, "price2 phải >= price3");
	}


}
