package com.unicorns.pages;
import com.unicorns.driver.WebDriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public abstract class BasePage {
    WebDriver chromeDriver = WebDriverSingleton.getWebDriver();
    WebDriver edgeDriver = WebDriverSingleton.getWebDriver();
    final int waitTime = 35;
    public BasePage(){
        PageFactory.initElements(new AjaxElementLocatorFactory(chromeDriver, waitTime), this);
    }
    public WebElement findTextByDynamicXpath (String dynamicXpath) {
        WebElement  fieldContainer = edgeDriver.findElement(By.xpath("//*[contains(text(), \"" + dynamicXpath + "\")]"));
        return fieldContainer;
    }
    public boolean elementIsDisplayed(By locator){
       try {
            return WebDriverSingleton.getWebDriver().findElement(locator).isDisplayed();

        } catch (Exception e) {
            return false;
        }

    }

    public boolean elementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement findTextByDynamicXpath (By dynamicXpath) {
        WebElement  fieldContainer = edgeDriver.findElement(By.xpath("//*[contains(text(), \"" + dynamicXpath + "\")]"));
        return fieldContainer;
    }
    public WebElement findElement(By locator){

        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(75));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));


        return WebDriverSingleton.getWebDriver().findElement(locator) ;
    }

    public WebElement waitUntilFindElement(By locator){

        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(75));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));

        return WebDriverSingleton.getWebDriver().findElement(locator) ;
    }
    
    public List<WebElement> findElements(By locator) {
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(35));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));


        return WebDriverSingleton.getWebDriver().findElements(locator) ;
    }

    public WebElement findElementByListOfLocators(By[] locators) throws Exception {
        WebElement element = null ;
//0 -  1 -2
        for (By locator:locators) {
            try{
                element =findElement(locator);
            }catch (Exception e){}
            if (element!=null)
                return element;
        }
        throw new Exception("NoSuchElement : cann't locate the element with specified locators");
    }


    public boolean elementWithMultibleLocatorsIsDisplayed(By[] locators) throws Exception {

        for (By locator : locators) {
            try {
                return WebDriverSingleton.getWebDriver().findElement(locator).isDisplayed();

            } catch (Exception e) {

            }
        }
        return false;
    }




    public void scrollTo(By locator) {
        Actions actionProvider = new Actions(WebDriverSingleton.getWebDriver());
        final int xOffset = 300;
        final int yOffset = 0;
        do {
            if (elementIsDisplayed(locator)) {
                actionProvider.moveToElement(WebDriverSingleton.getWebDriver().findElement(locator), xOffset, yOffset);
                actionProvider.perform();
                break;
            }
            actionProvider.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        while (true);
    }

    public WebElement getElementByPartialText(String text) {

        String textXpath = "//*[contains(text(), \"" + text + "\")]";

        WebDriver driver = WebDriverSingleton.getWebDriver();
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(35));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textXpath)));

        return driver.findElement(By.xpath(textXpath));
    }

    public void clickOnElementWithPartialText(String text) {
        getElementByPartialText(text).click();
    }

    public void zoomOut() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_MINUS);
            }

    public static String getScreenShot() throws IOException {
        File screenShot = ((TakesScreenshot) WebDriverSingleton.getWebDriver()).getScreenshotAs(OutputType.FILE);
        String destination = "./resources/screenshots/"+System.currentTimeMillis()+".png";
        FileUtils.copyFile(screenShot, new File(destination));
        byte [] imageBytes = IOUtils.toByteArray(new FileInputStream(destination));
        return Base64.getEncoder().encodeToString(imageBytes);
    }
    public void waitStaleElementToBeFoundAndClickOnIt(By locator){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(WebDriverSingleton.getWebDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);

        WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        ele.click();

    }
    public String getJsAlertTextAndAcceptTheAlert(){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(35));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }
    public void waitForVisibility(By locator){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
    public void waitForInvisibility(By locator){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
    public void waitForTextToBePresent(By locator,String text){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator,text));
    }
    public void waitForElementAttributeToBePresent(By locator,String attributeName){
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(findElement(locator),attributeName));
    }
    public void click(By elementLocator) {
        click(WebDriverSingleton.getWebDriver(), elementLocator);
    }

    private void click(WebDriver driver, By locator) {
        boolean flag = false ;
        By newLocator = null;
        if(checkLoadingIsInvisible(By.cssSelector("[class*='siebui-busy']"))) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                wait.until(ExpectedConditions.elementToBeClickable(locator));
            } catch (Exception e) {
                if (String.valueOf(locator).contains("By.linkText")) {
                    flag = true;
                }
                if (flag) {
                    String constructXpath = "//*[text()='" + String.valueOf(locator).split(": ")[1].trim() + "']";
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    newLocator = By.xpath(constructXpath);
                    wait.until(ExpectedConditions.elementToBeClickable(newLocator));
                } else {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    wait.until(ExpectedConditions.elementToBeClickable(locator));
                }

            }

            try {
                if (flag) locator = newLocator;
                WebDriverSingleton.getWebDriver().findElement(locator).click();
                ;
            } catch (Exception exception) {

                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                            driver.findElement(locator));
                } catch (Exception rootCauseException) {
                    fail("Couldn't click on the element:" + locator, rootCauseException);
                }
            }
        }
    }

    public void type(By locator, String text) {
        type(WebDriverSingleton.getWebDriver(), locator, text);
    }

    public void type(WebDriver driver, By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
                driver.findElement(locator).sendKeys(text);
                if (!driver.findElement(locator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(locator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(locator));
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(driver.findElement(locator).getAttribute("value").contains(text),
                "The data is not inserted successfully to the field, the inserted data should be: [" + text
                        + "]; But the current field value is: ["
                        + driver.findElement(locator).getAttribute("value") + "]");
    }
    public boolean isElementDisplayed(By locator){
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(25));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return WebDriverSingleton.getWebDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    public static String getText( By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return WebDriverSingleton.getWebDriver().findElement(locator).getText();
        } catch (Exception e) {
            return null;
        }
    }
    public void waitForAttributeToBeNotPresent(By locator,String attribute) {
            WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(60));
            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.attributeToBeNotEmpty(WebDriverSingleton.getWebDriver().findElement(locator)
                                    ,attribute)
                    )
            );

    }

    public boolean checkLoadingIsInvisible(By by){
        return new WebDriverWait(WebDriverSingleton.getWebDriver(),
                Duration.ofSeconds(waitTime)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
