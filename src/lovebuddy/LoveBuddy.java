/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovebuddy;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Omega
 */
public class LoveBuddy {

    public WebDriver driver = new FirefoxDriver();

    /**
     * @param args the command line arguments
     */
    public void openTestSite() {
        driver.navigate().to("http://www.tumblr.com/search/rap");

    }

    public void findPopularBlog() {
        driver.findElement(By.xpath(".//*[@id='search_posts']/article[2]/div/div[1]/div/div/span")).click();

    }

    public void addPeople() {
        Actions dragger = new Actions(driver);
        WebElement draggablePartOfScrollbar = driver.findElement(By.xpath(".//*[@id='search_posts']/article[2]/div/div[1]/div/div/div/div/div"));
        
       /* List<WebElement> allElements = driver.findElements(By.xpath(".//*[@id='search_posts']/article[2]/div/div[1]/div/div/div/div/div/div[1]/ol"));

        for (WebElement element : allElements) {
            System.out.println(element.getText());
        }*/
    }

    public static void main(String[] args) {
        // TODO code application logic here
        LoveBuddy webScrapper = new LoveBuddy(); //Here we are creating a instance of this class to make it a object from an idea 
        webScrapper.openTestSite(); // Calling the method openTestSite which will excute the browser to be open
        webScrapper.findPopularBlog();
        webScrapper.addPeople();
    }

}
