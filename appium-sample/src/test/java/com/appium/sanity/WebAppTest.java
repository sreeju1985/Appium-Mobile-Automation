package com.appium.sanity;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class WebAppTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("avd", "Nexus5X");
        capabilities.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        String context = driver.getContext();
        acceptGoogleWelcome();
        driver.context(context);


    }

    public void acceptGoogleWelcome(){
        driver.context("NATIVE_APP");
        try{
            WebElement agreeCheckBox =driver.findElement(By.id("send_report_checkbox"));
            agreeCheckBox.click();

            Thread.sleep(2000);

            WebElement termsAccept =driver.findElement(By.id("terms_accept"));
            termsAccept.click();

            Thread.sleep(2000);

            WebElement positive_button =driver.findElement(By.id("positive_button"));
            positive_button.click();
            Thread.sleep(2000);

            positive_button =driver.findElement(By.id("positive_button"));
            positive_button.click();
            Thread.sleep(2000);
        }
        catch (Exception e){

        }

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void webAppTest() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("https://signupgenius.com");
        Thread.sleep(5000);

        WebElement acceptGotIt = driver.findElement(By.cssSelector("a[class='btn btn-primary sug-notice--privacy-button']"));
        acceptGotIt.click();
        Thread.sleep(1000);
        WebElement register = driver.findElement(By.cssSelector("div.forgot-container>a[href*='registerNewUser']"));
        register.click();
        Thread.sleep(4000);
        WebElement firstName = driver.findElement(By.cssSelector("input[placeholder='First Name']"));
        WebElement lastName = driver.findElement(By.cssSelector("input[placeholder='Last Name']"));
        WebElement emailAddress = driver.findElement(By.cssSelector("input[placeholder='Email Address']"));
        WebElement reEnterEmailAddress = driver.findElement(By.cssSelector("input[placeholder='Re-enter Email Address']"));

        firstName.sendKeys("Sonu");
        lastName.sendKeys("Sadasivan");
        emailAddress.sendKeys("sonu.sadasivan@ust-global.com");
        reEnterEmailAddress.sendKeys("sonu.sadasivan@ust-global.com");

        Thread.sleep(5000);
    }
}