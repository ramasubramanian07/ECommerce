import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class ECommerceTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://your-github-hosted-url/products");
    }

    @Test(priority = 1)
    public void searchAndAddProductToCart() {
        driver.findElement(By.id("searchInput")).sendKeys("Laptop");
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.cssSelector(".add-to-cart[data-product-id='1']")).click();

        driver.get("https://your-github-hosted-url/cart");

        WebElement productName = driver.findElement(By.cssSelector(".product-name"));
        WebElement productPrice = driver.findElement(By.cssSelector(".product-price"));
        Assert.assertEquals(productName.getText(), "Laptop");
        Assert.assertEquals(productPrice.getText(), "$1000");
    }

    @Test(priority = 2)
    public void updateCartAndCheckout() {
        WebElement quantityField = driver.findElement(By.cssSelector(".cart-quantity"));
        quantityField.clear();
        quantityField.sendKeys("2");

        WebElement grandTotal = driver.findElement(By.id("grandTotal"));
        Assert.assertEquals(grandTotal.getText(), "$2000");

        driver.findElement(By.cssSelector(".remove-btn")).click();
        WebElement updatedGrandTotal = driver.findElement(By.id("grandTotal"));
        Assert.assertEquals(updatedGrandTotal.getText(), "$0");
    }

    @Test(priority = 3)
    public void completeCheckoutProcess() {
        driver.get("https://your-github-hosted-url/products");
        driver.findElement(By.cssSelector(".add-to-cart[data-product-id='1']")).click();
        driver.get("https://your-github-hosted-url/cart");
        driver.findElement(By.id("checkoutBtn")).click();

        driver.findElement(By.id("billingName")).sendKeys("Rama");
        driver.findElement(By.id("billingAddress")).sendKeys("Chennai, India");
        driver.findElement(By.id("paymentMethod")).sendKeys("Credit Card");

        driver.findElement(By.id("placeOrderBtn")).click();

        WebElement orderSuccessMessage = driver.findElement(By.id("orderSuccess"));
        Assert.assertEquals(orderSuccessMessage.getText(), "Order Placed Successfully!");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
