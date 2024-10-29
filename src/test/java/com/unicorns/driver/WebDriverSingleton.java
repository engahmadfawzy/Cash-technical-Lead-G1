package com.unicorns.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSingleton {

    private static WebDriver webDriver;
    private static WebDriverSingleton webDriverSingleton = null;

    private WebDriverSingleton() {
        setDriverCapabilities(System.getProperty("BROWSERNAME") );
    }

    public static WebDriverSingleton getDriverSingleton(){
        if (webDriverSingleton == null)
            webDriverSingleton = new WebDriverSingleton();
        return webDriverSingleton;
    }

    private void setDriverCapabilities(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
            webDriver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
            webDriver = new EdgeDriver();
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public void navigateTo(String link) {
        webDriver.navigate().to(link);
    }

    public void refreshCurrentUrl() {
        webDriver.navigate().refresh();
    }

    public void resetCache() throws InterruptedException {
        webDriver.manage().deleteAllCookies();
        Thread.sleep(7000);
    }

    public void maximizeWindow() {
        webDriver.manage().window().maximize();
    }

    public static void close() {
        webDriver.quit();
    }
}
