package ui_tests.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.How.*;

public class LocationWidget extends Page {
    @FindBy(how = CLASS_NAME, using = "LocationSelector")
    private static WebElement locationSelectorWidget = null;

    @FindBy(how = CLASS_NAME, using = "LocationSelector__Title")
    private static WebElement locationSelectorTitle = null;

    @FindBy(how = CLASS_NAME, using = "LocationSelector__Subtitle")
    private static WebElement locationSelectorSubtitle = null;

    @FindBy(how = CSS, using = "input[name=\"Search Locations\"]")
    private static WebElement locationInput = null;

    @FindBy(how = CLASS_NAME, using = "SearchInput__Icon")
    private static WebElement searchInputIcon = null;

    @FindBy(how = CLASS_NAME, using = "SearchInput__Reset")
    private static WebElement searchInputResetIcon = null;

    @FindBy(how = CLASS_NAME, using = "LocationsList")
    private static WebElement locationsList = null;

    @FindBy(how = CSS, using = ".LocationsList button")
    private static List<WebElement> listOfLocations = null;

    private String arrowSelector = ".LocationContainer__ArrowContainer";

    @FindBy(how = CSS, using = ".LocationSelector__PodiumPower .terms a")
    private static WebElement terms = null;

    private static final Logger LOG = LoggerFactory.getLogger(LocationWidget.class);

    public LocationWidget(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public boolean isLocationSelectorDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(locationSelectorWidget);
    }

    public String getLocationWidgetTitle() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(locationSelectorTitle);
    }

    public String getLocationWidgetSubTitle() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(locationSelectorSubtitle);
    }

    public String getLocationSearchInputPlaceholder() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getPlaceholder(locationInput);
    }

    public String getLocationSearchInput() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getAttribute(locationInput, "value");
    }

    public boolean isLocationSearchIconDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(searchInputIcon);
    }

    public boolean isClearLocationButtonDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(searchInputResetIcon);
    }

    public boolean isLocationsListDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(locationsList);
    }

    public boolean areTermsDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(terms);
    }

    public String getTermsText() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(terms);
    }

    public String getTermsLink() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getAttribute(terms, "href");
    }

    public void enterLocationSearchInput(String input) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        if (testWebDriver.isDisplayed(searchInputResetIcon)) {
            testWebDriver.clickOnElement(searchInputResetIcon);
        }
        testWebDriver.enterInput(locationInput, input);
        LOG.debug("Location search input is entered as " + input);
//        TODO replace by loader icon wait
        testWebDriver.sleep(1000);
        testWebDriver.waitForElementToAppear(locationsList);
    }

    public int getNumberOfLocationsInList() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.waitForElementToAppear(locationsList);
        testWebDriver.waitForElementToAppear(listOfLocations.get(0));
        return listOfLocations.size();
    }

    public List<String> getLocations() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.waitForElementToAppear(locationsList);
        testWebDriver.waitForElementToAppear(listOfLocations.get(0));
        List<String> locations = new ArrayList<String>();
        for (WebElement element : listOfLocations) {
            locations.add(element.getText());
        }
        return locations;
    }

    public MessageWidget selectLocationFromList(int locationPosition) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.waitForElementToAppear(locationsList);
        testWebDriver.clickOnElement(listOfLocations.get(locationPosition));
        testWebDriver.waitForElementToDisappear(locationsList);
        LOG.debug("Location at position " + locationPosition + " is selected from the list");
        return PageObjectFactory.getMessageWidget(testWebDriver);
    }

    public boolean isArrowDisplayedOnHover(int locationPosition) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.waitForElementToAppear(locationsList);
        testWebDriver.hoverOnElement(listOfLocations.get(locationPosition));
        LOG.debug("Hover on Location at position " + locationPosition + " in the list");
        testWebDriver.waitForElementToAppear(listOfLocations.get(locationPosition).findElement(By.cssSelector(arrowSelector)));
        return testWebDriver.isDisplayed(listOfLocations.get(locationPosition).findElement(By.cssSelector(arrowSelector)));
    }
}
