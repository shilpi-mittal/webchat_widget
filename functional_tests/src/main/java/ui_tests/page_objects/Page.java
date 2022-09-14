package ui_tests.page_objects;

import ui_tests.utils.TestWebDriver;

class Page {
    public TestWebDriver testWebDriver;

    public Page(TestWebDriver testWebDriver) {
        this.testWebDriver = testWebDriver;
    }

    public static final String widgetModalIframeID = "podium-modal";

    public HomePage accessHomePage() {
        testWebDriver.goToBaseUrl();
        return PageObjectFactory.getHomePage(testWebDriver);
    }

    public String getUrl() {
        return testWebDriver.getCurrentUrl();
    }
}
