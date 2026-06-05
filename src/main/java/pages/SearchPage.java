package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInput = By.xpath("//div[@id='search']//input[@name='search']");
    private By searchButton = By.xpath("//button[contains(@class, 'type-submit')]");
    private By firstProductTitle = By.xpath("//div[contains(@class, 'product-layout')]//h4[@class='title']/a");
    
    private By noResultTxt = By.xpath("//div[@id='content']//p");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterSearchQuery(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.click(); 
        input.clear();
        input.sendKeys(text);
    }

    public void clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        } catch (Exception e) {
            driver.findElement(searchInput).sendKeys(Keys.ENTER);
        }
    }

    public void performSearch(String text) {
        enterSearchQuery(text);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public String getFirstResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductTitle)).getText();
    }

    public String getNoResultsMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(noResultTxt)).getText();
        } catch (Exception e) {
            return driver.getPageSource();
        }
    }
    
    public boolean isSearchInputVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}