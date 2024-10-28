package com.unicorns.pages;
import org.openqa.selenium.By;

public class Confirmation extends BasePage {

    private By orderNumber =By.cssSelector("h3.panel-title");
    private By totalField =By.xpath("//*[contains(text(),'Total')]");

    public Boolean confirmShippingDetails(String detail){
        findElement(orderNumber).click();
        scrollTo(totalField);
        return findElement(By.xpath("//*[text()='" +detail + "']")).isDisplayed();
    }
   }
