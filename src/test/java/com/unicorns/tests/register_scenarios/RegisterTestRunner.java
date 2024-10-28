package com.unicorns.tests.register_scenarios;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/register_scenarios/register.feature",
        glue = {"com.unicorns.tests"},
        tags = "",
        plugin = {"html:reports/Register-Report.html"}

)
public class RegisterTestRunner extends AbstractTestNGCucumberTests {

}
