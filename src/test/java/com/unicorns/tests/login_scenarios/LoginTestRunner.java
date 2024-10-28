package com.unicorns.tests.login_scenarios;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/unicorns/tests/login_scenarios/login.feature",
        glue = {"com.unicorns.tests"},
        tags = "",
        plugin = {"html:reports/Login-Report.html"}

)
public class LoginTestRunner extends AbstractTestNGCucumberTests {

}
