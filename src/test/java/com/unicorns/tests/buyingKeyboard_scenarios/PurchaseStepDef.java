package com.unicorns.tests.buyingKeyboard_scenarios;

import com.unicorns.tests.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PurchaseStepDef extends BaseTest {

    @Given("user click on keyboard button in categories section")
    public void userClickOnKeyboardButtonInCategoriesSection() {
        browser.unicorns.home.clickOnkeyboardCheckmark();
    }

    @And("user click on Apply Button")
    public void userClickOnApplyButton() {
        browser.unicorns.home.clickOnApplyButtton();
    }

    @When("user click on category {string} button in categories section")
    public void userClickOnCategoryButtonInCategoriesSection(String category) {
        browser.unicorns.home.clickOnCategoryCheckmark(category);
    }

    @Then("validate that only category {string} products appear")
    public void validateThatOnlyCategoryProductsAppear(String category) {
        Assert.assertTrue(browser.unicorns.home.areSelectedCategoyProductsOnlyShown(category));
    }

    @When("user click on product {string} in home page")
    public void userClickOnProductInHomePage(String productName) {
        browser.unicorns.home.clickOnProduct(productName);
    }

    @Then("validate that the product price is shown on product page")
    public void validateThatTheProductPriceIsShownOnProductPage() {
       Assert.assertTrue(browser.unicorns.productPage.areProductNameAndPriceShown());
    }

    @Then("validate that the cart is empty")
    public void validateThatTheCartIsEmpty() {
     //   Assert.assertTrue(browser.unicorns.productPage.checkNumberOfItemsInTheCart("0"));
        Assert.assertTrue(browser.unicorns.productPage.checkIfTheCartIsEmpty());
    }

    @When("user click on add to cart button")
    public void userClickOnAddToCartButton() {
        browser.unicorns.productPage.clickOnAddToCartButton();
    }

    @Then("the product is added to the cart")
    public void theProductIsAdedToTheCart() {
     //   Assert.assertTrue(browser.unicorns.productPage.checkNumberOfItemsInTheCart("1"));
        Assert.assertTrue(browser.unicorns.productPage.checkItemsAddedToCart());
    }

    @When("user click on products button")
    public void userClickOnProductsButton() {
        driver.navigateTo("https://practice-react.sdetunicorns.com/shop-grid-standard");
    }

    @Then("validate that the two products are successfully added")
    public void validateThatTheTwoProductsAreSuccessfullyAdded() {
    }

    @Then("validate that the total price is correct")
    public void validateThatTheTotalPriceIsCorrect() {
        System.out.println(browser.unicorns.productPage.firstItemPrice);
        System.out.println(browser.unicorns.productPage.secondItemPrice);
        System.out.println(browser.unicorns.productPage.totalItemsPrice);
        Assert.assertEquals(browser.unicorns.productPage.firstItemPrice+browser.unicorns.productPage.secondItemPrice,browser.unicorns.productPage.totalItemsPrice,0.0);
    }

    @And("user click on product {string}")
    public void userClickOnProduct(String productName2) {
        browser.unicorns.home.clickOnProduct(productName2);
    }

    @And("user click on cart button")
    public void userClickOnCartButton() {
        browser.unicorns.productPage.clickOnCartButton();
    }

    @When("user click on checkout button")
    public void userClickOnCheckoutButton() {
        browser.unicorns.productPage.clickOnCheckoutButton();
    }

    @And("user filling the country {string} and region {string} and address {string} and zipcode {string} and phone number {string} on checkout page")
    public void userFillingTheCountryAndRegionAndAddressAndZipcodeAndPhoneNumberOnCheckoutPage(String country, String region, String address, String zip, String phoneNumber) throws InterruptedException {
        browser.unicorns.checkout.fillShippingData(country,region,address,zip,phoneNumber);
    }

    @Then("validate that shipping details {string} and {string} and {string} and {string} are correct on confirmation page")
    public void validateThatShippingDetailsAreCorrectOnConfirmationPage(String country, String region, String address, String price) {
        Assert.assertTrue(browser.unicorns.confirmation.confirmShippingDetails(country));
        Assert.assertTrue(browser.unicorns.confirmation.confirmShippingDetails(region));
        Assert.assertTrue(browser.unicorns.confirmation.confirmShippingDetails(address));
        Assert.assertTrue(browser.unicorns.confirmation.confirmShippingDetails(price));
    }
}
