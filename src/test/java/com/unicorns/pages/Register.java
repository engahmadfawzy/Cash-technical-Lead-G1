package com.unicorns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Register extends BasePage{

    private By UsernameField = By.id("username");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By genderField = By.id("gender");
    private By registerButton = By.xpath("//button[.='Register']");

    public void register(String Username,String email, String password,String gender){
        findElement(UsernameField).sendKeys(Username);
        findElement(emailField).sendKeys(email);
        findElement(passwordField).sendKeys(password);
        Select genderDropdown = new Select(findElement(genderField));
        genderDropdown.selectByVisibleText(gender);
        findElement(registerButton).click();
    }
}
