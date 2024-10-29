package com.unicorns.tests;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/buyingKeyboard_scenarios/buyingKeyboard.feature",
        glue = {"com.unicorns.tests"},
        plugin = {"html:reports/Buying-Report.html"},
        monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests{
}
