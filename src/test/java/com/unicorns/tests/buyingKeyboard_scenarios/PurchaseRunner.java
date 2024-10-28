package com.unicorns.tests.buyingKeyboard_scenarios;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/buyingKeyboard_scenarios/buyingKeyboard.feature",
        glue = {"com.unicorns.tests"},
        tags = "",
        plugin = {"html:reports/Buying-Report.html"}

)
public class PurchaseRunner extends AbstractTestNGCucumberTests{
}
