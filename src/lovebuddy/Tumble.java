/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovebuddy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Omega
 */
public class Tumble {

    static int followedNumber = 0;
    public WebDriver driver = new FirefoxDriver();

    public void login() {

        driver.navigate().to("https://www.tumblr.com/login"); //open tubmlr

        try {

            WebElement userName_editbox = driver.findElement(By.id("signup_email")); // get the username text field
            WebElement password_editbox = driver.findElement(By.id("signup_password")); // get the password text field
            WebElement submit_button = driver.findElement(By.xpath(".//*[@id='signup_forms_submit']")); //get the submit button

            userName_editbox.sendKeys("freshfresh91@gmail.com"); // enter username
            password_editbox.sendKeys("mastergeekz"); //enter passowrd
            submit_button.click(); // enter submit button
        } catch (Exception e) {

            System.out.println("Fail login"); //in case something goes wrong while attempting to login

        }

    }

    public void openNotes(String note) {

        driver.get(note); //open the note url in the same browser

        //driver.get("http://knowndoorsunknown.tumblr.com/notes/127047825926/kzie9mQE3?from_c=1439957501");
        // driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");// new tab for note
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");// close the blank tab
    }

    public void scrollToBottom() {

        for (int x = 1; x < 300; x++) { //loop 300 times which equal 50 notes * 300 scroll = 15,000 notes

            try {

                driver.findElement(By.xpath(".//*[@class='note more_notes_link_container']/a")).click(); //click show more
                System.out.println("Scrolled Number: " + x); //count the number of scrolls
                Thread.currentThread().sleep(1000); // pause this program for 1 second for the page to load the next show more button

            } catch (Exception e) {

                System.out.println("Fail Scrolling"); //incase the attempt to scroll fails

            }

        }

    }

    public void getUsername() {
        String userName = "";
        Pattern pattern = Pattern.compile("http:\\/\\/(.*)\\.tumblr\\.com.*"); //pattern to pull out username from url
        Matcher m; // the matcher of the string
        List<String> userNames = new ArrayList<>(); // used to store all usernames

        try {
            for (int x = 1; x < 10000; x++) { // loop the list for 9999 names in the list

                m = pattern.matcher(driver.findElement(By.xpath("html/body/ol/li[" + x + "]/span/a[1]")).getAttribute("href")); // match the user url to our pattern

                if (m.matches()) {  // If it matches, get and display group values

                    System.out.println("Found User: " + x); // let us know each time a user is added
                    userNames.add(m.group(1)); // add username to list array
                }
            }

        } catch (Exception e) {

            System.out.println("Fail");
        }

        followUser(userNames);  // now it is time to pass the array list and follow the users

    }

    public void followUser(List<String> userNames) {

        userNames.stream().forEach((userName) -> { //loop the array list until done

            try {

                Thread.currentThread().sleep(1000); // sleep for one second for the webpage to load
                driver.navigate().to("https://www.tumblr.com/search/" + userName); // find user's page

                if ("Follow".equals(driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).getText())) { // if the follow button says follow....then we follow

                    followedNumber++; //keeping track of the number of people we have followed
                    System.out.println("Followed user: " + followedNumber + ". " + userName); // alert us that we have a new follower
                    driver.findElement(By.xpath(".//*[@id='search_actions_search']/div[4]/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/button[2]")).click(); //click follow button
                    //driver.close();
                } else { //if the button does not say follow 

                    System.out.println("Dont Follow user: " + userName); // alert us that we did not follow user 
                    //  driver.close();
                }

            } catch (Exception e) {
            }

        });

    }

}
