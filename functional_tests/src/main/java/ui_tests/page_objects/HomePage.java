package ui_tests.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

import static org.openqa.selenium.support.How.*;

public class HomePage extends Page {
    @FindBy(how = ID, using = "podium-website-widget")
    private static WebElement websiteWidget = null;

    @FindBy(how = ID, using = "podium-bubble")
    private static WebElement widgetBubbleIframe = null;

    @FindBy(how = CSS, using = "#main button.ContactBubble__Bubble")
    private static WebElement widgetButton = null;

    @FindBy(how = CSS, using = "#main button.ContactBubble__Bubble--opened")
    private static WebElement widgetCloseButton = null;

    @FindBy(how = ID, using = widgetModalIframeID)
    private static WebElement widgetIframe = null;

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

    public HomePage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public void openApplication() {
        testWebDriver.goToBaseUrl();
        testWebDriver.waitForElementToAppear(websiteWidget);
        testWebDriver.waitForElementToAppear(widgetBubbleIframe);
        LOG.info("Application is open");
    }

    public LocationWidget clickOnWebsiteWidget() {
        testWebDriver.switchIFrame(widgetBubbleIframe);
        testWebDriver.clickOnElement(widgetButton);
        testWebDriver.waitForElementToAppear(widgetCloseButton);
        return PageObjectFactory.getLocationWidget(testWebDriver);
    }

    public boolean isWidgetDisplayed() {
        testWebDriver.switchToDefault();
        return testWebDriver.isDisplayed(widgetIframe);
    }

    public void closeWebsiteWidget() {
        testWebDriver.switchIFrame(widgetBubbleIframe);
        testWebDriver.clickOnElement(widgetCloseButton);
        testWebDriver.waitForElementToAppear(widgetButton);
    }
}
