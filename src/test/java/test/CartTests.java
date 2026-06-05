package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTests {

    private WebDriver driver;
    private CartPage cartPage;
    private final String BASE_URL = "https://ecommerce-playground.lambdatest.io/index.php?route=common/home";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void testQuickAddToCartViaHover_TC007() {
        cartPage.hoverAndClickQuickAddToCart();
        cartPage.navigateToCartPage();
        Assert.assertNotNull(driver.getTitle(), "Error: Page crashed after quick add to cart.");
    }

    @Test(priority = 2)
    public void testQuickBuyNowButton_TC008() {
        boolean isBuyAvailable = cartPage.isQuickBuyNowAvailable();
        System.out.println("TC008 - Quick Buy Now Active: " + isBuyAvailable);
        
        Assert.assertFalse(isBuyAvailable, "Error: Quick Buy Now is bypassable under zero stock options!");
    }

    @Test(priority = 3)
    public void testRemoveProductFromQuickCart_TC009() {
        cartPage.hoverAndClickQuickAddToCart();
        cartPage.navigateToCartPage();
        
        cartPage.removeProduct();
        
        String actualMsg = cartPage.getEmptyCartMessage().toLowerCase();
        Assert.assertTrue(actualMsg.contains("empty") || actualMsg.contains("فارغة") || actualMsg.contains("00"), 
                "Error: Cart clear message did not appear.");
    }

    @Test(priority = 4)
    public void testEditQuantityFromQuickCart_TC010() {
        cartPage.hoverAndClickQuickAddToCart();
        cartPage.navigateToCartPage();
        
        cartPage.updateQuantity("3");
        
        Assert.assertNotNull(driver.getTitle(), "Error: Page broke after updating quantity.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}