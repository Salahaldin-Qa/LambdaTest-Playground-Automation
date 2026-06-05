package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTests extends BaseTest {

    private SearchPage searchPage;

    @BeforeMethod
    public void init() {
        searchPage = new SearchPage(driver);
    }

    @DataProvider(name = "searchQueries")
    public Object[][] searchData() {
        return new Object[][] {
            { "ipod touch", true },
            { "samsung", true },
            { "invalid product", false },
            { "x".repeat(251), false },
            { "1234@", false }
        };
    }

    @Test(dataProvider = "searchQueries", priority = 1)
    public void testSearchFunctionality(String keyword, boolean shouldFind) {
        searchPage.performSearch(keyword);
        
        if (shouldFind) {
            String resultText = searchPage.getFirstResultText().toLowerCase();
            Assert.assertTrue(resultText.contains(keyword), "Search result mismatch for: " + keyword);
        } else {
            String errorMsg = searchPage.getNoResultsMessage().toLowerCase();
            Assert.assertTrue(errorMsg.contains("no product") || errorMsg.contains("criteria"), 
                    "Expected 'no results' message for: " + keyword);
        }
    }

    @Test(priority = 2)
    public void testEmptySearch_TC004() {
        searchPage.enterSearchQuery("");
        searchPage.clickSearchButton();
        
        String firstProduct = searchPage.getFirstResultText();
        Assert.assertFalse(firstProduct.isEmpty(), "Page didn't reload items on empty search");
    }
}
