package ui_tests.page_objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

 public class PageObjectFactory {
    private static Page instanceOfPage;
    private static HomePage instanceOfHomePage;
    private static LocationWidget instanceOfLocationWidget;
    private static MessageWidget instanceOfMessageWidget;

    private static final Logger LOG = LoggerFactory.getLogger(PageObjectFactory.class);

    public static Page getPage(TestWebDriver testWebDriver) {
        if (instanceOfPage == null) {
            LOG.debug("New Page instance is created");
            instanceOfPage = new Page(testWebDriver);
        }
        return instanceOfPage;
    }

    public static HomePage getHomePage(TestWebDriver testWebDriver) {
        if (instanceOfHomePage == null) {
            LOG.debug("New Home Page instance is created");
            instanceOfHomePage = new HomePage(testWebDriver);
        }
        return instanceOfHomePage;
    }

    public static LocationWidget getLocationWidget(TestWebDriver testWebDriver) {
        if (instanceOfLocationWidget == null) {
            LOG.debug("New Location Widget instance is created");
            instanceOfLocationWidget = new LocationWidget(testWebDriver);
        }
        return instanceOfLocationWidget;
    }

    public static MessageWidget getMessageWidget(TestWebDriver testWebDriver) {
        if (instanceOfMessageWidget == null) {
            LOG.debug("New Message Widget instance is created");
            instanceOfMessageWidget = new MessageWidget(testWebDriver);
        }
        return instanceOfMessageWidget;
    }

    public static void clearAllPageObjectReferences() {
        instanceOfPage = null;
        instanceOfHomePage = null;
        instanceOfLocationWidget = null;
        instanceOfMessageWidget = null;
    }
}
