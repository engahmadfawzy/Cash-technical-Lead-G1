package com.unicorns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Home extends BasePage{

    private By profileButton = By.cssSelector("button.account-setting-active");
    private By loginButton = By.linkText("Login");
    private By logoutButton = By.cssSelector("button.account-setting-active");
    private By registerButton = By.linkText("Register");
    private By ApplyButtton = By.cssSelector("button.btn.btn-primary");
    private By keyboardCheckmark = By.xpath("//button[span[@class='checkmark'] and contains(.,'keyboard')]");
    private By productsList = By.cssSelector("div.col-lg-9.order-1.order-lg-2 div.product-content h3 a");

    public void clickOnProfileButton() {
        findElement(profileButton).click();
    }

    public void clickOnLoginButton() {
        clickOnProfileButton();
        findElement(logoutButton).click();
        findElement(loginButton).click();
    }

    public void clickOnRegisterButton() {
        findElement(registerButton).click();
    }

    public Boolean isLogoutButtonDisplayed(){
        clickOnProfileButton();
        return findElement(logoutButton).isDisplayed();
    }

    public void clickOnkeyboardCheckmark(){
        findElement(keyboardCheckmark).click();
    }

    public void clickOnCategoryCheckmark(String category){
        findElement(By.xpath("//button[span[@class='checkmark'] and contains(.,'" + category + "')]")).click();
    }

    public void clickOnApplyButtton(){
        scrollTo(ApplyButtton);
        findElement(ApplyButtton).click();
    }

    public int getProductsNumber(){
        return findElements(productsList).size();
    }

    public Boolean areSelectedCategoyProductsOnlyShown(String text){
        Boolean flag =false;
        List <WebElement> productNames  = findElements(productsList);
        for(int i =0 ; i<productNames.size() ; i++){
            if(!productNames.get(i).getText().toLowerCase().contains(text.toLowerCase())){
                break;
            }
            flag = true;
        }
        return flag;
    }

    public void clickOnProduct(String productName){
        findElement(productsList).click();
    }

}
