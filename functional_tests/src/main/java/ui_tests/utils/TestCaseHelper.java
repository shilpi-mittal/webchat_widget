package ui_tests.utils;

import common_utils.AssignmentUtils;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseHelper {
    private boolean isSeleniumStarted;
    public TestWebDriver testWebDriver;
    private final DriverFactory driverFactory = new DriverFactory();
    private static final Logger LOG = LoggerFactory.getLogger(TestCaseHelper.class);

    public TestWebDriver setup() {
        if (!isSeleniumStarted) {
            testWebDriver = loadDriver();
            addTearDownShutDownHook();
            isSeleniumStarted = true;
        }
        return testWebDriver;
    }

    protected void addTearDownShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (testWebDriver != null) {
                    tearDownSuite();
                }
            }
        });
    }

    public void tearDownSuite() {
        try {
            if (testWebDriver != null) {
                testWebDriver.quitDriver();
            }
            isSeleniumStarted=false;
        } catch (UnreachableBrowserException e) {
            e.printStackTrace();
        }
    }

    private TestWebDriver loadDriver() {
        String TEST_BROWSER = AssignmentUtils.getValue(System.getProperty("test_browser"), "chrome");
        LOG.info("TEST_BROWSER: " + TEST_BROWSER);
        testWebDriver = new TestWebDriver(driverFactory.loadDriver(true, TEST_BROWSER));
        LOG.info("TEST_BROWSER: " + TEST_BROWSER + " is opening");
        return testWebDriver;
    }
}
