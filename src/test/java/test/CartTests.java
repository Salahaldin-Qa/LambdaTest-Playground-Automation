package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTests extends BaseTest {

    private CartPage cartPage;

    @BeforeMethod
    public void init() {
        cartPage = new CartPage(driver);
    }

    @DataProvider(name = "cartActions")
    public Object[][] cartData() {
        return new Object[][] {
            { "ADD", "" },
            { "REMOVE", "" },
            { "UPDATE", "3" }
        };
    }

    @Test(dataProvider = "cartActions", priority = 1)
    public void testCartOperations(String action, String qty) {

    	cartPage.hoverAndClickQuickAddToCart();
        cartPage.navigateToCartPage();
        
        if (action.equals("ADD")) {
            Assert.assertNotNull(driver.getTitle(), "Cart page crashed after adding item");
            
        } else if (action.equals("REMOVE")) {
            cartPage.removeProduct();
            String msg = cartPage.getEmptyCartMessage().toLowerCase();
            Assert.assertTrue(msg.contains("empty") || msg.contains("Your shopping cart is empty!"), "Empty cart message not shown");
            
        } else if (action.equals("UPDATE")) {
            cartPage.updateQuantity(qty);
            Assert.assertNotNull(driver.getTitle(), "Page broke after quantity update");
        }
    }

    @Test(priority = 2)
    public void testQuickBuyNow_TC008() {
        boolean isBuyAvailable = cartPage.isQuickBuyNowAvailable();
        Assert.assertFalse(isBuyAvailable, "Quick Buy Now should be disabled for zero stock!");
    }
}
