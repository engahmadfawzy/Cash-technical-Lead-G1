package com.unicorns.tests.login_scenarios;

import com.unicorns.tests.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDef extends BaseTest {

    @When("user login with email {string} and password {string}")
    public void userLoginWithEmailAndPassword(String email, String password) {
        browser.unicorns.login.login(email, password);
    }
    @When("user click on login button")
    public void userClickOnLoginButton() {
        browser.unicorns.home.clickOnLoginButton();
    }
}
