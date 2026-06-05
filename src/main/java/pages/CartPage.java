package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By firstProductCard = By.xpath("(//div[contains(@class, 'product-thumb')])[1]");
    private By quickAddToCartBtn = By.xpath("(//div[contains(@class, 'product-thumb')])[1]//button[@title='Add to Cart' or contains(@class, 'cart')]");
    
    private By quickBuyNowBtn = By.xpath("(//div[contains(@class, 'product-thumb')])[1]//button[contains(@title, 'Buy') or contains(@class, 'buy')]");
    
    private By quantityInput = By.xpath("//td[contains(@class,'text-left')]//input[contains(@name, 'quantity')] | //div[@class='input-group']//input");
    private By updateCartBtn = By.xpath("//button[@type='submit' and contains(@data-original-title, 'Update') or contains(@title, 'Update')]");
    private By removeProductBtn = By.xpath("//button[contains(@class, 'btn-danger')]");
    private By emptyCartMsg = By.xpath("//div[@id='content']//p[contains(text(), 'empty') or contains(text(), 'empty') or contains(text(), '00')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void hoverAndClickQuickAddToCart() {
        WebElement productCard = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductCard));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productCard);
        
        actions.moveToElement(productCard).perform();
        
        try {
            WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(quickAddToCartBtn));
            cartBtn.click();
        } catch (Exception e) {
            WebElement cartBtn = driver.findElement(quickAddToCartBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartBtn);
        }
        
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public boolean isQuickBuyNowAvailable() {
        try {
            WebElement productCard = driver.findElement(firstProductCard);
            actions.moveToElement(productCard).perform();
            return driver.findElement(quickBuyNowBtn).isDisplayed() && driver.findElement(quickBuyNowBtn).isEnabled();
        } catch (Exception e) {
            return false; 
        }
    }

    public void navigateToCartPage() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
    }

    public void updateQuantity(String qty) {
        WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qtyField.clear();
        qtyField.sendKeys(qty);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(updateCartBtn)).click();
        } catch (Exception e) {
            WebElement btn = driver.findElement(updateCartBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    public void removeProduct() {
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeProductBtn));
            removeBtn.click();
        } catch (Exception e) {
            WebElement removeBtn = driver.findElement(removeProductBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeBtn);
        }
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    public String getEmptyCartMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMsg)).getText();
        } catch (Exception e) {
            return driver.getPageSource();
        }
    }
}
