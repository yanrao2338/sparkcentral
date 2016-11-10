package sparkcentral;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Created by yanr on 11/8/16.
 */
public class SearchResultsPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    String baseUrl = "http://www.bing.com/";
    String geckoDriverInWindow = "C:\\gecko";
    String geckoDriverInMac = "/usr/local/bin/geckodriver";
    String geckoDriver;

    public void goToSearchEngineWebsite(String url){
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("win")) {
            geckoDriver = geckoDriverInWindow;
        }else {
            geckoDriver = geckoDriverInMac;

        }

        System.setProperty("webdriver.gecko.driver", geckoDriver);
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    public void searchForWithKeyword(String keyWord){
        driver.findElement(By.id("sb_form_q")).click();
        driver.findElement(By.id("sb_form_q")).clear();
        driver.findElement(By.id("sb_form_q")).sendKeys(keyWord);
        driver.findElement(By.id("sb_form_go")).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");


    }

    public List<WebElement> getAllSearchResultsInPage(){
        List<WebElement> list = driver.findElements(By.className("b_algo"));
        return list;
    }


    public WebElement findElementByLinkText(String linkText) {
        WebElement link = driver.findElement(By.linkText(linkText));
        return link;
    }


    public String getPageSource(){
        return driver.getPageSource();
    }


    public static void main(String[] args){
        SearchResultsPage page = new SearchResultsPage();
        page.goToSearchEngineWebsite("http://www.bing.com");
        page.searchForWithKeyword("sparkcentral");
        List<WebElement> urlList = page.getAllSearchResultsInPage();
        System.out.println("count: " + urlList.size());

        for(WebElement element : urlList) {
            System.out.println(element.getText());
        }


        WebElement productLink = page.findElementByLinkText("Product");
        System.out.println("by link text: " + productLink.getAttribute("href"));


        System.out.print("\n----------------------\n");
        System.out.print(page.getPageSource());
        System.out.print("\n----------------------\n");

    }

}
