package com.unicorns.tests.api_brands_tests;

import com.aventstack.extentreports.Status;
import com.unicorns.utilities.extent_report.ExtentReport;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class APIHooksHandler {

    @Before(value = "@API", order = 1)
    public void startTCHooks(Scenario scenario) {
        ExtentReport.setScenario(scenario);
        ExtentReport.startTC();
    }
    @Before(value = "@API", order = 2)
    public void setStepDefs() throws NoSuchFieldException, IllegalAccessException {
        ExtentReport.setStepDefs();
    }
    @AfterStep(value = "@API")
    public void
    afterStep(Scenario scenario) {
        String stepName = ExtentReport.getCurrentStepName();
        Status logStatus;

        if (scenario.isFailed()) {
            logStatus = Status.FAIL;
            ExtentReport.getTest().log(logStatus, stepName);
        }
        else {
            logStatus = Status.PASS;
            ExtentReport.getTest().log(logStatus,stepName);
        }
    }
    @After(value = "@API", order = 1)
    public void endTC() {
        if (ExtentReport.isCurrentlyUsingReport()) {
            ExtentReport.getExtent().flush();
        }
    }

}