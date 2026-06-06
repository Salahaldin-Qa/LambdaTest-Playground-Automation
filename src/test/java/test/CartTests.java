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
        cartPage.hoverAndClickQuickAddToCart();
        cartPage.navigateToCartPage();
    }

    @DataProvider(name = "cartOperationsProvider")
    public Object[][] getCartTestData() {
        return new Object[][] {
            { "ADD", "" },
            { "UPDATE", "6" },
            { "REMOVE", "" }
        };
    }

    @Test(dataProvider = "cartOperationsProvider", priority = 1)
    public void testCartOperations(String action, String qty) {
        
        if (action.equals("ADD")) {
            boolean isProductVisible = cartPage.isProductDisplayedInCart();
            Assert.assertTrue(isProductVisible, "Check failed: Product did not appear in cart!");
            
        } else if (action.equals("UPDATE")) {
            cartPage.updateQuantity(qty);
            
            String actualQty = cartPage.getCartQuantity();
            Assert.assertEquals(actualQty, qty, "Check failed: Quantity did not update correctly!");
            
        } else if (action.equals("REMOVE")) {
            cartPage.removeProduct();
            
            String msg = cartPage.getEmptyCartMessage().toLowerCase();
            Assert.assertTrue(msg.contains("empty") || msg.contains("your shopping cart is empty!"), 
                    "Check failed: The message 'your cart is empty' did not appear.!");
        }
    }

    @Test(priority = 2)
    public void testQuickBuyNow_TC008() {
        boolean isBuyAvailable = cartPage.isQuickBuyNowAvailable();
        Assert.assertFalse(isBuyAvailable, "Quick Buy Now should be disabled for zero stock!");
    }
}
