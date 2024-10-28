package com.unicorns.browser;

import com.unicorns.platform.Unicorns;
import com.unicorns.driver.WebDriverSingleton;
import io.cucumber.java.lv.Un;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.WebElement;

public class Browser {

    private final WebDriverSingleton webDriverSingleton = WebDriverSingleton.getDriverSingleton();
    public Unicorns unicorns;

    private Alert alert;

    public Browser() {
        unicorns = new Unicorns();
    }

    public Alert getAlert() {
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(80));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = WebDriverSingleton.getWebDriver().switchTo().alert();
        return alert;
    }
    public void pressOnCtrlSToSave(){

        Keys.chord(Keys.chord(Keys.CONTROL+"S"));
    }

    public WebElement switchToActiveElement() {
        WebElement activeElement = WebDriverSingleton.getWebDriver().switchTo().activeElement();
        return activeElement;
    }
}
