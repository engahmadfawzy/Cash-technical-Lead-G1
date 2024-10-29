package com.unicorns.tests.api_brands_tests;

import com.unicorns.apis.unicorns.BrandsAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BrandsApiStepDef {

    public BrandsAPI brandsAPI = new BrandsAPI();

    @Given("sent request to get all Brands")
    public void sentRequestToGetAllBrands() {
        brandsAPI.getAllBrandsRequest();
    }

    @Then("validate that get all brands response status code equals {string}")
    public void validateThatGetAllBrandsResponseStatusCodeEquals(String statusCode) {
        assertEquals(brandsAPI.getAllBrandsResponseStatusCode(),statusCode,"The Request Failed");
    }

    @Then("validate that number of all brands is more than one brand")
    public void validateThatNumberOfAllBrandsIsMoreThanOneBrand() {
        assertTrue(brandsAPI.getAllBrandsNumber() >= 1 ,"Brands are zero");
    }

    @Then("validate that each entry has id and name property")
    public void validateThatEachEntryHasIdAndNameProperty() {

        for (Map<String, Object> entry : brandsAPI.getAllBrandsEntries()) {
            // Check that each entry has both "_id" and "name" keys
            assertTrue(entry.containsKey("_id"), "Entry does not contain '_id'");
            assertTrue(entry.containsKey("name"), "Entry does not contain 'name'");
        }
    }

    @Given("sent request to get brand at id {string}")
    public void sentRequestToGetBrandAtId(String id) {
        brandsAPI.getBrandByIdRequest(id);
    }

    @Then("validate that get brand by id response status code equals {string}")
    public void validateThatGetBrandByIdResponseStatusCodeEquals(String statusCode) {
        assertEquals(brandsAPI.getBrandByIdStatusCode(),statusCode,"The Request Failed");

    }

    @Then("validate that get brand by id response body has brand id {string} and brand name {string}")
    public void validateThatGetBrandByIdResponseBodyHasBrandIdAndBrandName(String id, String brandName) {

        assertEquals(brandsAPI.getIdValueInGetBrandByIdResponseBody(), id);
        assertEquals(brandsAPI.getNameValueInGetBrandByIdResponseBody(), brandName);
    }

    @Then("validate that error message for wrong id is {string}")
    public void validateThatErrorMessageForWrongIdIs(String message) {
        assertEquals(brandsAPI.getErrorMessageForInvalidId(), message);
    }


    @Given("sent request to create brand with name {string} and description {string}")
    public void sentRequestToCreateBrandWithNameAndDescription(String name, String description) {
        brandsAPI.createBrand(name, description);
    }

    @Then("validate that create brand response status code equals {string}")
    public void validateThatCreateBrandResponseStatusCodeEquals(String statusCode) {
        assertEquals(brandsAPI.getCreatePostStatusCode(),statusCode,"The Request Failed");

    }

    @Then("validate that create brand response body has name {string} and description {string}")
    public void validateThatCreateBrandResponseBodyHasNameAndDescription(String name, String description) {
        assertEquals(brandsAPI.getNameValueInCreateBrandResponseBody(), name);
        assertEquals(brandsAPI.getDescriptionValueInCreateBrandResponseBody(), description);
    }

    @Then("validate that error message for duplicate brand is {string}")
    public void validateThatErrorMessageForDuplicateBrandIs(String message) {
        assertTrue(brandsAPI.getErrorMessageForDuplicateBrand().contains(message));
    }
}
