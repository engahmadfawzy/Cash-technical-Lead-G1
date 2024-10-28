package com.unicorns.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.unicorns.driver.WebDriverSingleton;
import com.unicorns.properties_reading.ReadPropertiesFile;
import com.unicorns.utilities.extent_report.ExtentReport;
import com.unicorns.utilities.extent_report.Screenshot;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class HooksHandler extends BaseTest {

    String unicornsLink;

    @Before(order = 1)
    public void setUp() throws InterruptedException, IOException {
        driver = WebDriverSingleton.getDriverSingleton();
        driver.resetCache();
        driver.maximizeWindow();

        properties = ReadPropertiesFile.setProperties();
        unicornsLink = properties.getProperty("unicornsLink");
        driver.navigateTo(unicornsLink);

    }
    @Before(order = 1)
    public void startTCHooks(Scenario scenario) {
        ExtentReport.setScenario(scenario);
        ExtentReport.startTC();
    }
    @Before(order = 2)
    public void setStepDefs() throws NoSuchFieldException, IllegalAccessException {
        ExtentReport.setStepDefs();
    }
    @AfterStep()
    public void
    afterStep(Scenario scenario) {
        String stepName = ExtentReport.getCurrentStepName();
        Status logStatus;

        if (scenario.isFailed()) {
            logStatus = Status.FAIL;
            String base64Screenshot = Screenshot.getScreenShot();
            ExtentReport.getTest().log(logStatus, stepName, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
        else {
            logStatus = Status.PASS;
            ExtentReport.getTest().log(logStatus,stepName);
        }
    }
    @After(order = 1)
    public void endTC() {
        if (ExtentReport.isCurrentlyUsingReport()) {
            ExtentReport.getExtent().flush();
        }
    }

    @After(order = 0)
    public void tearDownSiebel() throws InterruptedException {
        driver.resetCache();
        closeWebDriverAfterAllTestsHook();
    }

    private void closeWebDriverAfterAllTestsHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            WebDriverSingleton.close();
        }));
    }

}