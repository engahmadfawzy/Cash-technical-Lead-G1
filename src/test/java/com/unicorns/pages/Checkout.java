package com.unicorns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
public class Checkout extends BasePage{

    private By countryField=By.name("rcrs-country");
    private By regionField=By.name("rcrs-region");
    private By addressField =By.cssSelector("div.tax-select input[name='address1']");
    private By zipField = By.cssSelector("div.tax-select input[name='postalcode']");
    private By phoneField = By.cssSelector("div.tax-select input[name='contact']");
    private By confirmButton = By.cssSelector("div.place-order button.btn-hover");
    private By checkbox =By.cssSelector("div.tax-select input[type='checkbox']");

    public void fillShippingData(String country,String region,String address,String zip,String phoneNumber) throws InterruptedException {
        scrollTo(addressField);
        Select countryDropdown = new Select(findElement(countryField));
        countryDropdown.selectByVisibleText(country);
        Select regionDropdown = new Select(findElement(regionField));
        regionDropdown.selectByVisibleText(region);
        findElement(addressField).sendKeys(address);
        findElement(zipField).sendKeys(zip);
        findElement(phoneField).sendKeys(phoneNumber);
        scrollTo(confirmButton);
        findElement(checkbox).click();
        Thread.sleep(10000);
        findElement(confirmButton).click();

    }
}
