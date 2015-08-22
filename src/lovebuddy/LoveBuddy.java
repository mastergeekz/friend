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
import java.util.Scanner;
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // used to accept user input
        String note; // To store url string
        int userChoice; // to store user input
        Tumble tumble = new Tumble(); // create a instance of the tumble class
        System.out.println("Enter 1 for Tumble"); // prompt user to comfirm to run tumble
        userChoice = in.nextInt(); // accept user input
       
        if (userChoice == 1) { // if user choice equals 1
            System.out.println("Tumble has started"); // prompt user that tumble has started
            tumble.login(); // accept login 
            System.out.println("Enter a note url"); // prompt user to enter in note url 
            note = in.next();
            tumble.openNotes(note); // Calling the method openTestSite which will excute the browser to be open
            tumble.scrollToBottom(); // calling method to scroll down to bottom of page to get all of the notes
            tumble.getUsername(); // get the usernames and follow the users
        } else {
// to do code 
        }
    }

}
