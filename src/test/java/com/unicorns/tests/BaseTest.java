package com.unicorns.tests;

import com.unicorns.browser.Browser;
import com.unicorns.driver.WebDriverSingleton;

import java.util.Properties;

public class BaseTest {

    protected WebDriverSingleton driver;
    protected static Browser browser = new Browser();
    protected static Properties properties;
    protected static Properties data;

    public BaseTest(){
        driver = WebDriverSingleton.getDriverSingleton();
    }
}
