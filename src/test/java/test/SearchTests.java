package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SearchPage;

public class SearchTests extends BaseTest {

    private SearchPage searchPage;

    @Test(priority = 1)
    public void testValidProductSearch_TC001() {
        searchPage = new SearchPage(driver);
        searchPage.performSearch("ipod touch");
        
        String actualResult = searchPage.getFirstResultText();
        Assert.assertTrue(actualResult.toLowerCase().contains("ipod touch"), 
                "Error: 'ipod touch' was not found.");
    }

    @Test(priority = 2)
    public void testNonExistentProductSearch_TC002() {
        searchPage = new SearchPage(driver);
        searchPage.performSearch("invalid product");
        
        String actualMessage = searchPage.getNoResultsMessage();
        Assert.assertTrue(actualMessage.toLowerCase().contains("There is no product that matches the search criteria"

) || actualMessage.toLowerCase().contains("search criteria"), 
                "Error: 'No results found' message did not appear.");
    }

    @Test(priority = 3)
    public void testPartialKeywordSearch_TC003() {
        searchPage = new SearchPage(driver);
        searchPage.performSearch("samsung");
        
        String actualResult = searchPage.getFirstResultText();
        Assert.assertTrue(actualResult.toLowerCase().contains("samsung"), 
                "Error: Results do not contain 'samsung'.");
    }
    @Test(priority = 4)
    public void testEmptySearch_TC004() {
        searchPage = new pages.SearchPage(driver);
        
        searchPage.enterSearchQuery("");
        
        searchPage.clickSearchButton();
        
        String firstProduct = searchPage.getFirstResultText();
        
        Assert.assertFalse(firstProduct.isEmpty(), 
                "Error: Page did not reload or display products upon empty search.");
    }

    @Test(priority = 5)
    public void testLongCharacterSearch_TC005() {
        searchPage = new SearchPage(driver);
        
        String longQuery = "x".repeat(251);
        searchPage.performSearch(longQuery);
        
        String actualMessage = searchPage.getNoResultsMessage();
        Assert.assertTrue(actualMessage.toLowerCase().contains("no product") || actualMessage.toLowerCase().contains("search criteria"), 
                "Error: System failed to handle maximum limit safely.");
    }

    @Test(priority = 6)
    public void testNumbersAndSymbolsSearch_TC006() {
        searchPage = new SearchPage(driver);
        
        searchPage.performSearch("1234@");
        
        String actualMessage = searchPage.getNoResultsMessage();
        Assert.assertTrue(actualMessage.toLowerCase().contains("no product") || actualMessage.toLowerCase().contains("search criteria"), 
                "Error: Invalid symbols did not trigger validation message.");
    }
}