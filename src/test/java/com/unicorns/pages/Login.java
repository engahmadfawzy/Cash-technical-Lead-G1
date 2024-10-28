package com.unicorns.pages;

import org.openqa.selenium.By;

public class Login extends BasePage{

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[.='Login']");

    public void login(String email, String password){
        findElement(emailField).sendKeys(email);
        findElement(passwordField).sendKeys(password);
        findElement(loginButton).click();
    }
}
