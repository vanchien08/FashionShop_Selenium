package UC03;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dathang {

    WebDriver driver;
    Scanner scanner = new Scanner(System.in);

    @BeforeTest
    public void init() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.get("http://localhost:8080/FootFlower%20Shop/index.htm");
    }

    @Test(priority = 1)
    public void TC_001() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_001...");
        System.in.read();

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();

        String currentUrl = driver.getCurrentUrl();
        boolean isRedirected = currentUrl.toLowerCase().contains("guest") || currentUrl.toLowerCase().contains("login");

        Assert.assertTrue(
            isRedirected,
            "Không chuyển đến trang đăng nhập khi mở giỏ hàng mà chưa đăng nhập"
        );
    }

    @Test(priority = 2)
    public void TC_002() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_002...");
        System.in.read();

        WebElement usernameField = driver.findElement(By.xpath("//input[@id='username']"));
        usernameField.sendKeys("phantienwk@gmail.com");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        passwordField.sendKeys("123456");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log')]"));
        loginButton.click();

        Thread.sleep(1500);

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();

        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        boolean isCartPage = currentUrl.toLowerCase().contains("cart");

        Assert.assertTrue(
            isCartPage,
            "Sau khi đăng nhập, không vào được trang giỏ hàng"
        );
    }

    @Test(priority = 3)
    public void TC_003() throws Exception {
        System.in.read();

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();
        Thread.sleep(1000);

        List<WebElement> productRows = driver.findElements(By.xpath("//tbody/tr"));
        boolean hasProduct = !productRows.isEmpty();

        if (!hasProduct) {
            Assert.fail("Không có sản phẩm trong giỏ hàng → không phù hợp để test TC_003");
            return;
        }

        boolean hasAddress;
        try {
            driver.findElement(By.xpath("//div[@class='h5 ps-3']"));
            hasAddress = true;
        } catch (NoSuchElementException e) {
            hasAddress = false;
        }

        if (!hasAddress) {
            WebElement checkoutButton = driver.findElement(By.id("btnCheckout"));
            boolean isDisabled = !checkoutButton.isEnabled();
            Assert.assertTrue(isDisabled);
        } else {
            Assert.fail("Có địa chỉ mặc định → không phù hợp để test TC_003");
        }
    }


 
    @Test(priority = 4)
    public void TC_004() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_004...");
        System.in.read();

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();
        Thread.sleep(1000);

        int maxAttempts = 20;
        boolean reachedMax = false;

        for (int i = 0; i < maxAttempts; i++) {
            Thread.sleep(500);

            WebElement plusButton;
            try {
                plusButton = driver.findElement(By.xpath("//a[normalize-space()='+']"));
            } catch (NoSuchElementException e) {
                System.out.println("Không tìm thấy nút '+'");
                break;
            }

            if (!plusButton.isEnabled()) {
                reachedMax = true;
                break;
            }

            plusButton.click();
        }

        WebElement finalPlusButton = driver.findElement(By.xpath("//a[normalize-space()='+']"));
        boolean isDisabled = !finalPlusButton.isEnabled();


    }

    

    @Test(priority = 5)
    public void TC_005() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_005...");
        System.in.read();

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();
        Thread.sleep(1000);

        WebElement mainThanhToanBtn = driver.findElement(By.xpath("//button[normalize-space()='Thanh Toán']"));
        mainThanhToanBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement modalHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[normalize-space()='Confirm Order']")
        ));

        WebElement confirmThanhToanBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space()='Thanh Toán' and contains(@class, 'btn-success')]")
        ));

        confirmThanhToanBtn.click();

        wait.until(ExpectedConditions.urlContains("order/success"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("order/success"),
            "Không chuyển sang trang thành công sau khi xác nhận đặt hàng"
        );
    }
    
    @Test(priority = 6)
    public void TC_006() throws Exception {
        System.in.read();

        WebElement cartIcon = driver.findElement(By.xpath("//i[@class='bi bi-cart3 text-dark']"));
        cartIcon.click();
        Thread.sleep(1000);

        List<WebElement> productRows = driver.findElements(By.xpath("//tbody/tr"));
        boolean hasProduct = !productRows.isEmpty();

        if (hasProduct) {
            Assert.fail("Giỏ hàng có sản phẩm → không phù hợp để test TC_006");
            return;
        }

        List<WebElement> checkoutButtons = driver.findElements(By.id("btnCheckout"));
        boolean hasCheckoutButton = !checkoutButtons.isEmpty();

        Assert.assertFalse(
            hasCheckoutButton,
            "Giỏ hàng trống nhưng vẫn hiển thị nút 'Thanh toán'"
        );

        WebElement emptyCartMessage = driver.findElement(By.xpath("//*[contains(text(),'Không có sản phẩm nào trong mục giỏ hàng')]"));

        Assert.assertTrue(
            emptyCartMessage.isDisplayed(),
            "Không hiển thị thông báo 'Không có sản phẩm nào trong mục giỏ hàng' khi giỏ hàng trống"
        );

    }

    
    @Test(priority = 7)
    public void TC_007() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_007...");
        System.in.read();

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']"));
        accountIcon.click();
        Thread.sleep(1000);

        WebElement orderLink = driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']"));
        orderLink.click();
        Thread.sleep(1500);

        List<WebElement> orderRows = driver.findElements(By.xpath("//table//tbody//tr"));

        boolean hasOrders = !orderRows.isEmpty();

        Assert.assertTrue(
            hasOrders,
            "Không hiển thị danh sách đơn hàng của người dùng"
        );
    }
    
    
    @Test(priority = 8)
    public void TC_008() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_008...");
        System.in.read();

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']"));
        accountIcon.click();
        Thread.sleep(1000);

        WebElement orderLink = driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']"));
        orderLink.click();
        Thread.sleep(1500);

        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));

        boolean found = false;

        for (WebElement row : rows) {
            WebElement statusCell = row.findElement(By.xpath("./td[6]"));
            String status = statusCell.getText().trim();

            if (status.contains("Chờ xác nhận") || status.contains("Đang giao") || status.contains("Đã Hủy") || status.contains("Đã hủy")) {
                WebElement detailIcon = row.findElement(By.xpath(".//i[contains(@class, 'bi-info-circle')]"));
                detailIcon.click();

                Thread.sleep(1500);

                List<WebElement> reviewButtons = driver.findElements(By.xpath("//button[contains(text(),'Đánh giá')]"));

                if (!reviewButtons.isEmpty()) {
                    WebElement reviewButton = reviewButtons.get(0);
                    Assert.assertFalse(
                        reviewButton.isEnabled(),
                        "Nút 'Đánh giá' vẫn hoạt động cho đơn hàng chưa giao"
                    );
                }

                found = true;
                break;
            }
        }

        if (!found) {
            Assert.fail("Không tìm thấy đơn hàng nào ở trạng thái chưa giao (Chờ xác nhận, Đang giao, Đã hủy)");
        }
    }
    
    
    @Test(priority = 9)
    public void TC_009() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_009...");
        System.in.read();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']")).click();
        Thread.sleep(1500);

        boolean found = false;

        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));

        for (int i = 0; i < rows.size(); i++) {
            rows = driver.findElements(By.xpath("//table//tbody//tr"));
            WebElement row = rows.get(i);

            String status = row.findElement(By.xpath("./td[6]")).getText().trim();

            if (status.equalsIgnoreCase("Đã giao")) {
                WebElement detailIcon = row.findElement(By.xpath(".//i[contains(@class, 'bi-info-circle')]"));
                detailIcon.click();
                Thread.sleep(1500);

                List<WebElement> feedbackLinks = driver.findElements(By.xpath("//a[contains(@href, 'feedback.htm')]"));

                if (!feedbackLinks.isEmpty()) {
                    WebElement feedbackLink = feedbackLinks.get(0);
                    feedbackLink.click();
                    Thread.sleep(1500);

                    WebElement starInput = driver.findElement(By.id("star-input"));
                    Assert.assertEquals(starInput.getAttribute("value"), "0", "Giá trị sao ban đầu không phải 0");

                    WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Thêm')]"));
                    submitButton.click();
                    Thread.sleep(1000);

                    String validationMessage = (String) ((JavascriptExecutor) driver)
                            .executeScript("return arguments[0].validationMessage;", starInput);

                    System.out.println("Validation message: " + validationMessage);

                    Assert.assertTrue(
                        validationMessage.toLowerCase().contains("no less than 1"),
                        "Không hiển thị thông báo lỗi khi chưa chọn sao"
                    );

                    found = true;
                    break;
                } else {
                    driver.navigate().back();
                    Thread.sleep(1000);
                }
            }
        }

        if (!found) {
            Assert.fail("Không tìm thấy đơn hàng nào đã giao có nút đánh giá để kiểm tra.");
        }
    }

    
    @Test(priority = 10)
    public void TC_010() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_010...");
        System.in.read();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']")).click();
        Thread.sleep(1500);

        boolean found = false;

        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));

        for (int i = 0; i < rows.size(); i++) {
            rows = driver.findElements(By.xpath("//table//tbody//tr"));
            WebElement row = rows.get(i);

            String status = row.findElement(By.xpath("./td[6]")).getText().trim();

            if (status.equalsIgnoreCase("Đã giao")) {
                WebElement detailIcon = row.findElement(By.xpath(".//i[contains(@class, 'bi-info-circle')]"));
                detailIcon.click();
                Thread.sleep(1500);

                List<WebElement> feedbackLinks = driver.findElements(By.xpath("//a[contains(@href, 'feedback.htm')]"));

                if (!feedbackLinks.isEmpty()) {
                    WebElement feedbackLink = feedbackLinks.get(0);
                    feedbackLink.click();
                    Thread.sleep(1500);

                    WebElement starInput = driver.findElement(By.id("star-input"));
                    starInput.clear();
                    starInput.sendKeys("5");

 
                    WebElement editorIframe = driver.findElement(By.cssSelector("iframe.cke_wysiwyg_frame"));
                    driver.switchTo().frame(editorIframe);

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].innerHTML = '<p>Sản phẩm rất tốt, giao hàng nhanh.</p>'", 
                                     driver.findElement(By.tagName("body")));

                    driver.switchTo().defaultContent();


                    WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Thêm')]"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
                    Thread.sleep(500);
                    submitButton.click();
                    Thread.sleep(1500);

                    boolean isReviewShown = driver.getPageSource().contains("Sản phẩm rất tốt") || 
                                            driver.getPageSource().toLowerCase().contains("đánh giá của bạn đã được ghi nhận");

                    Assert.assertTrue(isReviewShown, "Không tìm thấy đánh giá sau khi gửi.");

                    found = true;
                    break;
                } else {
                    driver.navigate().back();
                    Thread.sleep(1000);
                }
            }
        }

        if (!found) {
            Assert.fail("Không tìm thấy đơn hàng nào đã giao có nút đánh giá để kiểm tra.");
        }
    }

    
    @Test(priority = 11)
    public void TC_011() throws Exception {
        System.out.println("Nhấn Enter để bắt đầu TC_011...");
        System.in.read();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//i[@class='bi bi-person text-dark']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[@href='account/ordersHistory.htm']")).click();
        Thread.sleep(1500);

        boolean found = false;

        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));

        for (int i = 0; i < rows.size(); i++) {
            rows = driver.findElements(By.xpath("//table//tbody//tr"));
            WebElement row = rows.get(i);

            String status = row.findElement(By.xpath("./td[6]")).getText().trim();

            if (status.equalsIgnoreCase("Đã giao")) {
                WebElement detailIcon = row.findElement(By.xpath(".//i[contains(@class, 'bi-info-circle')]"));
                detailIcon.click();
                Thread.sleep(1500);

                List<WebElement> feedbackLinks = driver.findElements(By.xpath("//a[contains(@href, 'feedback.htm')]"));
                if (!feedbackLinks.isEmpty()) {
                    WebElement feedbackLink = feedbackLinks.get(0);
                    feedbackLink.click();
                    Thread.sleep(2000);

                    String currentUrl = driver.getCurrentUrl().toLowerCase();
                    boolean isFeedbackPage = currentUrl.contains("feedback") || currentUrl.contains("review");


                    Assert.assertTrue(isFeedbackPage, "Không chuyển đến trang lịch sử đánh giá sau khi nhấn nút đánh giá.");
                    found = true;
                    break;
                } else {
                    driver.navigate().back();
                    Thread.sleep(1000);
                }
            }
        }

        if (!found) {
            Assert.fail("Không tìm thấy đơn hàng đã giao có link đến lịch sử đánh giá.");
        }
    }

}
