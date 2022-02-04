package com.appium.sanity;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class NativeAppTest {

    private AndroidDriver driver;
    private WebElement formulaElement, resultElement;
    private WebElement operationAdd,operationSubstract, equals;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("avd", "Nexus5X");
        capabilities.setCapability("noReset", true);
        //capabilities.setCapability("automationName", "UiAutomator2");
        //capabilities.setCapability("app", "absoulte path to the app");
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        String context = driver.getContext();
        System.out.println(context);
        driver.context("NATIVE_APP");


        formulaElement = driver.findElement(By.id("com.android.calculator2:id/formula"));
        resultElement = driver.findElement(By.id("com.android.calculator2:id/result"));
        operationAdd = driver.findElement(By.id("com.android.calculator2:id/op_add"));
        operationSubstract = driver.findElement(By.id("com.android.calculator2:id/op_sub"));
        equals = driver.findElement(By.id("com.android.calculator2:id/eq"));
    }

    public void addCalc(int a, int b){
        WebElement number1 = this.driver.findElement(By.id("com.android.calculator2:id/digit_" + String.valueOf(a)));
        WebElement number2 = this.driver.findElement(By.id("com.android.calculator2:id/digit_" + String.valueOf(b)));
        number1.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        operationAdd.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number2.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("s");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void appiumHelloWorld() throws InterruptedException {
        Thread.sleep(1000);
        addCalc(1,3);
        Thread.sleep(3000);
    }
}