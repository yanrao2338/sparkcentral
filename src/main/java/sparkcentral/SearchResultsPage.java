package searchtest;

import java.util.*;
import java.util.concurrent.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by yanr on 11/8/16.
 */

public class SearchResultsPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public SearchResultsPage(String geckoDriverPath, String searchEngineUrl){
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        driver = new FirefoxDriver();
        driver.get(searchEngineUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void searchWithKeywordInBing(String keyWord){
        driver.findElement(By.id("sb_form_q")).click();
        driver.findElement(By.id("sb_form_q")).clear();
        driver.findElement(By.id("sb_form_q")).sendKeys(keyWord);
        driver.findElement(By.id("sb_form_go")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public List<WebElement> getAllSearchResultsInPage(){
        return  driver.findElements(By.className("b_algo"));
    }

    public WebElement findElementByLinkText(String linkText) {
        WebElement link = driver.findElement(By.linkText(linkText));
        return link;
    }

    public void close(){
        driver.quit();
    }
}
