/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovebuddy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

/**
 *
 * @author Omega
 */
public class LoveBuddy {

    public WebDriver driver = new FirefoxDriver();

    public void login() {
        driver.navigate().to("https://www.tumblr.com/login");

        WebElement userName_editbox = driver.findElement(By.id("signup_email"));
        WebElement password_editbox = driver.findElement(By.id("signup_password"));
        WebElement submit_button = driver.findElement(By.xpath(".//*[@id='signup_forms_submit']"));

        userName_editbox.sendKeys("freshfresh91@gmail.com");
        password_editbox.sendKeys("mastergeekz");
        submit_button.click();

    }

    public void openTestSite() {
        driver.get("http://knowndoorsunknown.tumblr.com/notes/127047825926/kzie9mQE3?from_c=1439957501");
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");// new tab open
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");// close the tab

    }

    public void scrollToBottom() {
        //driver.findElement(By.xpath(".//*[@id='search_posts']/article[2]/div/div[1]/div/div/span")).click();
        for (int x = 0; x < 1000; x++) {
            try {
                driver.findElement(By.xpath(".//*[@id='more_notes_127047825926']/a")).click();
                Thread.currentThread().sleep(1000);
            } catch (Exception e) {
                System.out.println("Fail");
            }

        }

    }

    public void getUsername() {
        String userName = "";
        Pattern pattern = Pattern.compile("http:\\/\\/(.*)\\.tumblr\\.com.*");
        // Get matcher on this String.
        Matcher m;
        List<String> userNames = new ArrayList<>();
        try {
            for (int x = 1; x < 5; x++) {
                m = pattern.matcher(driver.findElement(By.xpath("html/body/ol/li[" + x + "]/span/a[1]")).getAttribute("href"));
                Thread.currentThread().sleep(1000);

                // If it matches, get and display group values.
                if (m.matches()) {
                    userNames.add(m.group(1));// = m.group(1);
                }

                //driver.findElement(By.xpath("html/body/ol/li[1]/span/a[1]")).click(); //click on user name
                Thread.currentThread().sleep(1000);
                //  followUser(userName);
            }
        } catch (Exception e) {
            System.out.println("Fail");
        }

        followUser(userNames);

    }

    public void followUser(List<String> userNames) {

        userNames.stream().forEach((userName) -> {
            System.out.println(userName);
            driver.navigate().to("https://www.tumblr.com/search/" + userName);
            if ("Follow".equals(driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).getAttribute("class"))) {
                System.out.println("System says: " + driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).getText());

                System.out.println("Followed");
                driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).click(); //click on user name
                // driver.close();
            } else {
                System.out.println("System says: " + driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).getAttribute("class"));

                System.out.println("Dont Follow");
                //  driver.close();

            }

        });

    }

    public static void main(String[] args) {
        // TODO code application logic here
        LoveBuddy webScrapper = new LoveBuddy(); //Here we are creating a instance of this class to make it a object from an idea 
        webScrapper.login();
        webScrapper.openTestSite(); // Calling the method openTestSite which will excute the browser to be open
        // webScrapper.scrollToBottom();
        webScrapper.getUsername();
    }

}
