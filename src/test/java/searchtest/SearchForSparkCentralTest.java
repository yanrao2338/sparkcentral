package searchtest;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by yanr on 11/8/16.
 */
public class SearchForSparkCentralTest {
    String baseUrl = "http://www.bing.com/";
    String geckoDriverInWindow = "C:\\jeff\\dowmload\\geckodriver-v0.11.1-win64\\geckodriver.exe";
    String geckoDriverInMac = "/usr/local/bin/geckodriver";
    String geckoDriver;

    SearchResultsPage rsltPage;

    @Before
    public void setup(){
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("win")) {
            geckoDriver = geckoDriverInWindow;
        }else {
            geckoDriver = geckoDriverInMac;
        }
        rsltPage = new SearchResultsPage(geckoDriver, baseUrl);
        rsltPage.searchWithKeywordInBing("sparkcentral");
    }

    @Test
    public void verifyFirstPageResultsInBingSearch(){
        //Verify the first link is "Sparkcentral - Official Site"
        List<WebElement> rsltLinks = rsltPage.getAllSearchResultsInPage();
        WebElement firstLink = rsltLinks.get(0);
        Assert.assertTrue(firstLink.getText().startsWith("Sparkcentral - Official Site"));

        //Verify Product is under first result and points to "https://www.sparkcentral.com/product/"
        WebElement productLink = firstLink.findElement(By.linkText("Product"));
        String productUrl = productLink.getAttribute("href");
        Assert.assertEquals("https://www.sparkcentral.com/product/", productUrl);

        //Verify all results contains "Spartcentral"
        for(int i=1; i<rsltLinks.size();i++) {
            Assert.assertTrue(rsltLinks.get(i).getText().contains("Sparkcentral"));
        }
    }

    @After
    public void tearDown(){
        rsltPage.close();
    }
}
