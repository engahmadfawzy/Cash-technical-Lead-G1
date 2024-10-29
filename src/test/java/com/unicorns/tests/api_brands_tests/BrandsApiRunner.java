package com.unicorns.tests.api_brands_tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/api_brands_tests/brandsApi.feature",
        glue = {"com.unicorns.tests.api_brands_tests"},
        plugin = {"html:reports/Brands.html"},
        monochrome = true
       ,tags = "@API"
)
public class BrandsApiRunner extends AbstractTestNGCucumberTests {

}
