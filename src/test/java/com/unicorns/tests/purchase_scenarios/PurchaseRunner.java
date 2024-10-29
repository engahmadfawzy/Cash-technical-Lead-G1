package com.unicorns.tests.purchase_scenarios;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/purchase_scenarios/purchase.feature",
        glue = {"com.unicorns.tests"},
        plugin = {"html:reports/Buying-Report.html"},
        monochrome = true

)
public class PurchaseRunner extends AbstractTestNGCucumberTests{
}
