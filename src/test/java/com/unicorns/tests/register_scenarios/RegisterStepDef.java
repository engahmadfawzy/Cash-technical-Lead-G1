package com.unicorns.tests.register_scenarios;

import com.unicorns.tests.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegisterStepDef extends BaseTest {

    @Given("user click on profile button")
    public void userClickOnProfileButton() {
        browser.unicorns.home.clickOnProfileButton();
    }

    @When("user click on register button")
    public void userClickOnRegisterButton() {
        browser.unicorns.home.clickOnRegisterButton();
    }

    @And("user fill username {string} and email {string} and password {string} and gender {string}")
    public void userFillUsernameAndEmailAndPasswordAndGender(String username, String email, String password, String gender) {
        browser.unicorns.register.register(username,email, password,gender);
    }

    @Then("validate that logout button is visible on home page")
    public void validateThatLogoutButtonIsVisibleOnHomePage() {
        Assert.assertTrue(browser.unicorns.home.isLogoutButtonDisplayed(),"logout Button is not displayed");
    }

}
